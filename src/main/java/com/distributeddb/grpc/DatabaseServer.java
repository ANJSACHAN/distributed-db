package com.distributeddb.grpc;

import com.distributeddb.storage.KeyValueStore;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class DatabaseServer {

    public static void main(String[] args)
            throws IOException, InterruptedException {

        KeyValueStore store =
                new KeyValueStore("database.wal");

        DatabaseServiceImpl service =
                new DatabaseServiceImpl(store);

        Server server = ServerBuilder
                .forPort(50051)
                .addService(service)
                .build();

        server.start();

        System.out.println(
                "Database server started on port 50051"
        );

        server.awaitTermination();
    }
}