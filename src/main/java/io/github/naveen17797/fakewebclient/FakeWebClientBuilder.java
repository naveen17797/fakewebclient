package io.github.naveen17797.fakewebclient;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


public class FakeWebClientBuilder {


    private WebClient.Builder builder;

    private String baseUrl;

    private List<FakeRequestResponse> requestResponsesList = new ArrayList<>();


    FakeWebClientBuilder(WebClient.Builder builder) {
        this.builder = builder;
    }


    public static FakeWebClientBuilder useDefaultWebClientBuilder() {
        return new FakeWebClientBuilder(WebClient.builder());
    }


    public WebClient build() {
        WebClient.Builder builder = this.builder
                .clientConnector(
                        new FakeHttpConnector(requestResponsesList)
                )
                .exchangeFunction(new FakeExchangeFunction(requestResponsesList));

        if (baseUrl != null) {
            builder.baseUrl(baseUrl);
        }

        return builder.build();
    }


    public FakeWebClientBuilder addRequestResponse(FakeRequestResponse fakeRequestResponse) {
        this.requestResponsesList.add(fakeRequestResponse);
        return this;
    }

    public FakeWebClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
