package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;

public class FakeRequestResponse {

    private final HttpStatus httpStatus;

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

    private URI url;

    private HttpMethod requestMethod;

    private String response;


    FakeRequestResponse(URI url,
                        HttpMethod requestMethod,
                        String response, HttpStatus httpStatus) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.response = response;
        this.httpStatus = httpStatus;

    }

}
