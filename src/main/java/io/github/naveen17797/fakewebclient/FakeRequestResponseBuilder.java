package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import java.net.URI;
import java.util.*;

public class FakeRequestResponseBuilder {

    private URI url;

    private HttpMethod requestMethod;

    private String response;

    private HttpStatus statusCode;

    private Map<String, List<String>> requestHeaders = new HashMap<>();

    private Map<String, List<String>> responseHeaders = new HashMap<>();

    private Optional<BodyInserter<?, ? super ClientHttpRequest>> requestBody = Optional.empty();


    public FakeRequestResponseBuilder withRequestUrl(String url) {
        this.url = URI.create(url);
        return this;
    }

    public FakeRequestResponseBuilder withRequestMethod(HttpMethod method) {
        this.requestMethod = method;
        return this;
    }

    public FakeRequestResponseBuilder replyWithResponse(String response) {
        this.response = response;
        return this;
    }

    public FakeRequestResponseBuilder replyWithResponseStatusCode(int statusCode) {

        this.statusCode = HttpStatus.resolve(statusCode);
        return this;
    }


    public FakeRequestResponse build() {
        return new FakeRequestResponse(url, requestMethod, response, statusCode, requestHeaders, responseHeaders, requestBody);
    }

    public FakeRequestResponseBuilder withRequestHeader(String key, String value) {
        ArrayList<String> mutableList = new ArrayList<>();
        mutableList.add(value);
        requestHeaders.put(key, mutableList);
        return this;
    }

    public FakeRequestResponseBuilder withResponseHeader(String key, String value) {
        ArrayList<String> mutableList = new ArrayList<>();
        mutableList.add(value);
        responseHeaders.put(key, mutableList);
        return this;
    }


    public FakeRequestResponseBuilder withRequestBody(BodyInserter<?, ? super ClientHttpRequest> requestBody) {
        this.requestBody = Optional.of(requestBody);
        return this;
    }
}
