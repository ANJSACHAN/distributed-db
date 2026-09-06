package com.distributeddb;

import com.distributeddb.storage.KeyValueStore;

public class App {

    public static void main(String[] args) {

        KeyValueStore store = new KeyValueStore();

        // PUT
        store.put("name", "Anjali");
        store.put("language", "Java");

        // GET
        System.out.println("Name: " + store.get("name"));
        System.out.println("Language: " + store.get("language"));

        // DELETE
        store.delete("language");

        // GET after DELETE
        System.out.println("Language: " + store.get("language"));
    }
}