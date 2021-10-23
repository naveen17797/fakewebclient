package io.github.naveen17797.fakewebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FakewebclientApplication {

    private WebClient.Builder builder;

    void foo() {
        builder.build().post().exchange();
    }

    public static void main(String[] args) {
        SpringApplication.run(FakewebclientApplication.class, args);

    }

}
