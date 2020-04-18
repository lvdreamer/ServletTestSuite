package com.lvdreamer.algorithm;

import java.util.HashMap;

public class LRUCache {
    private class CacheNode {
        int key;
        int value;
        CacheNode pre;
        CacheNode next;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, CacheNode> map = new HashMap<>();
    private CacheNode head;
    private CacheNode tail;

    public int get(int key) {
        CacheNode node = map.get(key);
        if (node != null) {
            if (node != head) {
                removeMiddleAndLast(node);
                bringToFront(node);
            }
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        CacheNode node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            if (node == head) return;
            removeMiddleAndLast(node);
        } else {
            node = new CacheNode(key, value);
            map.put(node.key, node);
        }
        if (head == null) {
            head = tail = node;
        } else {
            bringToFront(node);
        }
        if (map.size() > capacity) {
            map.remove(tail.key);
            tail.pre.next = null;
            tail = tail.pre;
        }
    }

    private void removeMiddleAndLast(CacheNode node) {
        if (node == head) return;
        node.pre.next = node.next;
        if (node == tail) {
            tail = tail.pre;
        } else {
            node.next.pre = node.pre;
        }
    }

    private void bringToFront(CacheNode node) {
        node.next = head;
        node.pre = null;
        head.pre = node;
        head = node;
    }
}

