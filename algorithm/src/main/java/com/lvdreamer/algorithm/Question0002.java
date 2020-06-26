package com.lvdreamer.algorithm;

import java.io.IOException;
import java.util.Stack;

/**
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Question0002 {

    public static void main(String[] args) throws IOException {
        Question0002 question0002 = new Question0002();
        ListNode l1 = question0002.stringToListNode("[7,2,4,3]");
        ListNode l2 = question0002.stringToListNode("[5,6,4]");
        ListNode ret = new Question0002().addTwoNumbers(l1, l2);
        String out = listNodeToString(ret);
        System.out.print(out);
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        Stack<Integer> l1Stack = new Stack();
        Stack<Integer> l2Stack = new Stack();

        ListNode lastListNode = l1;
        do {
            l1Stack.push(lastListNode.val);
        } while (null != (lastListNode = lastListNode.next));
        lastListNode = l2;
        do {
            l2Stack.push(lastListNode.val);
        } while (null != (lastListNode = lastListNode.next));
        int carry = 0;
        ListNode header = null;
        while (!l1Stack.empty() || !l2Stack.empty() || carry > 0) {
            int sum = carry;
            sum += l1Stack.isEmpty() ? 0 : l1Stack.pop();
            sum += l2Stack.isEmpty() ? 0 : l2Stack.pop();
            ListNode node = new ListNode(sum % 10);
            node.next = header;
            header = node;
            carry = sum / 10;
        }
        return header;
    }

}


