package com.example.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Base64;

/**
 * @author tianzhoubing
 * @date 2021/11/14 23:16
 * @description
 **/
public class Test {
    @org.junit.Test
    public void test(){
        char c1 = '5';
        char c = '0';
        int i = c1 - c;
        System.out.println(i);
    }

    @org.junit.Test
    public void test343(){
        String encoded = "SFRUUC8xLjEgMjAwIE9LDQpTZXJ2ZXI6IFRlbmdpbmUNCkRhdGU6IFRodSwgMDQgTm92IDIwMjEgMDI6MzY6MTMgR01UDQpDb250ZW50LVR5cGU6IGFwcGxpY2F0aW9uL2pzb247Y2hhcnNldD1VVEYtOA0KQ29udGVudC1MZW5ndGg6IDU2NA0KQ29ubmVjdGlvbjogY2xvc2UNCkNhY2hlLUNvbnRyb2w6IG5vLWNhY2hlDQpwcmFnbWE6IG5vLWNhY2hlDQp4LWFtLXNpZ246IDA5MTQ3OWY3ZDdiODk5Yzg4OGYwMTBlZDQ0NzIyMWE3ZThhZDRiNzcNCngtYW0taWQ6IGFtZGMwMTEwMjAxNDUxMzEuY2VudGVyLm5hNjIwXzE2MzU5OTMzNzM4ODNfNDE0MTYyOTU1DQp4LWFtLWNvZGU6IDEwMDANCg0KZXlKaFkyTmxjM05RYjJsdWRDSTZJbWhyWW00dWJtVjBYemd4TURBd01GODBJaXdpWTJGeWNtbGxjaUk2SW1oclltNHVibVYwSWl3aVkyeHBaVzUwU1hOd0lqb2lhR3RpYmk1dVpYUWlMQ0pqYjJSbElqb3hNREF3TENKamRpSTZNQ3dpWkc1eklqcGJleUpoYVhOc1pYTWlPbHQ3SW1OMGJ5STZNVEF3TURBc0ltaGxZWEowWW1WaGRDSTZORFV3TURBc0luQnZjblFpT2pRME15d2ljSEp2ZEc5amIyd2lPaUpvZEhSd01pSXNJbkIxWW14cFkydGxlU0k2SW1GamN5SXNJbkpsZEhKNUlqb3hMQ0p5ZEc4aU9qRXdNREF3TENKeWRIUWlPaUl3Y25SMEluMWRMQ0pvYjNOMElqb2liM0poYm1kbExXUmpMbUZzYVdKaFltRXVZMjl0SWl3aWFYQnpJanBiSWpFNU9DNHhNUzR4TXpZdU5UQWlYU3dpYzJGbVpVRnBjMnhsY3lJNkltaDBkSEJ6SWl3aWMzUnlZWFJsWjJsbGN5STZXMTBzSW5SMGJDSTZNekF3TENKMlpYSnphVzl1SWpvaU1DSjlYU3dpYVhBaU9pSTJNUzQ1TXk0eE56a3VNVFV6SWl3aWRXNXBkQ0k2SW5WdWVtSnRhWGdpTENKMWRHUnBaQ0k2SWxsV2NFNU9ORWt2WlhOalJFRkRSRWRoYmxsWmNHOHhlaUo5";
        String newStr="";
        if (StringUtils.isNotBlank(encoded)){
            newStr = encoded.substring(0, 300);
        }
        byte[] decoded = Base64.getDecoder().decode(newStr);
        String s = new String(decoded);
        if (StringUtils.containsIgnoreCase(s,"json")){
            byte[] decode = Base64.getDecoder().decode(encoded);
            String str = new String(decode);
            String[] split = str.split("\n");
            String s1 = split[split.length - 1];
            if (StringUtils.contains(s1,"{")){
                JSONObject jsonObject = JSONObject.parseObject(s1);
            }else {
                byte[] decode2 = Base64.getDecoder().decode(s1);
                System.out.println(new String(decode2));
            }
        }

    }

    @org.junit.Test
    public void test34(){
        String str="SFRUUC8xLjEgMjAwIE9LDQpTZXJ2ZXI6IFRlbmdpbmUNCkRhdGU6IFRodSwgMDQgTm92IDIwMjEgMDI6MzY6MTMgR01UDQpDb250ZW50LVR5cGU6IGFwcGxpY2F0aW9uL2pzb247Y2hhcnNldD1VVEYtOA0KQ";
        System.out.println(str.length());
    }
}
