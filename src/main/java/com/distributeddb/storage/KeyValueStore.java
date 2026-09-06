package com.distributeddb.storage;

import java.util.HashMap;
import java.util.Map;

public class KeyValueStore {

    private final Map<String, String> data = new HashMap<>();

    public void put(String key, String value) {
        data.put(key, value);
    }

    public String get(String key) {
        return data.get(key);
    }

    public void delete(String key) {
        data.remove(key);
    }
}