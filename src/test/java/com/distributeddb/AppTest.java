package com.distributeddb;

import com.distributeddb.storage.KeyValueStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void shouldStoreAndRetrieveValue() {
        KeyValueStore store = new KeyValueStore();

        store.put("name", "Anjali");

        assertEquals("Anjali", store.get("name"));
    }

    @Test
    void shouldReturnNullForMissingKey() {
        KeyValueStore store = new KeyValueStore();

        assertNull(store.get("name"));
    }

    @Test
    void shouldDeleteValue() {
        KeyValueStore store = new KeyValueStore();

        store.put("name", "Anjali");
        store.delete("name");

        assertNull(store.get("name"));
    }
}