package io.github.naveen17797.fakewebclient;

import org.springframework.web.reactive.function.client.ClientRequest;

public class NoMockFoundException extends RuntimeException {

    static String serialize(ClientRequest clientHttpRequest) {
        return String.format("FakeWebClient : Cant find suitable mocks for Request method : %s\nRequest Url : %s\nRequest Headers : %s",
                clientHttpRequest.method(),
                clientHttpRequest.url(),
                clientHttpRequest.headers()
        );
    }

    public NoMockFoundException(ClientRequest clientHttpRequest) {

        super(serialize(clientHttpRequest));
    }
}
