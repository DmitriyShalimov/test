package com.minexsystems;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class CacheHashMap<K, V> implements CacheMap<K, V> {

    private Map<K, Pair<Long, V>> map = new HashMap<>();
    private long timeToLive;

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public V put(K key, V value) {
        clearExpired();
        Pair<Long, V> oldValue = map.put(key, new Pair<>(Clock.getTime(), value));
        return oldValue != null ? oldValue.getValue() : null;
    }

    public void clearExpired() {
        Map<K, Pair<Long, V>> result = new HashMap<>();
        for (Map.Entry<K, Pair<Long, V>> e : map.entrySet()) {
            if (e.getValue().getKey() > Clock.getTime() - timeToLive) {
                result.put(e.getKey(), e.getValue());
            }
        }
        map = result;
    }

    public void clear() {
        map.clear();
    }

    public boolean containsKey(K key) {
        clearExpired();
        return map.containsKey(key);
    }

    public boolean containsValue(V value) {
        clearExpired();
        for (Pair<Long, V> pair : map.values()) {
            if (pair.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        clearExpired();
        if (map.get(key) == null) {
            return null;
        } else {
            return map.get(key).getValue();
        }
    }

    public boolean isEmpty() {
        clearExpired();
        return map.isEmpty();
    }

    public V remove(K key) {
        clearExpired();
        Pair<Long, V> pair = map.remove(key);
        if (pair != null) {
            return pair.getValue();
        }
        return null;
    }

    public int size() {
        clearExpired();
        return map.size();
    }
}
