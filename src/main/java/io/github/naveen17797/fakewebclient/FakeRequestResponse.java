package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class FakeRequestResponse {

    private URI url;
    private HttpMethod requestMethod;
    private String response;
    private final HttpStatus httpStatus;
    private final Map<String, List<String>> requestHeaders;
    private final Map<String, List<String>> responseHeaders;


    public URI getUrl() {
        return url;
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


    FakeRequestResponse(URI url,
                        HttpMethod requestMethod,
                        String response, HttpStatus httpStatus, Map<String, List<String>> requestHeaders, Map<String, List<String>> responseHeaders) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.response = response;
        this.httpStatus = httpStatus;
        this.requestHeaders = requestHeaders;
        this.responseHeaders = responseHeaders;
    }


}
