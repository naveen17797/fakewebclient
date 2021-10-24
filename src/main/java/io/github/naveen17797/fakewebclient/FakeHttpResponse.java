package io.github.naveen17797.fakewebclient;

import org.springframework.core.io.buffer.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;

import java.net.URI;

public class FakeHttpResponse implements ClientHttpResponse {

    private HttpStatus httpStatus;
    private String response;

    public FakeHttpResponse() {
    }

    public FakeHttpResponse(String response, HttpStatus httpStatus) {
        this.response = response;
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getStatusCode() {
        return httpStatus;
    }

    @Override
    public int getRawStatusCode() {
        return httpStatus.value();
    }

    @Override
    public MultiValueMap<String, ResponseCookie> getCookies() {
        return null;
    }

    @Override
    public Flux<DataBuffer> getBody() {
        return Flux.just(DefaultDataBufferFactory.sharedInstance.wrap(this.response.getBytes()));
    }

    @Override
    public HttpHeaders getHeaders() {
        return new HttpHeaders();
    }
}
