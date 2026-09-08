package com.distributeddb;

import com.distributeddb.storage.KeyValueStore;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void shouldStoreAndRetrieveValue() throws Exception {

        Path tempFile = Files.createTempFile("database", ".wal");

        KeyValueStore store =
                new KeyValueStore(tempFile.toString());

        store.put("name", "Anjali");

        assertEquals("Anjali", store.get("name"));

        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldReturnNullForMissingKey() throws Exception {

        Path tempFile = Files.createTempFile("database", ".wal");

        KeyValueStore store =
                new KeyValueStore(tempFile.toString());

        assertNull(store.get("name"));

        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldDeleteValue() throws Exception {

        Path tempFile = Files.createTempFile("database", ".wal");

        KeyValueStore store =
                new KeyValueStore(tempFile.toString());

        store.put("name", "Anjali");
        store.delete("name");

        assertNull(store.get("name"));

        Files.deleteIfExists(tempFile);
    }

    @Test
void shouldRecoverDataAfterRestart() throws Exception {

    Path tempFile = Files.createTempFile("database", ".wal");

    // First database instance
    KeyValueStore firstStore =
            new KeyValueStore(tempFile.toString());

    firstStore.put("name", "Anjali");
    firstStore.put("language", "Java");
    firstStore.delete("language");

    // Simulate application restart
    KeyValueStore secondStore =
            new KeyValueStore(tempFile.toString());

    assertEquals("Anjali", secondStore.get("name"));
    assertNull(secondStore.get("language"));

    Files.deleteIfExists(tempFile);
}
}