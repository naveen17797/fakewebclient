package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class FakeWebClient implements WebClient {
    @Override
    public RequestHeadersUriSpec<?> get() {
        return null;
    }

    @Override
    public RequestHeadersUriSpec<?> head() {
        return null;
    }

    @Override
    public RequestBodyUriSpec post() {
        return null;
    }

    @Override
    public RequestBodyUriSpec put() {
        return null;
    }

    @Override
    public RequestBodyUriSpec patch() {
        return null;
    }

    @Override
    public RequestHeadersUriSpec<?> delete() {
        return null;
    }

    @Override
    public RequestHeadersUriSpec<?> options() {
        return null;
    }

    @Override
    public RequestBodyUriSpec method(HttpMethod method) {
        return null;
    }

    @Override
    public Builder mutate() {
        return null;
    }
}
