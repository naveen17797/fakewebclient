package io.github.naveen17797.fakewebclient;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


public class FakeWebClientBuilder {


    private WebClient.Builder builder;

    private List<FakeRequestResponse> requestResponsesList = new ArrayList<>();


    FakeWebClientBuilder(WebClient.Builder builder) {
        this.builder = builder;
    }


    static FakeWebClientBuilder useDefaultWebClientBuilder() {
        return new FakeWebClientBuilder(WebClient.builder());
    }


    WebClient build() {
        return this.builder
                .clientConnector(
                        new FakeHttpConnector(requestResponsesList)
                )
                .exchangeFunction(new FakeExchangeFunction(requestResponsesList))


                .build();
    }


    public FakeWebClientBuilder addRequestResponse(FakeRequestResponse fakeRequestResponse) {
        this.requestResponsesList.add(fakeRequestResponse);
        return this;
    }
}
