package com.lvdreamer.algorithm;

import java.util.Stack;

/**
 * 20. 有效的括号
 * <p>
 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-parentheses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0020 {

    public static void main(String[] args) {
        boolean res = new Question0020().isValid("()[]{}");
        System.out.println(res);

    }

    public boolean isValid(String s) {
        char[] charArr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : charArr) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char cStack = stack.pop();
                if (c == ')' && cStack != '(' ||
                        c == ']' && cStack != '[' ||
                        c == '}' && cStack != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();}
}
