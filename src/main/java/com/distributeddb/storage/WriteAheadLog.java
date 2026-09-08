package com.distributeddb.storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteAheadLog {

    private final String filePath;

    public WriteAheadLog(String filePath) {
        this.filePath = filePath;
    }

    public void append(String operation) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, true))) {

            writer.write(operation);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Failed to write to WAL", e);
        }
    }

    public List<String> readAll() {

        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            return List.of();
        }

        try {
            return Files.readAllLines(path);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read WAL", e);
        }
    }
}