package com.distributeddb.storage;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteAheadLogTest {

    @Test
    void shouldAppendOperationToLog() throws Exception {

        Path tempFile = Files.createTempFile("database", ".wal");

        WriteAheadLog wal = new WriteAheadLog(tempFile.toString());

        wal.append("PUT|name|Anjali");

        String content = Files.readString(tempFile);

        assertEquals("PUT|name|Anjali" + System.lineSeparator(), content);

        Files.deleteIfExists(tempFile);
    }
}