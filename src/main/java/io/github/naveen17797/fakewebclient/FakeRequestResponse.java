package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeRequestResponse {

    private final Optional<BodyInserter<?, ? super ClientHttpRequest>> requestBody;

    private URI url;
    private HttpMethod requestMethod;
    private String response;
    private final HttpStatus httpStatus;
    private final Map<String, List<String>> requestHeaders;
    private final Map<String, List<String>> responseHeaders;


    public URI getUrl() {
        return url;
    }

    public Optional<BodyInserter<?, ? super ClientHttpRequest>> getRequestBody() {
        return this.requestBody;
    }

    public HttpMethod getRequestMethod() {
        return requestMethod;
    }

    public String getResponse() {
        return response;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<String, List<String>> getRequestHeaders() {
        return requestHeaders;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return responseHeaders;
    }


    public FakeRequestResponse(URI url,
                               HttpMethod requestMethod,
                               String response, HttpStatus httpStatus, Map<String, List<String>> requestHeaders, Map<String, List<String>> responseHeaders, Optional<BodyInserter<?, ? super ClientHttpRequest>> requestBody) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.response = response;
        this.httpStatus = httpStatus;
        this.requestHeaders = requestHeaders;
        this.responseHeaders = responseHeaders;
        this.requestBody = requestBody;
    }


}
