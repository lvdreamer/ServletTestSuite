package com.lvdreamer.algorithm;

/**
 * 9. 回文数isPalindrome
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0009 {
    public static void main(String[] args) {
        boolean res = new Question0009().isPalindrome(12321);
        System.out.println(res);
        res=new Question0009().isPalindrome(1245);
        System.out.println(res);

    }

    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0) return false;
        if (x % 10 == 0) return false;
        int right = 0;
        while (x > right) {
            right = right * 10 + x % 10;
            x = x / 10;
        }
        return x == right || x == right / 10;
    }

}
