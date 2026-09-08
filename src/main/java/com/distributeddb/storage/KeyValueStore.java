package com.distributeddb.storage;

import java.util.HashMap;
import java.util.Map;

public class KeyValueStore {

    private final Map<String, String> data = new HashMap<>();
    private final WriteAheadLog wal;

    public KeyValueStore(String walFilePath) {
        this.wal = new WriteAheadLog(walFilePath);
        recover();
    }

    public void put(String key, String value) {

        wal.append("PUT|" + key + "|" + value);

        data.put(key, value);
    }

    public String get(String key) {
        return data.get(key);
    }

    public void delete(String key) {

        wal.append("DELETE|" + key);

        data.remove(key);
    }

    private void recover() {

        for (String operation : wal.readAll()) {
            apply(operation);
        }
    }

    private void apply(String operation) {

        String[] parts = operation.split("\\|", -1);

        if (parts[0].equals("PUT")) {

            String key = parts[1];
            String value = parts[2];

            data.put(key, value);

        } else if (parts[0].equals("DELETE")) {

            String key = parts[1];

            data.remove(key);
        }
    }
}