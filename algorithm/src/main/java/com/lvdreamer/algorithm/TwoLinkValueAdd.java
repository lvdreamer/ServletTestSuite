package com.lvdreamer.algorithm;

import java.io.IOException;
import java.util.Stack;

public class TwoLinkValueAdd {
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

    public static ListNode stringToListNode(String input) {
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

    public static void main(String[] args) throws IOException {
        ListNode l1 = stringToListNode("[7,2,4,3]");
        ListNode l2 = stringToListNode("[5,6,4]");
        ListNode ret = new TwoLinkValueAdd().addTwoNumbers(l1, l2);

        String out = listNodeToString(ret);

        System.out.print(out);
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

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}