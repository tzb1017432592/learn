package com.example.leetcode.hot;

import java.util.*;

public class LRUCache {
    LinkedList<Integer> cache;
    Map<Integer, Integer> map;
    int limit;

    public LRUCache(int capacity) {
        limit = capacity;
        cache = new LinkedList<>();
        map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Integer result = map.get(key);
        put(key, result);
        return result;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (cache.size() >= limit) {
                map.remove(cache.removeLast());
            }
        } else {
            if (cache.size() > 0) {
                Integer k=key;
                cache.remove(k);
            }
        }
        map.put(key, value);
        cache.addFirst(key);
    }
}
