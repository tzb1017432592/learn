package com.example.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * @author tianzhoubing
 * @date 2021/11/23 15:29
 * @description
 **/
@Slf4j
public class LogContentUtils {
    public static Map<String, Object> parseContent(String encodeStr) {
        HashMap<String, Object> resultMap = Maps.newHashMap();
        byte[] decodeAll = Base64.getDecoder().decode(encodeStr);
        String str = new String(decodeAll);
        if (StringUtils.contains(str, "Content-Type:")) {
            String[] contendSplit = str.split("\r\n\r\n");
            if (contendSplit.length <= 1) {
                return resultMap;
            }
            String content = contendSplit[contendSplit.length - 1];
            String httpAttributes = contendSplit[0];
            if (!org.springframework.util.StringUtils.hasText(content)) {
                return resultMap;
            }
            String contentType = "";
            boolean isJson = false;
            boolean isTextPlain = false;
            boolean isUrlencoded = false;
            String[] rows = httpAttributes.split("\n");
            HashMap<String, String> rowMap = Maps.newHashMap();
            for (int i = 1; i < rows.length; i++) {
                if ("\r\n\r\n".equals(rows[i])) {
                    break;
                }
                String[] rowArray = rows[i].split(":", 2);
                if (rowArray.length > 1) {
                    rowMap.put(rowArray[0].trim(), rowArray[1].trim());
                }
            }
            if (MapUtils.isNotEmpty(rowMap)) {
                String contentEncoding = rowMap.get("Content-Encoding");
                contentType = rowMap.get("Content-Type");
                if (StringUtils.isNotBlank(contentType)) {
                    if (StringUtils.isNotBlank(contentEncoding) && contentEncoding.contains("gzip")) {
                        String json = processGzip(decodeAll);
                        resultMap.put(contentType, json);
                        return resultMap;
                    }
                    if (contentType.contains("json")) {
                        isJson = true;
                    } else if (contentType.contains("text/plain")) {
                        isTextPlain = true;
                    } else if (contentType.contains("urlencoded")) {
                        isUrlencoded = true;
                    }
                }
            }
            if (isJson) {
                JSONObject jsonObject = null;
                try {
                    if (StringUtils.contains(content, "{")) {
                        jsonObject = JSONObject.parseObject(content);
                    } else {
                        byte[] jsonDecode = Base64.getDecoder().decode(content);
                        String jsonContent = new String(jsonDecode);
                        jsonObject = JSONObject.parseObject(jsonContent);
                    }
                } catch (Exception e) {
                    log.error("es json解析错误【" + e + "】");
                }
                resultMap.put(contentType, jsonObject);
                //loop(jsonObject, resultList);

            } else if (isTextPlain || isUrlencoded) {
                resultMap.put(contentType, content);
            }
        }
        return resultMap;
    }

    static Integer err = 0;
    static Integer suc = 0;

    private static String processGzip(byte[] decode) {
        String resultStr = StringUtils.EMPTY;
        byte[] splitBytes = "\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);
        int index = 0;
        f:
        for (int i = 0; i < decode.length; i++) {
            if (i == decode.length - 4) {
                break;
            }
            int count = 0;
            while (i < decode.length && count < splitBytes.length) {
                if (decode[i] != splitBytes[count]) {
                    break;
                }
                i++;
                count++;
                if (count == 4) {
                    index = i;
                    break f;
                }
            }
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] checkBytes = new byte[4];
            int checkIndex = 0;
            for (int i = decode.length - 4; i < decode.length; i++) {
                checkBytes[checkIndex] = decode[i];
                checkIndex++;
            }
            if (Arrays.equals(checkBytes, splitBytes)) {
                int newLen = decode.length - 5;
                while (((decode[newLen] != 13 && decode[newLen + 1] != 10) || (decode[index] != 10 && decode[index - 1] != 13)) && newLen > index) {
                    if (decode[newLen] != 13 && decode[newLen + 1] != 10) {
                        newLen--;
                    }
                    if (decode[index] != 10 && decode[index - 1] != 13) {
                        index++;
                    }
                }
                for (int i = index + 1; i < newLen; i++) {
                    out.write(decode[i]);
                }
            } else {
                for (int i = index; i < decode.length; i++) {
                    out.write(decode[i]);
                }
            }
            try (ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
                 GZIPInputStream gunzip = new GZIPInputStream(in);
                 ByteArrayOutputStream fout = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[256];
                int n;
                while ((n = gunzip.read(buffer)) >= 0) {
                    fout.write(buffer, 0, n);
                }
                resultStr = fout.toString();
                if (StringUtils.isNotBlank(resultStr)) {
                    suc++;
                }
            } catch (Exception e) {
                err++;
                log.error("");
            }
        } catch (Exception e) {
            log.error("");
        }
        return resultStr;
    }

    private static void loop(JSONObject jsonObject, List<Pair<String, Object>> resultList) {
        if (Objects.isNull(jsonObject)) {
            return;
        }
        jsonObject.forEach((k, v) -> {
            if (v instanceof JSONArray) {
                try {
                    JSONArray array = (JSONArray) v;
                    array.stream()
                            .filter(Objects::nonNull)
                            .forEach(e -> {
                                try {
                                    if (e instanceof JSONObject) {
                                        loop((JSONObject) e, resultList);
                                    } else if (e instanceof String) {
                                        Pair<String, Object> node = Pair.of(k, e.toString());
                                        resultList.add(node);
                                    } else if (e instanceof Integer) {
                                        Pair<String, Object> node = Pair.of(k, e);
                                        resultList.add(node);
                                    }
                                } catch (Exception e2) {
                                    log.error("json数组内部元素解析失败:" + e2);
                                }
                            });
                } catch (Exception e) {
                    log.error("json数组解析失败:" + e);
                }
            } else if (v instanceof JSONObject) {
                JSONObject jv = (JSONObject) v;
                loop(jv, resultList);
            } else {
                resultList.add(Pair.of(k, v));
            }
        });
    }

    public static void main(String[] args) throws IOException {
        List<String> stringList = FileUtils.readLines(new File("C:\\Users\\10174\\Desktop\\logjson\\gzip.text"), StandardCharsets.UTF_8);
        List<Map<String, Object>> lines = new ArrayList<>();
        for (String s : stringList) {
            lines.add(parseContent(s));
        }
        System.out.println("err:" + err);
        System.out.println("suc:" + suc);
    }

    @Test
    public void test345() throws Exception {

        URL url = new URL("https://foundation.youdao.com/offline/allpatch?patchkey=NMT");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
        conn.connect();
        InputStream inconn = conn.getInputStream();
        ByteArrayOutputStream orOutArray = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = inconn.read(buf)) != -1) {
            orOutArray.write(buf, 0, len);
        }
        String entity = "SFRUUC8xLjEgMjAwIA0KU2VydmVyOiBuZ2lueA0KRGF0ZTogVHVlLCAwMiBOb3YgMjAyMSAwNTo0MToxMCBHTVQNCkNvbnRlbnQtVHlwZTogYXBwbGljYXRpb24vanNvbjtjaGFyc2V0PXV0Zi04DQpUcmFuc2Zlci1FbmNvZGluZzogY2h1bmtlZA0KQ29ubmVjdGlvbjoga2VlcC1hbGl2ZQ0KVmFyeTogQWNjZXB0LUVuY29kaW5nDQpDb250ZW50LUVuY29kaW5nOiBnemlwDQoNCmMyDQofiwgAAAAAAAQDLY7BDoIwEET/Zc8IFVqwnLnCRT2bst0IEVpiSwwS/t1CPM7Lzs5bYXRPKMHNiBABWk1Qsgi08grKFQx9yPnqn3Z6fw/hvvN+cmWSGOtiQ56UoxjtmCzaYWftcLJm6A0lZyEZvwiRFVzkk/LYPVKWMibSPG57EyYP+KIlPG3qWwB28r01Kqysh8a1/+5O8d+qmceQtgjqSoQOaSa5VKrgHJFIKNnmO8rbNNMoC9i27QdwMzK25QAAAA0KMA0KDQo=";
        byte[] decode = Base64.getDecoder().decode(entity);
        System.out.println(new String(decode, "UTF-8"));
        System.out.println(Arrays.toString(decode));
        String str = new String(decode);
        System.out.println(str);
        byte[] splitBytes = "\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1);
        int index = 0;
        f:
        for (int i = 0; i < decode.length; i++) {
            if (i == decode.length - 4) {
                break;
            }
            int count = 0;
            while (i < decode.length && count < splitBytes.length) {
                if (decode[i] != splitBytes[count]) {
                    break;
                }
                i++;
                count++;
                if (count == 4) {
                    index = i;
                    break f;
                }
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int newLen = decode.length - 5;
        while (((decode[newLen] != 13 && decode[newLen + 1] != 10) || (decode[index] != 10 && decode[index - 1] != 13)) && newLen > index) {
            if (decode[newLen] != 13 && decode[newLen + 1] != 10) {
                newLen--;
            }
            if (decode[index] != 10 && decode[index - 1] != 13) {
                index++;
            }
        }
        for (int i = index + 1; i < newLen; i++) {
            out.write(decode[i]);
        }
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        GZIPInputStream gunzip = new GZIPInputStream(in);
        ByteArrayOutputStream fout = new ByteArrayOutputStream();
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            fout.write(buffer, 0, n);
        }
        System.out.println(fout.toString());
        in.close();
        gunzip.close();
        out.close();
        fout.close();
        //System.out.println(Arrays.toString(split));
    }

    @Test
    public void test3145() throws Exception {
        byte[] bytes = "dwe".getBytes(StandardCharsets.ISO_8859_1);
        String s = Arrays.toString(bytes);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(bytes, 0, bytes.length);
        System.out.println(Arrays.toString(out.toByteArray()));
    }

    @Test
    public void test315() throws Exception {
        File f = new File("C:\\Users\\10174\\Desktop\\json\\新建文本文档.txt");
        String s = FileUtils.readFileToString(f, StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(s.split("\r\n\r\n")));
    }
}
