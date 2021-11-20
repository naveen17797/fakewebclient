package io.github.naveen17797.fakewebclient;

import io.github.naveen17797.fakewebclient.serializer.RequestBodySerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
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
    private final RequestBodyComparator requestBodyComparator;
    private final RequestBodySerializer requestBodySerializer;

    public FakeExchangeFunction(FakeWebClientBuilder fakeWebClientBuilder, RequestBodyComparator requestBodyComparator, RequestBodySerializer requestBodySerializer) {
        this.fakeWebClientBuilder = fakeWebClientBuilder;
        this.requestBodyComparator = requestBodyComparator;
        this.requestBodySerializer = requestBodySerializer;
    }

    @Override
    public Mono<ClientResponse> exchange(ClientRequest request) {

        List<FakeRequestResponse> filteredItems = this.fakeWebClientBuilder.requestResponsesList.stream().filter(item ->

                Objects.equals(item.getUrl(), request.url()) &&
                        item.getRequestMethod() == request.method() &&
                        headerCompare(item.getRequestHeaders(), request.headers()) &&
                        compareByRequestBody(item, item.getRequestBody(), request.body())

        ).collect(Collectors.toList());


        if (filteredItems.size() == 0) {

            throw new NoMockFoundException(requestBodySerializer, request, this.fakeWebClientBuilder.requestResponsesList);
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

    private boolean compareByRequestBody(FakeRequestResponse expectedRequest, Optional<BodyInserter<?, ? super ClientHttpRequest>> expectedRequestBody, BodyInserter<?, ? super ClientHttpRequest> body) {

        return this.requestBodyComparator.compare(expectedRequest, expectedRequestBody, body);
    }

    private boolean headerCompare(Map<String, List<String>> itemHeader, HttpHeaders requestHeader) {
        return itemHeader.entrySet().equals(requestHeader.entrySet());
    }
}
