package com.example.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HelloWorldClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldClient.class);

    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565).usePlaintext().build();

        greeterBlockingStub = GreeterGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        LOGGER.info("client sending {}", request);

        HelloResponse response = greeterBlockingStub.sayHello(request);
        LOGGER.info("client received {}", response);

        return response.getMessage();
    }
}
