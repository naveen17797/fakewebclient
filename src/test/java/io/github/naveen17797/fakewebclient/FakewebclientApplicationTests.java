package io.github.naveen17797.fakewebclient;

import io.github.naveen17797.fakewebclient.exceptions.ResponseNotDelieverdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FakewebclientApplicationTests {


    private FakeWebClientBuilder fakeWebClientBuilder;

    @BeforeEach
    void beforeEach() {
        this.fakeWebClientBuilder = FakeWebClientBuilder.useDefaultWebClientBuilder();
    }

    @Test
    void testShouldBeAbleToUseFakeWebClientForAssertions() {

        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();

        WebClient client =
                fakeWebClientBuilder
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("test", client.method(HttpMethod.GET).uri(URI.create("https://google.com")).exchange().block()
                .bodyToMono(String.class).block());

        Assertions.assertTrue(this.fakeWebClientBuilder.assertAllResponsesDispatched());

    }


    @Test
    void testWhenNoMockProvidedShouldThrowException() {
        WebClient client =
                fakeWebClientBuilder
                        .build();


        assertThrows(NoMockFoundException.class, () -> {
            client.method(HttpMethod.GET)
                    .uri(URI.create("https://google.com")).exchange().block()
                    .bodyToMono(String.class).block();
        });
    }


    @Test
    void testShouldBeAbleToCompareByHeaders() {
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .withRequestHeader("foo", "bar")
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();


        WebClient client =
                fakeWebClientBuilder
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
        Assertions.assertTrue(this.fakeWebClientBuilder.assertAllResponsesDispatched());
    }


    @Test
    void testWhenHeaderNotMatchShouldThrowException() {
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .withRequestHeader("foo", "bar")
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();

        WebClient client =
                fakeWebClientBuilder
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertThrows(NoMockFoundException.class, () -> {
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
                .withRequestUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .withResponseHeader("foo", "bar")
                .build();

        WebClient client =
                fakeWebClientBuilder
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
        Assertions.assertTrue(this.fakeWebClientBuilder.assertAllResponsesDispatched());
    }


    @Test
    void testShouldBeAbleToBaseUrlParameter() {

        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com/foo")
                .withRequestMethod(HttpMethod.GET)
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();

        WebClient client =
                fakeWebClientBuilder
                        .baseUrl("https://google.com")
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("test", client.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path("/foo").build())
                .exchange().block()
                .bodyToMono(String.class).block());

    }


    @Test
    void testShouldBeAbleToCompareByRequestBodyCorrectly() {
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com/foo")
                .withRequestMethod(HttpMethod.POST)
                .withRequestBody(BodyInserters.fromFormData("foo", "bar"))
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();



        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .baseUrl("https://google.com")
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("test", client.method(HttpMethod.POST)
                .uri(uriBuilder -> uriBuilder.path("/foo").build())
                .body(BodyInserters.fromFormData("foo", "bar"))
                .exchange().block()
                .bodyToMono(String.class).block());

        Assertions.assertTrue(this.fakeWebClientBuilder.assertAllResponsesDispatched());
    }

    @Test
    void testShouldBeAbleToCompareByRequestBodyJsonCorrectly() {
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com/foo")
                .withRequestMethod(HttpMethod.POST)
                .withRequestBody(BodyInserters.fromValue(new MockJsonBody("f1", "f2")))
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();



        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .baseUrl("https://google.com")
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("test", client.method(HttpMethod.POST)
                .uri(uriBuilder -> uriBuilder.path("/foo").build())
                .body(BodyInserters.fromValue(new MockJsonBody("f1", "f2")))
                .exchange().block()
                .bodyToMono(String.class).block());

        Assertions.assertTrue(this.fakeWebClientBuilder.assertAllResponsesDispatched());
    }


    @Test
    void testShouldSerializeRequestCorrectly() {

        String expectedString = "FakeWebClient : Cant find suitable mocks for Request method : POST\nRequest Url : https://google.com\nRequest Headers : []";

        assertEquals(expectedString, NoMockFoundException.serialize(ClientRequest.create(HttpMethod.POST, URI.create("https://google.com"))
                .build()));

    }


    @Test
    void testShouldThrowExceptionIfNoRequestIsMade() {
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com/foo")
                .withRequestMethod(HttpMethod.GET)
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();

        WebClient client =
                fakeWebClientBuilder
                        .baseUrl("https://google.com")
                        .addRequestResponse(fakeRequestResponse)
                        .build();

        assertThrows( ResponseNotDelieverdException.class, fakeWebClientBuilder::assertAllResponsesDispatched);
    }


    @Test
    void testExceptionShouldPrintCorrectErrorMessages() {

        FakeRequestResponse response = new FakeRequestResponseBuilder()
                .withRequestUrl("https://google.com/foo")
                .withRequestMethod(HttpMethod.GET)
                .withRequestBody(BodyInserters.fromFormData("foo", "bar"))
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();

        Exception e = new ResponseNotDelieverdException(new ArrayList<>(List.of(response)));

        assertEquals("The following responses are not matched by fakewebclient\n" +
                "\n" +
                "Url: https://google.com/foo\n" +
                "Method: GET\n" +
                "Request Status Code: 200\n" +
                "Request Headers: {}\n" +
                "Request Body: foo=bar\n" +
                "Response Headers: {}\n", e.getMessage());

    }



}
