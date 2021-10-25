package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FakeExchangeFunction implements ExchangeFunction {


    private final List<FakeRequestResponse> requestResponsesList;

    public FakeExchangeFunction(List<FakeRequestResponse> requestResponsesList) {
        this.requestResponsesList = requestResponsesList;
    }

    @Override
    public Mono<ClientResponse> exchange(ClientRequest request) {

        List<FakeRequestResponse> filteredItems = this.requestResponsesList.stream().filter(item ->

                Objects.equals(item.getUrl(), request.url()) &&
                        item.getRequestMethod() == request.method() &&
                        headerCompare( item.getRequestHeaders(), request.headers() )

        ).collect(Collectors.toList());


        if (filteredItems.size() == 0) {

            throw new NoMockFoundException(request);
        }

        FakeRequestResponse match = filteredItems.get(0);




        return Mono.just(ClientResponse.create(match.getHttpStatus())
                .body(match.getResponse())
                        .headers((responseHeaders) -> {
                            for (Map.Entry<String, List<String>> entry: match.getResponseHeaders().entrySet()) {
                                responseHeaders.addAll(entry.getKey(), entry.getValue());
                            }
                        })
                .build());
    }

    private boolean headerCompare(Map<String, List<String>> itemHeader, HttpHeaders requestHeader) {
        return itemHeader.entrySet().equals(requestHeader.entrySet());
    }
}
