package com.lvdreamer.algorithm;

/**
 * 7. 整数反转
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0007 {
    public static void main(String[] args) {
        int res = new Question0007().reverse(12245);
        System.out.println(res);

    }

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return (int) result == result ? (int) result : 0;
    }

}
