package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ClientHttpResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FakeHttpConnector implements ClientHttpConnector {

    private final List<FakeRequestResponse> requestResponseList;


    public FakeHttpConnector(List<FakeRequestResponse> requestResponseList) {
        this.requestResponseList = requestResponseList;
    }


    @Override
    public Mono<ClientHttpResponse> connect(HttpMethod method, URI uri, Function<? super ClientHttpRequest, Mono<Void>> requestCallback) {
        List<FakeRequestResponse> filteredResponses = this.requestResponseList.stream()
                .filter(it -> it.getRequestMethod() == method && it.getUrl().toString() == uri.toString())
                .collect(Collectors.toList());
        if (filteredResponses.isEmpty()) {
            return Mono.just(new FakeHttpResponse());
        }

        FakeRequestResponse matchedRequest = filteredResponses.get(0);

        return Mono.just(new FakeHttpResponse(matchedRequest.getResponse(), matchedRequest.getHttpStatus()));
    }

}
