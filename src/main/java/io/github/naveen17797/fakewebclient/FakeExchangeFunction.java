package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeExchangeFunction implements ExchangeFunction {


    private final FakeWebClientBuilder fakeWebClientBuilder;

    public FakeExchangeFunction(FakeWebClientBuilder fakeWebClientBuilder) {
        this.fakeWebClientBuilder = fakeWebClientBuilder;
    }

    @Override
    public Mono<ClientResponse> exchange(ClientRequest request) {

        List<FakeRequestResponse> filteredItems = this.fakeWebClientBuilder.requestResponsesList.stream().filter(item ->

                Objects.equals(item.getUrl(), request.url()) &&
                        item.getRequestMethod() == request.method() &&
                        headerCompare(item.getRequestHeaders(), request.headers()) &&
                        compareByRequestBody(item.getRequestBody(), request.body())

        ).collect(Collectors.toList());


        if (filteredItems.size() == 0) {

            throw new NoMockFoundException(request);
        }

        FakeRequestResponse match = filteredItems.get(0);

        // Remove this item from the builder.
        this.fakeWebClientBuilder.requestResponsesList.remove(match);


        return Mono.just(ClientResponse.create(match.getHttpStatus())
                .body(match.getResponse())
                .headers((responseHeaders) -> {
                    for (Map.Entry<String, List<String>> entry : match.getResponseHeaders().entrySet()) {
                        responseHeaders.addAll(entry.getKey(), entry.getValue());
                    }
                })
                .build());
    }

    private boolean headerCompare(Map<String, List<String>> itemHeader, HttpHeaders requestHeader) {
        return itemHeader.entrySet().equals(requestHeader.entrySet());
    }
}
