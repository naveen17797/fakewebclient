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

    private Map<String, List<String>> requestHeaders = new HashMap<>();

    private Map<String, List<String>> responseHeaders = new HashMap<>();


    public FakeRequestResponseBuilder forUrl(String url) {
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
        return new FakeRequestResponse(url, requestMethod, response, statusCode, requestHeaders, responseHeaders);
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
}
