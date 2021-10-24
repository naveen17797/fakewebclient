package io.github.naveen17797.fakewebclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FakewebclientApplicationTests {

    @Autowired
    private WebClient.Builder builder;

    private WebClient webClient;

    @BeforeEach
    void beforeEach() {
        this.webClient = WebClient
                .builder()
                .clientConnector(new FakeHttpConnector())
                .build();
    }

    @Test
    void testShouldBeAbleToUseFakeWebClientForAssertions() {

        WebClient client = forUrl("https://google.com")
//                .withRequestHeaders("request_header_key", "request_header_value")
//                .withRequestCookies("request_cookie_key", "request_cookie_value")
                .withRequestMethod(HttpMethod.GET)
//                .replyWithResponseHeaders("response_header_key", "response_header_value")
//                .replyWithResponseCookies("response_cookie_key", "response_cookie_value")
                .replyWithResponse("test")
                .build();


        assertEquals( "test", client.method(HttpMethod.GET).exchange().block().bodyToMono(String.class).block());


    }

}
