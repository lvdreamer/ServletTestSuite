package com.lvdreamer.algorithm;

/**
 * 14. 最长公共前缀
 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0014 {

    public static void main(String[] args) {
        String res = new Question0014().longestCommonPrefix(new String[]{"flower","flow","flight"});
        System.out.println(res);

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefixStr = strs[0];
        for (String str : strs) {
            while (!str.startsWith(prefixStr)) {
                if (prefixStr.length() == 1) {
                    return "";
                }
                prefixStr = prefixStr.substring(0, prefixStr.length() - 1);
            }
        }
        return prefixStr;
    }
}
