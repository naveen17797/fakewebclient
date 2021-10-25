package io.github.naveen17797.fakewebclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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


    @Test
    void testWhenNoMockProvidedShouldThrowException() {
        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .build();


        assertThrows(RuntimeException.class, () -> {
            client.method(HttpMethod.GET)
                    .uri(URI.create("https://google.com")).exchange().block()
                    .bodyToMono(String.class).block();
        });
    }


    @Test
    void testShouldBeAbleToCompareByHeaders() {
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .forUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .withRequestHeader("foo", "bar")
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();


        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("test",

                client
                        .method(HttpMethod.GET)
                        .uri(URI.create("https://google.com"))
                        .header("foo", "bar")
                        .exchange()
                        .block()
                        .bodyToMono(String.class).block());

    }


    @Test
    void testWhenHeaderNotMatchShouldThrowException() {
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .forUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .withRequestHeader("foo", "bar")
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();

        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertThrows(RuntimeException.class, () -> {
            client
                    .method(HttpMethod.GET)
                    .uri(URI.create("https://google.com"))
                    .exchange()
                    .block()
                    .bodyToMono(String.class).block();
        });
    }


    @Test
    void testShouldBeAbleToMockResponseHeader() {

        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .forUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .withResponseHeader("foo", "bar")
                .build();

        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("bar",

                client
                        .method(HttpMethod.GET)
                        .uri(URI.create("https://google.com"))
                        .exchange()
                        .block()
                        .headers()
                        .header("foo")
                        .get(0));
    }

}
