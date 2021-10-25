package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeRequestResponseBuilder {

    private URI url;

    private HttpMethod requestMethod;

    private String response;

    private HttpStatus statusCode;

    private Map<String, List<String>> headers = new HashMap<>();


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
        return new FakeRequestResponse(url, requestMethod, response, statusCode, headers);
    }

    public FakeRequestResponseBuilder withRequestHeader(String key, String value) {
        ArrayList<String> mutableList = new ArrayList<>();
        mutableList.add(value);
        headers.put(key, mutableList);
        return this;
    }
}
