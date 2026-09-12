package com.distributeddb.grpc;

import com.distributeddb.storage.KeyValueStore;
import io.grpc.stub.StreamObserver;

public class DatabaseServiceImpl
        extends DatabaseServiceGrpc.DatabaseServiceImplBase {

    private final KeyValueStore store;

    public DatabaseServiceImpl(KeyValueStore store) {
        this.store = store;
    }

    @Override
public void put(
        PutRequest request,
        StreamObserver<PutResponse> responseObserver) {

    store.put(request.getKey(), request.getValue());

    PutResponse response = PutResponse.newBuilder()
            .setSuccess(true)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}

@Override
public void get(
        GetRequest request,
        StreamObserver<GetResponse> responseObserver) {

    String value = store.get(request.getKey());

    GetResponse response = GetResponse.newBuilder()
            .setValue(value == null ? "" : value)
            .setFound(value != null)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}

@Override
public void delete(
        DeleteRequest request,
        StreamObserver<DeleteResponse> responseObserver) {

    store.delete(request.getKey());

    DeleteResponse response = DeleteResponse.newBuilder()
            .setSuccess(true)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}
}