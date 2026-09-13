package com.distributeddb.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DatabaseClient {

    public static void main(String[] args) {

        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress("localhost", 50051)
                        .usePlaintext()
                        .build();

        DatabaseServiceGrpc.DatabaseServiceBlockingStub stub =
                DatabaseServiceGrpc.newBlockingStub(channel);

        PutRequest request = PutRequest.newBuilder()
                .setKey("name")
                .setValue("Anjali")
                .build();

        PutResponse response = stub.put(request);

        System.out.println("PUT success: " + response.getSuccess());

        channel.shutdown();
    }
}