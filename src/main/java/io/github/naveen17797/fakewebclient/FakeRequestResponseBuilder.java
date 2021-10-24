package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;

public class FakeRequestResponseBuilder {

    private URI url;

    private HttpMethod requestMethod;

    private String response;

    private HttpStatus statusCode;


    FakeRequestResponseBuilder forUrl(String url) {
        this.url = URI.create(url);
        return this;
    }

    FakeRequestResponseBuilder withRequestMethod(HttpMethod method) {
        this.requestMethod = method;
        return this;
    }

    FakeRequestResponseBuilder replyWithResponse(String response) {
        this.response = response;
        return this;
    }

    FakeRequestResponseBuilder replyWithResponseStatusCode(int statusCode) {

        this.statusCode = HttpStatus.resolve(statusCode);
        return this;
    }


    FakeRequestResponse build() {
        return new FakeRequestResponse(url, requestMethod, response, statusCode);
    }

}
