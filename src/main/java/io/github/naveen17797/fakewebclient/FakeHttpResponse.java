package io.github.naveen17797.fakewebclient;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;

public class FakeHttpResponse implements ClientHttpResponse {

    @Override
    public HttpStatus getStatusCode() {
        return null;
    }

    @Override
    public int getRawStatusCode() {
        return 0;
    }

    @Override
    public MultiValueMap<String, ResponseCookie> getCookies() {
        return null;
    }

    @Override
    public Flux<DataBuffer> getBody() {
        return null;
    }

    @Override
    public HttpHeaders getHeaders() {
        return null;
    }
}
