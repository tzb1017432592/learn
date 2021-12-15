package com.example.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import ws.schild.jave.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author tianzhoubing
 * @date 2021/11/14 23:16
 * @description
 **/
public class Test {
    @org.junit.Test
    public void test() {
        String test = "{\n" +
                "    \"accessPoint\": \"hkbn.net_810000_4\",\n" +
                "    \"carrier\": \"hkbn.net\",\n" +
                "    \"clientIsp\": \"hkbn.net\",\n" +
                "    \"code\": 1000,\n" +
                "    \"cv\": 0,\n" +
                "    \"dns\": [\n" +
                "        {\n" +
                "            \"aisles\": [\n" +
                "                {\n" +
                "                    \"cto\": 10000,\n" +
                "                    \"heartbeat\": 45000,\n" +
                "                    \"port\": 443,\n" +
                "                    \"protocol\": \"http2\",\n" +
                "                    \"publickey\": \"acs\",\n" +
                "                    \"retry\": 1,\n" +
                "                    \"rto\": 10000,\n" +
                "                    \"rtt\": \"0rtt\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"host\": \"orange-dc.alibaba.com\",\n" +
                "            \"ips\": [\n" +
                "                \"198.11.136.50\"\n" +
                "            ],\n" +
                "            \"safeAisles\": \"https\",\n" +
                "            \"strategies\": [],\n" +
                "            \"ttl\": 300,\n" +
                "            \"version\": \"0\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"ip\": \"61.93.179.153\",\n" +
                "    \"test\": {\n" +
                "        \"accessPoint2\": \"hkbn.net_810000_4\",\n" +
                "        \"carrier2\": \"hkbn.net\",\n" +
                "        \"clientIsp2\": \"hkbn.net\",\n" +
                "        \"code2\": 1000,\n" +
                "        \"cv2\": 0,\n" +
                "        \"dns2\": [\n" +
                "            {\n" +
                "                \"aisles\": [\n" +
                "                    {\n" +
                "                        \"cto2\": 10000,\n" +
                "                        \"heartbeat2\": 45000,\n" +
                "                        \"port2\": 443,\n" +
                "                        \"protocol2\": \"http2\",\n" +
                "                        \"publickey2\": \"acs\",\n" +
                "                        \"retry2\": 1,\n" +
                "                        \"rto2\": 10000,\n" +
                "                        \"rtt2\": \"0rtt\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"host2\": \"orange-dc.alibaba.com\",\n" +
                "                \"ips2\": [\n" +
                "                    \"198.11.136.50\"\n" +
                "                ],\n" +
                "                \"safeAisles2\": \"https\",\n" +
                "                \"strategies2\": [],\n" +
                "                \"ttl2\": 300,\n" +
                "                \"version2\": \"0\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"ip2\": \"61.93.179.153\",\n" +
                "        \"unit2\": \"unzbmix\",\n" +
                "        \"utdid2\": \"YVpNN4I/escDACDGanYYpo1z\"\n" +
                "    },\n" +
                "    \"unit\": \"unzbmix\",\n" +
                "    \"utdid\": \"YVpNN4I/escDACDGanYYpo1z\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(test);
        List<Pair<String, Object>> resultList = new ArrayList<>();
        loop(jsonObject, resultList);
        System.out.println(JSONObject.toJSONString(resultList));
    }

    @org.junit.Test
    public void test343() {
        String encoded = "SFRUUC8xLjEgMjAwIE9LDQpTZXJ2ZXI6IFRlbmdpbmUNCkRhdGU6IFRodSwgMDQgTm92IDIwMjEgMDI6MzY6MTMgR01UDQpDb250ZW50LVR5cGU6IGFwcGxpY2F0aW9uL2pzb247Y2hhcnNldD1VVEYtOA0KQ29udGVudC1MZW5ndGg6IDU2NA0KQ29ubmVjdGlvbjogY2xvc2UNCkNhY2hlLUNvbnRyb2w6IG5vLWNhY2hlDQpwcmFnbWE6IG5vLWNhY2hlDQp4LWFtLXNpZ246IDA5MTQ3OWY3ZDdiODk5Yzg4OGYwMTBlZDQ0NzIyMWE3ZThhZDRiNzcNCngtYW0taWQ6IGFtZGMwMTEwMjAxNDUxMzEuY2VudGVyLm5hNjIwXzE2MzU5OTMzNzM4ODNfNDE0MTYyOTU1DQp4LWFtLWNvZGU6IDEwMDANCg0KZXlKaFkyTmxjM05RYjJsdWRDSTZJbWhyWW00dWJtVjBYemd4TURBd01GODBJaXdpWTJGeWNtbGxjaUk2SW1oclltNHVibVYwSWl3aVkyeHBaVzUwU1hOd0lqb2lhR3RpYmk1dVpYUWlMQ0pqYjJSbElqb3hNREF3TENKamRpSTZNQ3dpWkc1eklqcGJleUpoYVhOc1pYTWlPbHQ3SW1OMGJ5STZNVEF3TURBc0ltaGxZWEowWW1WaGRDSTZORFV3TURBc0luQnZjblFpT2pRME15d2ljSEp2ZEc5amIyd2lPaUpvZEhSd01pSXNJbkIxWW14cFkydGxlU0k2SW1GamN5SXNJbkpsZEhKNUlqb3hMQ0p5ZEc4aU9qRXdNREF3TENKeWRIUWlPaUl3Y25SMEluMWRMQ0pvYjNOMElqb2liM0poYm1kbExXUmpMbUZzYVdKaFltRXVZMjl0SWl3aWFYQnpJanBiSWpFNU9DNHhNUzR4TXpZdU5UQWlYU3dpYzJGbVpVRnBjMnhsY3lJNkltaDBkSEJ6SWl3aWMzUnlZWFJsWjJsbGN5STZXMTBzSW5SMGJDSTZNekF3TENKMlpYSnphVzl1SWpvaU1DSjlYU3dpYVhBaU9pSTJNUzQ1TXk0eE56a3VNVFV6SWl3aWRXNXBkQ0k2SW5WdWVtSnRhWGdpTENKMWRHUnBaQ0k2SWxsV2NFNU9ORWt2WlhOalJFRkRSRWRoYmxsWmNHOHhlaUo5";
        String newStr = "";
        if (StringUtils.isNotBlank(encoded)) {
            newStr = encoded.substring(0, 300);
        }
        byte[] decoded = Base64.getDecoder().decode(newStr);
        String s = new String(decoded);
        if (StringUtils.containsIgnoreCase(s, "json")) {
            byte[] decode = Base64.getDecoder().decode(encoded);
            String str = new String(decode);
            String[] split = str.split("\n");
            String s1 = split[split.length - 1];
            JSONObject jsonObject;
            if (StringUtils.contains(s1, "{")) {
                jsonObject = JSONObject.parseObject(s1);
            } else {
                byte[] decode2 = Base64.getDecoder().decode(s1);
                String s2 = new String(decode2);
                jsonObject = JSONObject.parseObject(s2);
            }
            List<Pair<String, Object>> resultList = new ArrayList<>();
            loop(jsonObject, resultList);
        }

    }

    private void loop(JSONObject jsonObject, List<Pair<String, Object>> resultList) {
        jsonObject.forEach((k, v) -> {
            if (v instanceof JSONArray) {
                try {
                    List<JSONObject> list = JSONArray.parseArray(v.toString(), JSONObject.class);
                    list.forEach(e -> {
                        loop(e, resultList);
                    });
                } catch (Exception e) {
                    System.err.println("報錯：" + e);
                    List<String> list = JSONArray.parseArray(v.toString(), String.class);
                    for (String s : list) {
                        Pair<String, Object> node = Pair.of(k, s);
                        resultList.add(node);
                    }
                }
            } else if (v instanceof JSONObject) {
                JSONObject jv = (JSONObject) v;
                loop(jv, resultList);
            } else {
                resultList.add(Pair.of(k, v));
            }
        });
    }

    @org.junit.Test
    public void test34() {
        String encoded = "SFRUUC8xLjEgMjAwIE9LDQpTZXJ2ZXI6IFRlbmdpbmUNCkRhdGU6IFRodSwgMDQgTm92IDIwMjEgMDI6MzY6MTMgR01UDQpDb250ZW50LVR5cGU6IGFwcGxpY2F0aW9uL2pzb247Y2hhcnNldD1VVEYtOA0KQ29udGVudC1MZW5ndGg6IDU2NA0KQ29ubmVjdGlvbjogY2xvc2UNCkNhY2hlLUNvbnRyb2w6IG5vLWNhY2hlDQpwcmFnbWE6IG5vLWNhY2hlDQp4LWFtLXNpZ246IDA5MTQ3OWY3ZDdiODk5Yzg4OGYwMTBlZDQ0NzIyMWE3ZThhZDRiNzcNCngtYW0taWQ6IGFtZGMwMTEwMjAxNDUxMzEuY2VudGVyLm5hNjIwXzE2MzU5OTMzNzM4ODNfNDE0MTYyOTU1DQp4LWFtLWNvZGU6IDEwMDANCg0KZXlKaFkyTmxjM05RYjJsdWRDSTZJbWhyWW00dWJtVjBYemd4TURBd01GODBJaXdpWTJGeWNtbGxjaUk2SW1oclltNHVibVYwSWl3aVkyeHBaVzUwU1hOd0lqb2lhR3RpYmk1dVpYUWlMQ0pqYjJSbElqb3hNREF3TENKamRpSTZNQ3dpWkc1eklqcGJleUpoYVhOc1pYTWlPbHQ3SW1OMGJ5STZNVEF3TURBc0ltaGxZWEowWW1WaGRDSTZORFV3TURBc0luQnZjblFpT2pRME15d2ljSEp2ZEc5amIyd2lPaUpvZEhSd01pSXNJbkIxWW14cFkydGxlU0k2SW1GamN5SXNJbkpsZEhKNUlqb3hMQ0p5ZEc4aU9qRXdNREF3TENKeWRIUWlPaUl3Y25SMEluMWRMQ0pvYjNOMElqb2liM0poYm1kbExXUmpMbUZzYVdKaFltRXVZMjl0SWl3aWFYQnpJanBiSWpFNU9DNHhNUzR4TXpZdU5UQWlYU3dpYzJGbVpVRnBjMnhsY3lJNkltaDBkSEJ6SWl3aWMzUnlZWFJsWjJsbGN5STZXMTBzSW5SMGJDSTZNekF3TENKMlpYSnphVzl1SWpvaU1DSjlYU3dpYVhBaU9pSTJNUzQ1TXk0eE56a3VNVFV6SWl3aWRXNXBkQ0k2SW5WdWVtSnRhWGdpTENKMWRHUnBaQ0k2SWxsV2NFNU9ORWt2WlhOalJFRkRSRWRoYmxsWmNHOHhlaUo5";
        byte[] decode = Base64.getDecoder().decode(encoded);
        String str = new String(decode);
        String[] split = str.split("\n");
        String s1 = split[split.length - 1];
        byte[] decode2 = Base64.getDecoder().decode(s1);
        String s2 = new String(decode2);
        System.out.println(s2);
        JSONObject jsonObject = JSONObject.parseObject(s2);
        boolean is = jsonObject.get("dns") instanceof JSONArray;
        if (is) {
            System.err.println("==================================================================");
            List<JSONObject> list = JSONArray.parseArray(jsonObject.get("dns").toString(), JSONObject.class);
            list.forEach(e -> {
                if (e.get("aisles") instanceof JSONArray) {
                    List<JSONObject> jsonObjects = JSONArray.parseArray(e.get("aisles").toString(), JSONObject.class);
                    jsonObjects.forEach(e2 -> {
                        e2.forEach((jk, jv) -> {
                            System.out.println(jk + " " + jv);
                        });
                    });
                }
            });
        }
    }

    @org.junit.Test
    public void test12() {
        String str = "eyJIIjp7Im1vZGVsIjoiTUlYIDIiLCJhbmRyb2lkIjoiOSIsIm1pdWkiOiJWMTIuMC4xLjAuUERFQ05YTSIsImJuIjoiUyIsInByb2R1Y3QiOiJjaGlyb24iLCJkZXZpY2UiOiJjaGlyb24iLCJjdiI6IjMuNi4wIiwibGFuZyI6InpoX0NOIiwicmVnaW9uIjoiQ04iLCJtaSI6IjAiLCJzZW5kZXIiOiJjb20ubWl1aS5hbmFseXRpY3MiLCJ1c2VyaWQiOjAsIm9hIjoiNGY5NTdhNWM4MzY2N2NmOSIsIm4iOiIwIiwiYWFpZCI6ImI0MDc0NDZiLWJmODgtNDlmNC1iNWE1LTczMDkzOTJhYzlmNSIsIm1hYyI6IjJlMmQ2OTZlNTI4MTc4MGFiNDkwMGExOTI3ZjI3Nzc2IiwiaW1laSI6ImI0ZDc5NmIyZmMyNmJmYzk5MDFmMDAyOTZhMjU2OGJkIiwic3QiOiIxNjM1MzI4MTE5MTIxIn0sIkIiOlt7IkgiOnsic2lkIjoiIiwicGsiOiJjb20uYW5kcm9pZC5xdWlja3NlYXJjaGJveCIsImtleSI6InF1aWNrc2VhcmNoYm94X2hvdHN1Z2dlc3QiLCJldmVudFRpbWUiOiIxNjM1MzI4MTE5MDUzIiwic24iOiIzNjMxMzY2OTY2NzE5NTM4IiwicmV0cnlDbnQiOjB9LCJCIjp7Il9jYXRlZ29yeV8iOiJhZCIsIl9hY3Rpb25fIjoiQVBQX0xBVU5DSF9TVEFSVF9ERUVQTElOSyIsImV4IjoiaG1iX0M0VllNNlNjaWZkZ3hqUFg5TGlFekF0NFhBYk5UUVhmeGlXU0lKdktwZWg4Zk5WVkdHZ3Q0NkkzcmNTcVBBU�\u001B9�d\u0003;U\u0001U0wVlQ0cEJOeUhzR2IxN3RCUkNvMWVIRDl1MkRmQ1pRZlEtQ0cwWm9Tc1J0RHNWUWt1SjFwajJlcmNfT010SFZDTUdKeEZCb2RfcGlBWWRab1VGaHRuenpHanRaazJHcU9OMkFhVG9Nc3ZvX1Zyc0dfOFRzZzkwRHNzRGtadUZCNTM3NU1EeE96TjZaWWRCeGhFbXA1QTVuMFhjWG84QkF4eTlaWE91YlhfeTlmajkwWW1iekpyZjJnZC11MFQ4dTNaSW96cE9PRElLMGRLRDk0OHVVblo3QlFOc1pkN2RSSVZDSXRtdFJSYVFYdWd0UzJSckxheVdzZ0t1Umk4WU01dDdMRkpWejhyNW5mWDUxVEstSklXSjI3bzFURF9XVWtvMWFRTVk5MkVoOVZabDZNWDJ2dFNVSEZYQ3pwUzN6akhxb2JuR0lOMTVKSHFPbjVBdTNtLWp0ckRTSm5tbEkxZ21NbVZmSjllaG1FazJyN01RQ18tUjBMVTdpajhwTTA3YmhudXhPdWF5SEU1UU05UDFxZWdld3hsRS1BVndiaXJKUm4zMjdtZGdqZkE1d25OTVNkN0t4ZHRUdjN2aVZiZElXUmhlX2lxbnY0dU9veUp6MXRUMUxqRG1mb0wxX0hsOGp0TC1UWFg5cU1JWnNJRHFfSVo3ajBJamRMRnBlV1FOOXBwbXQtWGU2YUZOY2RDbU1STWlmM3d5eHFOamM2RTNtNE9jYlo1LXpGUXdub3FTMHNmODBSWFBwSXktVEdiMmllZzZEZk5iN3I3VVhwdkp0TUNnNzFHcE5rSW5Yd3lWd25odUhwMVV5THlsRzE3YnRLVTNxS2k2QWVnRFcyTlFZa3g2cDUzZTN4QzVjN0MzSV9fMVU2ZG15dVdPLVNRUVh1QXdpc3NrWUJzbUZTVkVZZVNvYjc2MlJXMmN1S0IyWUpjNlRQdExUOVJSb0dTTUZYMnZHbGFJV1VmbkRVc05MSldMdUkteFFISF9JaWNNR2FULVdRYzM0bl82S3hEdjdMejBXZmtTMnlnd3BxVjRPTWxGSTBab2lYYVRiVVdjQ243Vl9hdENaQ3NPdmJkNVBxWFQtMUlEdlY2dmw0T19lYm9SaVY0UEMwTTlhWEtBWVl3QjJjb29EOGtuTGd5cGNZYmpOU0p4TXdNTUFpb1RsNndjWnRrUE8zLWtYRjBvS0dyX2U1cHdURC1POER3dmpqeUE3TWZ5S1gwRDNpbm0yR0pmaDFNaGF6Tk43Ykc1QmZtdVE5dzBtZDhWdE5mdlE5cXhGSjZXV0hwd296OS1TcDBRRGNLVDdkeE5tQWJEd3M1R2FrYTUyVVJrWFNSV2d3UzZGS2Fxcm51WWZSdUp1c0l6R1JTdFBjY1JkM0ZfcjBqRTdXRVVBcEgtLTAzdElaT0RXWDN5cklaOGdIb3kydzc3NmdSTjl6ajhPNndELTJWMGhSXzRpdkFiTldfVm1TblVTdDJYMm80NlBGLXVKcG94aHh6REd3VzVOOXJtOTJHNVhiOFpndGp4anIwaDUyalktMDY2MEJibURvQjRQTTRfTmp4dDJsQ05ZWmpnRzAyRnVBelhPbWNTXzkxclVqLVdQMW8tSFZORFZqbjN2QVFBbXFPRl9BUnVlZUdFb3djYkNkc0s5cjZCSTg2aE1XZ0dzel92VXdIYU00YlBzanZ1WkwzbHAwNXN6QnAyY3FRc2ZYdFlYMGhZNlBmNVA4MGp5Y1Q5V2JrRVRZdWx6WC1uYk5BSzFaSVE3R213bkFkMVZtWG94ZmEtMUpRY1ZjTE9sTGZPTWVxaHVjWWczWGtsS2hmNXEya2VYb0ZGUDdwR3VUNnFWaVFWUVFuSnY4V3hYMGJ1RGZ1YmszWkFVbm9RNG9FMFp3NC02X1FrMnZlNHRVYnhYRnpDWjUwazZmUDRpQ3RENlo2R2gybWttaEcxY2puN1VSX3NCbGc4VFpYNXhkdklzNEZaUXloUmtmQllYaTJYYjBYbmt3Qm9zLThNRUJXWVlRS1VoUjZ1OXdRUmszMV9sTlg3RmhFTjJ0SWk3S0M3dyIsInQiOjE2MzUzMjgxMTkwNTEsImUiOiJBUFBfTEFVTkNIX1NUQVJUX0RFRVBMSU5LIiwidiI6InNka18xLjAifX1dfQ%3D%3D&sign=6a4e6b7b23aa85e2838438fec058d9a0&ts=1635328119105&nonce=2e94e13f9fa37a9e3dc49a5b3b3c7022";
        byte[] decode = Base64.getDecoder().decode(str);
        String decodeStr = new String(decode);
        System.out.println(decodeStr);
    }

    @org.junit.Test
    public void test1324() {
        String str = "DFff";
        System.out.println(str.indexOf("t"));
    }

    @org.junit.Test
    public void test124() {
        String str = "POST /partnersDeviceManageServer/device/deviceTurnOn.do HTTP/1.1\n" +
                "Connection: close\n" +
                "User-Agent: Dalvik/1.6.0 (Linux; U; Android 10; Konka Smart TV sabrina Build/LMY47V)\n" +
                "Content-Type: application/json;charset=utf-8\n" +
                "Host: pdm.kkapp.com\n" +
                "Accept-Encoding: gzip\n" +
                "Content-Length: 146";
        String[] split = str.split("\n");
        for (String s : split) {
            if (s.contains("Content-Type:")) {
                String substring = s.substring(s.indexOf(":") + 1);
                System.out.println(substring);
            }
        }
    }


    @org.junit.Test
    public void test132() {
        try {
            Tika tika = new Tika();
            String filePath = "C:\\Users\\10174\\Desktop\\新建文件夹 (2)\\wenti\\3c15a80dc182b6a534b17aa21fd6824d_3635096.mp4";
            InputStream inputStream = new FileInputStream(filePath);
            String s = tika.parseToString(inputStream);
            System.out.println(s);
        } catch (IOException | TikaException e) {
            System.out.println("错误：" + e);
        }

    }

    @org.junit.Test
    public void test133() throws EncoderException {
        File source = new File("C:\\Users\\10174\\Desktop\\新建文件夹 (2)\\wenti\\9F206159DBFA854B1C35A9B06CFA2FB8_2742807.mp4");

        // File source = new File("C:\\Users\\10174\\Desktop\\新建文件夹 (2)\\video\\2112d64679fec516127e2e2e6f3cc363_5003036.mp4");
        File target = new File("C:\\Users\\10174\\Desktop\\新建文件夹 (2)\\wenti\\target.mp4");


        MultimediaObject object = new MultimediaObject(source);
        MultimediaInfo info = object.getInfo();
        System.out.println("格式format：" + info.getFormat());
        System.out.println("时长duration：" + info.getDuration());
        VideoInfo videoInfo = info.getVideo();
        System.out.println("编码decoder：" + videoInfo.getDecoder());
        System.out.println("码率bitRate：" + videoInfo.getBitRate());
        System.out.println("帧率frameRate：" + videoInfo.getFrameRate());
        VideoSize videoSize = videoInfo.getSize();
        System.out.println("分辨率：" + videoSize.getWidth() + " x " + videoSize.getHeight());
        AudioInfo audioInfo = info.getAudio();
        System.out.println("编码decoder：" + audioInfo.getDecoder());
        System.out.println("码率bitRate：" + audioInfo.getBitRate());
        System.out.println("声道channels：" + audioInfo.getChannels());
        System.out.println("采样率samplingRate：" + audioInfo.getSamplingRate());

        // 视频属性
        VideoAttributes video = new VideoAttributes();
        video.setCodec("libx264");
        video.setBitRate(256000); // 码率：256kbps
        video.setFrameRate(25); // 帧率：25fps
        int width = 640; // 固定宽度
        int height = width * videoSize.getHeight() / videoSize.getWidth();
        height = height % 2 == 0 ? height : height + 1; // 视频宽高必须是偶数
        video.setSize(new VideoSize(width, height));
        // 音频属性
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("aac");
        audio.setBitRate(64000); // 码率：64kbps
        audio.setSamplingRate(24000); // 采样率：24kHz
        // 转码属性
        EncodingAttributes attributes = new EncodingAttributes();
        attributes.setVideoAttributes(video);
        attributes.setAudioAttributes(audio);
        attributes.setFormat("mp4");

        Encoder encoder = new Encoder();
        encoder.encode(object, target, attributes);

        System.out.println("转码完成：新分辨率：" + width + " x " + height);

    }

    @org.junit.Test
    public void test135() {
        try {
            File source = new File("C:\\Users\\10174\\Desktop\\complete_mp4\\B78C124653DDAA84638983417C10FF0D_28022694.mp4");
            File source2 = new File("C:\\Users\\10174\\Desktop\\complete_mp4\\80A9A547BEE562E6382BF291E9AD4FFA_25259496.mp4");
            MultimediaObject object = new MultimediaObject(source);
            MultimediaObject object2 = new MultimediaObject(source2);
            MultimediaInfo info = object.getInfo();
            MultimediaInfo info2 = object2.getInfo();
            System.out.println(info.toString());
            System.out.println(info2.toString());
        } catch (Exception e) {
            System.out.println("视频有错误：" + e);
        }
    }

    @org.junit.Test
    public void test1351() {
        try {
            //File source = new File("C:\\Users\\10174\\Desktop\\complete_mp4\\B78C124653DDAA84638983417C10FF0D_28022694.mp4");
            File source = new File("C:\\Users\\10174\\Desktop\\complete_mp4\\61AE5AC63EF43FAD37B65A8315ACA7C7_9318938.mp4");

            File target = new File("C:\\Users\\10174\\Desktop\\complete_mp4" + File.separator + source.getName().substring(0, source.getName().lastIndexOf(".")) + ".png");

            MultimediaObject object = new MultimediaObject(source);
            MultimediaInfo info = object.getInfo();
            long duration = info.getDuration() / 1000;
            System.out.println((float) duration);
            VideoInfo videoInfo = info.getVideo();
            VideoAttributes videoAt = new VideoAttributes();
            videoAt.setCodec("png");
            videoAt.setSize(videoInfo.getSize());
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("image2");
            attrs.setOffset((float) duration);//设置偏移位置，即开始转码位置（0.1秒）
            attrs.setDuration(0.01f);//设置转码持续时间（0.01秒）
            attrs.setVideoAttributes(videoAt);
            Encoder encoder = new Encoder();
            encoder.encode(object, target, attrs);
            if (target.exists()) {
                System.out.println("生成了图片");
                //target.delete();
            } else {
                System.out.println("未生成图片");
            }
        } catch (Exception e) {
            System.out.println("视频有错误：" + e);
        }
    }

    @org.junit.Test
    public void test1362() {
        try (FileInputStream e = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\files\\08af57cb46c03f8df1d2d894532b7f6d_79029.jpg");
             FileInputStream s = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\complete_files\\0DDAB6AF36CD072D951F9D306999847C_81783.jpg");
             FileInputStream e2 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\files\\E42FEF7B5EBCE01132430CCA66D04FEF_36491.jpg");
             FileInputStream e3 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\files\\F03C696048254A5045DE995C670CF7BA_110599.jpg");
             FileInputStream s2 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\complete_files\\0E00AE6DD46063B11C6EE03BADC3F911_317767.jpg");
             FileInputStream s3 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\complete_files\\5CF7B30501E7EEBBF3C83B8DC9F49FEE_193157.jpg")) {

            //File f2 = new File("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\files");
            File f2 = new File("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\complete_files");
            boolean is = false;
            for (String name : f2.list()) {
                try {
                    String path = f2.getPath() + File.separator + name;
                    FileInputStream fis = new FileInputStream(path);
                    byte[] b = new byte[1024];
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    int len = 0;
                    while ((len = fis.read(b)) != -1) {
                        out.write(b, 0, len);
                    }
                    byte[] szBuffer = out.toByteArray();
                    if ((szBuffer[0] & 0xff) == 255 && (szBuffer[1] & 0xff) == 216) {
                        //标准jpeg最后出现ff d9
                        if ((szBuffer[szBuffer.length - 2] & 0xff) == 255 && (szBuffer[szBuffer.length - 1] & 0xff) == 217) {
                            System.out.println(name + ":完整");
                        } else {

                            //有好多jpg最后被人为补了些字符也能打得开, 算作完整jpg, ffd9出现在近末端
                            //jpeg开始几个是特殊字节, 所以最后大于10就行了 从最后字符遍历
                            //有些文件会出现两个ffd9 后半部分ffd9才行
                            for (int i = szBuffer.length - 2; i > szBuffer.length / 2; --i) {
                                //检查有没有ffd9连在一起的
                                if ((szBuffer[i] & 0xff) == 255 && (szBuffer[i + 1] & 0xff) == 217) {
                                    System.out.println(name + ":完整");
                                    break;
                                }
                                if (i - 1 == szBuffer.length / 2) {
                                    System.out.println(name + ":不完整");
                                }
                            }

                        }
                    } else {
                        System.out.println(name + ":不完整");
                    }
                } catch (Exception e34) {

                }


            }
            /*
            byte[] bs = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = 0;
            while ((len = s2.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
            byte[] szBuffer = out.toByteArray();
            if (szBuffer[0] == -1 && szBuffer[szBuffer.length - 1] == -39) {
                System.out.println("true");
            }else {
                System.out.println("false");
            }
            out.close();
            */

        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    @org.junit.Test
    public void test3432() {

        try (FileInputStream e = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\files\\08af57cb46c03f8df1d2d894532b7f6d_79029.jpg");
             FileInputStream s = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\complete_files\\0DDAB6AF36CD072D951F9D306999847C_81783.jpg");
             FileInputStream e2 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\files\\E42FEF7B5EBCE01132430CCA66D04FEF_36491.jpg");
             FileInputStream e3 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\files\\F03C696048254A5045DE995C670CF7BA_110599.jpg");
             FileInputStream s2 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\complete_files\\0E00AE6DD46063B11C6EE03BADC3F911_317767.jpg");
             FileInputStream s3 = new FileInputStream("C:\\Users\\10174\\Desktop\\新建文件夹 (3)\\complete_files\\5CF7B30501E7EEBBF3C83B8DC9F49FEE_193157.jpg");) {

            byte[] bs = new byte[1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = 0;
            while ((len = e.read(bs)) != -1) {
                out.write(bs, 0, len);
            }


            byte[] bs2 = new byte[1024];
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            int len2 = 0;
            while ((len2 = s.read(bs2)) != -1) {
                out2.write(bs2, 0, len2);
            }

            byte[] bs3 = new byte[1024];
            ByteArrayOutputStream out3 = new ByteArrayOutputStream();
            int len3 = 0;
            while ((len3 = e2.read(bs3)) != -1) {
                out3.write(bs3, 0, len3);
            }

            byte[] bs4 = new byte[1024];
            ByteArrayOutputStream out4 = new ByteArrayOutputStream();
            int len4 = 0;
            while ((len4 = s2.read(bs4)) != -1) {
                out4.write(bs4, 0, len4);
            }

            BufferedImage eImg = ImageIO.read(e);
            BufferedImage sImg = ImageIO.read(s);
            BufferedImage eImg1 = ImageIO.read(e2);
            BufferedImage sImg2 = ImageIO.read(s2);
            BufferedImage eImg3 = ImageIO.read(e3);
            BufferedImage sImg3 = ImageIO.read(s3);
            eImg.getWidth();
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    public static boolean check(byte[] szBuffer) {
        if ((szBuffer[0] & 0xff) == 255 && (szBuffer[1] & 0xff) == 216) {
            //标准jpeg最后出现ff d9
            if ((szBuffer[szBuffer.length - 2] & 0xff) == 255 && (szBuffer[szBuffer.length - 1] & 0xff) == 217) {
                return true;
            } else {
                //有好多jpg最后被人为补了些字符也能打得开, 算作完整jpg, ffd9出现在近末端
                //jpeg开始几个是特殊字节, 所以最后大于10就行了 从最后字符遍历
                //有些文件会出现两个ffd9 后半部分ffd9才行
                for (int i = szBuffer.length - 2; i > szBuffer.length / 2; --i) {
                    //检查有没有ffd9连在一起的
                    if ((szBuffer[i] & 0xff) == 255 && (szBuffer[i + 1] & 0xff) == 217) {
                        return true;
                    }
                }
            }
        } else if ((szBuffer[0] & 0xff) == 137 && (szBuffer[1] & 0xff) == 80 && (szBuffer[2] & 0xff) == 78 && (szBuffer[3] & 0xff) == 71 && (szBuffer[4] & 0xff) == 13
                && (szBuffer[5] & 0xff) == 10 && (szBuffer[6] & 0xff) == 26 && (szBuffer[7] & 0xff) == 10) {
            //png检查
            if ((szBuffer[szBuffer.length - 5] & 0xff) == 68 && (szBuffer[szBuffer.length - 4] & 0xff) == 174 && (szBuffer[szBuffer.length - 3] & 0xff) == 66
                    && (szBuffer[szBuffer.length - 2] & 0xff) == 96 && (szBuffer[szBuffer.length - 1] & 0xff) == 130) {
                return true;
            }
            //有些情况最后多了些没用的字节
            for (int i = szBuffer.length - 1; i > szBuffer.length / 2; --i) {
                if ((szBuffer[i - 5] & 0xff) == 68 && (szBuffer[i - 4] & 0xff) == 174 && (szBuffer[i - 3] & 0xff) == 66
                        && (szBuffer[i - 2] & 0xff) == 96 && (szBuffer[i - 1] & 0xff) == 130) {
                    return true;
                }
            }
        } else if ((szBuffer[0] & 0xff) == 66 && (szBuffer[1] & 0xff) == 77)//bmp
        {
            //bmp长度
            //整数转成字符串拼接
            String str = Long.toHexString(szBuffer[5]) + Long.toHexString(szBuffer[5])
                    + Long.toHexString(szBuffer[5]) + Long.toHexString(szBuffer[5]);
            int iLength = Integer.parseInt(str, 16);
            if (iLength <= szBuffer.length) {
                return true;
            }
        } else if ((szBuffer[0] & 0xff) == 71 && (szBuffer[1] & 0xff) == 73 && (szBuffer[2] & 0xff) == 70 && (szBuffer[3] & 0xff) == 56)//gif
        {
            //标准gif 检查00 3B
            if ((szBuffer[szBuffer.length - 2] & 0xff) == 0 && (szBuffer[szBuffer.length - 1] & 0xff) == 59)
                return true;
            //检查含00 3B
            for (int i = szBuffer.length - 1; i > szBuffer.length / 2; --i) {
                if ((szBuffer[i] & 0xff) != 0) {
                    if ((szBuffer[i] & 0xff) == 59 && (szBuffer[i - 1] & 0xff) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkJpeg(byte[] szBuffer) {
        if (szBuffer == null || szBuffer.length == 0) {
            return false;
        }
        try {
            if ((szBuffer[0] & 0xff) == 255 && (szBuffer[1] & 0xff) == 216) {
                //标准jpeg最后出现ff d9
                if ((szBuffer[szBuffer.length - 2] & 0xff) == 255 && (szBuffer[szBuffer.length - 1] & 0xff) == 217) {
                    return true;
                } else {
                    //有好多jpg最后被人为补了些字符也能打得开, 算作完整jpg, ffd9出现在近末端
                    //jpeg开始几个是特殊字节, 所以最后大于10就行了 从最后字符遍历
                    //有些文件会出现两个ffd9 后半部分ffd9才行
                    for (int i = szBuffer.length - 2; i > szBuffer.length / 2; --i) {
                        //检查有没有ffd9连在一起的
                        if ((szBuffer[i] & 0xff) == 255 && (szBuffer[i + 1] & 0xff) == 217) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("checkJpeg错误：" + e);
        }
        return false;
    }

    public static boolean checkMp4(File source) {
        File target = null;
        boolean isComplete = false;
        try {
            target = new File("C:\\Users\\10174\\Desktop\\newp" + File.separator + source.getName().substring(0, source.getName().lastIndexOf(".")) + ".png");
            MultimediaObject object = new MultimediaObject(source);
            MultimediaInfo info = object.getInfo();
            long duration = info.getDuration() / 1000;
            VideoInfo videoInfo = info.getVideo();
            VideoAttributes videoAt = new VideoAttributes();
            videoAt.setCodec("png");
            videoAt.setSize(videoInfo.getSize());
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("image2");
            attrs.setOffset((float) (duration / 5) * 3);//设置偏移位置，即开始转码位置（0.1秒）
            attrs.setDuration(0.01f);//设置转码持续时间（0.01秒）
            attrs.setVideoAttributes(videoAt);
            Encoder encoder = new Encoder();
            encoder.encode(object, target, attrs);
        } catch (Exception e) {
            System.out.println("视频有错误：" + e);
        } finally {
            if (Objects.nonNull(target) && target.exists()) {
                // target.delete();
                isComplete = true;
            }
        }
        return isComplete;
    }

    @org.junit.Test
    public void test1352() {
        String directoryPath = "E:";
        String path = "E:\\taskfile";
        File file = new File(path);
        for (String pathName : file.list()) {
            File directory = new File(file.getPath() + File.separator + pathName);
            if (directory.isDirectory()) {
                if ("mp4".equals(directory.getName())) {
                    String completeMp4 = directoryPath + File.separator + "complete_mp4";
                    String unCompleteMp4 = directoryPath + File.separator + "uncomplete_mp4";
                    for (String nodeName : directory.list()) {
                        try (FileInputStream fileInputStream = new FileInputStream(directory.getPath() + File.separator + nodeName);
                             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                            byte[] bs = new byte[1024];
                            int len;
                            while ((len = fileInputStream.read(bs)) != -1) {
                                out.write(bs, 0, len);
                            }
                            byte[] szBuffer = out.toByteArray();
                            String newPath;
                            if (checkMp4(new File(directory.getPath() + File.separator + nodeName))) {
                                newPath = completeMp4 + File.separator + nodeName;
                            } else {
                                newPath = unCompleteMp4 + File.separator + nodeName;
                            }
                            FileUtils.writeByteArrayToFile(new File(newPath), szBuffer);
                        } catch (IOException e) {
                            System.out.printf("io读写错误：" + e);
                        }
                    }
                } else {
                    String completePicture = directoryPath + File.separator + "complete_p";
                    String unCompletePicture = directoryPath + File.separator + "uncomplete_p";
                    for (String nodeName : directory.list()) {
                        try (FileInputStream fileInputStream = new FileInputStream(directory.getPath() + File.separator + nodeName);
                             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                            byte[] bs = new byte[1024];
                            int len;
                            while ((len = fileInputStream.read(bs)) != -1) {
                                out.write(bs, 0, len);
                            }
                            byte[] szBuffer = out.toByteArray();
                            String suffix = nodeName.substring(nodeName.lastIndexOf(".") + 1);
                            if (StringUtils.equalsIgnoreCase(suffix, "jpg") || StringUtils.equalsIgnoreCase(suffix, "jpeg")) {
                                String newPath;
                                if (checkJpeg(szBuffer)) {
                                    newPath = completePicture + File.separator + nodeName;
                                } else {
                                    newPath = unCompletePicture + File.separator + nodeName;
                                }
                                FileUtils.writeByteArrayToFile(new File(newPath), szBuffer);
                            }
                        } catch (IOException e) {
                            System.out.printf("io读写错误：" + e);
                        }
                    }
                }
            }
        }
    }

    @org.junit.Test
    public void test35() {
        String path = "E:";
        String allPath = path + File.separator + "all_m";
        String textPath = path + File.separator + "9.01-12.1 mp4.txt";
        String cPath = path + File.separator + "complete_m";
        String uncPath = path + File.separator + "uncomplete_m";
        try {
            String text = FileUtils.readFileToString(new File(textPath), StandardCharsets.UTF_8);
            JSONObject jsonObject = JSONObject.parseObject(text);
            JSONObject jsonNode = (JSONObject) jsonObject.get("hits");
            JSONArray jsonArray = (JSONArray) jsonNode.get("hits");
            jsonArray.parallelStream().forEach(e -> {
                File allFile = null;
                try {
                    JSONObject eJson = (JSONObject) e;
                    JSONObject eJsonObject = (JSONObject) eJson.get("_source");
                    String fileUrl = eJsonObject.get("file_url").toString();
                    if (StringUtils.isNotBlank(fileUrl) && "mp4".equals(fileUrl.substring(fileUrl.lastIndexOf(".") + 1))) {
                        URL url = new URL(fileUrl);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        int status = conn.getResponseCode();
                        boolean redirect = false;
                        if (status != HttpURLConnection.HTTP_OK) {
                            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                                    || status == HttpURLConnection.HTTP_MOVED_PERM
                                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                                redirect = true;
                        }
                        String newFilePath = allPath + File.separator + fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
                        if (redirect) {
                            String newFileUrl = conn.getHeaderField("Location");
                            URL newUrl = new URL(newFileUrl);
                            conn = (HttpURLConnection) newUrl.openConnection();
                        }
                        try (DataInputStream dataInputStream = new DataInputStream(conn.getInputStream());
                             FileOutputStream allOutputStream = new FileOutputStream(newFilePath);
                             ByteArrayOutputStream outputArray = new ByteArrayOutputStream()) {
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = dataInputStream.read(buffer)) > 0) {
                                outputArray.write(buffer, 0, length);
                            }
                            byte[] bytes = outputArray.toByteArray();
                            allOutputStream.write(bytes);
                            allFile = new File(newFilePath);
                            if (allFile.exists()) {
                                if (checkMp4(allFile)) {
                                    FileUtils.writeByteArrayToFile(new File(cPath + File.separator + allFile.getName()), bytes);
                                } else {
                                    FileUtils.writeByteArrayToFile(new File(uncPath + File.separator + allFile.getName()), bytes);
                                }
                            }
                        } catch (Exception ee) {
                            System.out.println("ee错误：" + ee);
                        }
                    }
                } catch (Exception eee) {
                    System.out.println("eee错误：" + eee);
                } finally {
                    if (Objects.nonNull(allFile) && allFile.exists()) {
                        allFile.delete();
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("e错误：" + e);
        }
    }

    @org.junit.Test
    public void test45() throws Exception {
        //String fileUrl = "https://upos-sz-mirrorkodo.bilivideo.com/upgcxcode/61/88/456828861/456828861-1-208.mp4?e=ig8euxZM2rNcNbNBhWdVhwdlhbU1hwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1639037422&gen=playurlv2&os=kodobv&oi=1950233169&trid=b84b5204960647ff9ffbd90a8278e643T&platform=html5&upsig=9f4c479f02822af2787a5cb4c2fa5e3f&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0&bvc=vod&nettype=0&bw=251976&orderid=0,1&logo=80000000";
        //String fileUrl = "https://www.baidu.com/";
        //String fileUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0829%2Fb871e1addf5f8e96f3b390ece2b2da0d.jpg&refer=http%3A%2F%2Ffile02.16sucai.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641614340&t=4bf22539cef752215520967e281b0e6f";
        //String fileUrl = "http://tmp.oss.baticq.com/0D6A4B6A9B4B70C5D83E75E49C1740D3_949894.mp4?auth_key=1639030727-7k0XIq1LMoY-0-c006b3553d3c2cd1ceb5cbb5911401f1";

        String fileUrl = "https://storage.baaaat.com/guest/stores/bat-tmp/0D6A4B6A9B4B70C5D83E75E49C1740D3_949894.mp4";
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int status = conn.getResponseCode();
        boolean redirect = false;
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }
        String path = "E:";
        String allPath = path + File.separator + "text111";
        String newFilePath = allPath + File.separator + fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        if (redirect) {
            String newFileUrl = conn.getHeaderField("Location");
            URL newUrl = new URL(newFileUrl);
            conn = (HttpURLConnection) newUrl.openConnection();
        }
        try (InputStream in = conn.getInputStream();
             FileOutputStream out = new FileOutputStream(newFilePath)) {
            byte[] buffer = new byte[2048];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (Exception e) {
            System.err.println("错误：" + e);
        }
    }

    @org.junit.Test
    public void test4587() {
        String path = "E:";
        String allPath = path + File.separator + "uncomplete_m2";
        String cPath = path + File.separator + "complete_m";
        String uncPath = path + File.separator + "uncomplete_m";
        File file = new File(allPath);
        for (String name : file.list()) {
            String filePath = allPath + File.separator + name;
            File allFile = new File(filePath);
            try (FileInputStream dataInputStream = new FileInputStream(allFile);
                 ByteArrayOutputStream outputArray = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0) {
                    outputArray.write(buffer, 0, length);
                }
                byte[] bytes = outputArray.toByteArray();
                if (allFile.exists()) {
                    if (checkMp4(allFile)) {
                        FileUtils.writeByteArrayToFile(new File(cPath + File.separator + allFile.getName()), bytes);
                    } else {
                        FileUtils.writeByteArrayToFile(new File(uncPath + File.separator + allFile.getName()), bytes);
                    }
                }
            } catch (Exception ee) {
                System.out.println("ee错误：" + ee);
            }
        }
    }



    @org.junit.Test
    public void test425() throws Exception {
        //String fileUrl = "https://upos-sz-mirrorkodo.bilivideo.com/upgcxcode/61/88/456828861/456828861-1-208.mp4?e=ig8euxZM2rNcNbNBhWdVhwdlhbU1hwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1639037422&gen=playurlv2&os=kodobv&oi=1950233169&trid=b84b5204960647ff9ffbd90a8278e643T&platform=html5&upsig=9f4c479f02822af2787a5cb4c2fa5e3f&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0&bvc=vod&nettype=0&bw=251976&orderid=0,1&logo=80000000";
        //String fileUrl = "https://www.baidu.com/";
        //String fileUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0829%2Fb871e1addf5f8e96f3b390ece2b2da0d.jpg&refer=http%3A%2F%2Ffile02.16sucai.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1641614340&t=4bf22539cef752215520967e281b0e6f";
        //String fileUrl = "http://tmp.oss.baticq.com/0D6A4B6A9B4B70C5D83E75E49C1740D3_949894.mp4?auth_key=1639030727-7k0XIq1LMoY-0-c006b3553d3c2cd1ceb5cbb5911401f1";

        String fileUrl = "https://storage.baaaat.com/guest/stores/bat-tmp/0D6A4B6A9B4B70C5D83E75E49C1740D3_949894.mp4";
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int status = conn.getResponseCode();
        boolean redirect = false;
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }
        String path = "E:";
        String allPath = path + File.separator + "text111";
        String newFilePath = allPath + File.separator + fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        if (redirect) {
            String newFileUrl = conn.getHeaderField("Location");
            URL newUrl = new URL(newFileUrl);
            conn = (HttpURLConnection) newUrl.openConnection();
        }
        try (InputStream in = conn.getInputStream();
             FileOutputStream out = new FileOutputStream(newFilePath)) {
            byte[] buffer = new byte[2048];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (Exception e) {
            System.err.println("错误：" + e);
        }
    }


    public void test488(){
    }
}
