package com.distributeddb;

import com.distributeddb.storage.KeyValueStore;

public class App {

    public static void main(String[] args) {

        KeyValueStore store =
                new KeyValueStore("database.wal");

        store.put("name", "Anjali");
        store.put("language", "Java");

        System.out.println("Name: " + store.get("name"));
        System.out.println("Language: " + store.get("language"));

        store.delete("language");

        System.out.println("Language: " + store.get("language"));
    }
}