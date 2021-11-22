package com.example.leetcode.str;

/**
 * @author tianzhoubing
 * @date 2021/11/22 21:51
 * @description
 **/
public class IndexOf {
    public static int strStr(String haystack, String needle) {
        if (haystack == null) {
            return -1;
        }
        int hLength = haystack.length();
        int nLength = needle.length();
        if (hLength == 0 && nLength == 0) {
            return 0;
        }
        for (int i = 0; i < hLength; i++) {
            int hStart = i;
            int nStart = 0;
            if (hLength - i < nLength) {
                return -1;
            }
            while (hStart < hLength && nStart < nLength && haystack.charAt(hStart) == needle.charAt(nStart)) {
                hStart++;
                nStart++;
            }
            if (nStart == nLength) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        strStr("", "");
    }
}
