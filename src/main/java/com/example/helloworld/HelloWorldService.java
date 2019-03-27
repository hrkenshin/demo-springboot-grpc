package com.example.helloworld;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class HelloWorldService extends GreeterGrpc.GreeterImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldService.class);

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        LOGGER.info("server received {}", request);

        String message = "Hello " + request.getName() + "!";
        HelloResponse response = HelloResponse.newBuilder().setMessage(message).build();

        LOGGER.info("server responded {}", response);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
