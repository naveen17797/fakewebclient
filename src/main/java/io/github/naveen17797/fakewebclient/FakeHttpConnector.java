package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ClientHttpResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

public class FakeHttpConnector implements ClientHttpConnector {

    private HttpMethod method;

    private URI uri;

    @Override
    public Mono<ClientHttpResponse> connect(HttpMethod method, URI uri, Function<? super ClientHttpRequest, Mono<Void>> requestCallback) {

        this.method = method;
        this.uri = uri;

        return Mono.just(new FakeHttpResponse());
    }

}
