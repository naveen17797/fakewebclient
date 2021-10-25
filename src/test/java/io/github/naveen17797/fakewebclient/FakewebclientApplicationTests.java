package io.github.naveen17797.fakewebclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FakewebclientApplicationTests {




    @Test
    void testShouldBeAbleToUseFakeWebClientForAssertions() {

        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .forUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();

        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("test", client.method(HttpMethod.GET).uri(URI.create("https://google.com")).exchange().block()
                .bodyToMono(String.class).block());


    }

}
