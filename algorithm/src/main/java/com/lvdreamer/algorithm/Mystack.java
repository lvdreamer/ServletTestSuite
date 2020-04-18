package com.lvdreamer.algorithm;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

class MyStack {
    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(2);
        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();
    }

    private Queue<Integer> dataQueue = new LinkedList<Integer>();
    private Queue<Integer> tmpQueue = new LinkedList<Integer>();

    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        tmpQueue.add(x);
        while (!dataQueue.isEmpty()) {
            tmpQueue.add(dataQueue.poll());
        }
        while (!tmpQueue.isEmpty())

        {
            dataQueue.add(tmpQueue.poll());
        }

    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return Optional.ofNullable(dataQueue.poll()).orElse(0);
    }

    /**
     * Get the top element.
     */
    public int top() {
        return Optional.ofNullable(dataQueue.peek()).orElse(0);
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return dataQueue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */