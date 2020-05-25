package com.lvdreamer.algorithm;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0005 {
    public static void main(String[] args) {
        String res = new Question0005().longestPalindrome("babad");
        System.out.println(res);

    }

    public String longestPalindrome(String sourceStr) {
        if (null == sourceStr || sourceStr.length() == 0) {
            return "";
        }
        int strLegth = sourceStr.length();
        int maxStart = 0;
        int maxLen = 0;
        for (int i = 0; i < strLegth; i++) {
            int left = i - 1;
            int rigth = i + 1;
            int curLen = 1;
            char middle = sourceStr.charAt(i);
            while (left >= 0 && sourceStr.charAt(left) == middle) {
                left--;
                curLen++;
            }
            while (rigth < strLegth && sourceStr.charAt(rigth) == middle) {
                rigth++;
                curLen++;
            }
            while (left >= 0 && rigth < strLegth && sourceStr.charAt(left) == sourceStr.charAt(rigth)) {
                left--;
                rigth++;
                curLen += 2;
            }
            if (curLen > maxLen) {
                maxLen = curLen;
                maxStart = left;
            }
        }
        return sourceStr.substring(maxStart + 1, maxStart + maxLen + 1);
    }
}
