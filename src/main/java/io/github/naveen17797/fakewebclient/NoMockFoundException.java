package io.github.naveen17797.fakewebclient;

import io.github.naveen17797.fakewebclient.serializer.RequestBodySerializer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;

import java.util.List;
import java.util.stream.Collectors;

public class NoMockFoundException extends RuntimeException {


    static String serializeRequest(RequestBodySerializer serializer, ClientRequest clientRequest) {
        return String.format(
                "Request Method : %s\n" +
                "Request Url : %s\n" +
                        "Request Headers : %s\n" +
                        "Request Body: %s",
                clientRequest.method(),
                clientRequest.url(),
                clientRequest.headers(),
                serializer.extractBody(
                        clientRequest.url(),
                        clientRequest.method(),
                        clientRequest.body()
                )
        );
    }

    static String serializeMockRequest(RequestBodySerializer serializer, FakeRequestResponse fakeRequestResponse) {
        return String.format(
                "Request Method : %s\n" +
                        "Request Url : %s\n" +
                        "Request Headers : %s\n" +
                        "Request Body: %s",
                fakeRequestResponse.getRequestMethod(),
                fakeRequestResponse.getUrl(),
                fakeRequestResponse.getRequestHeaders(),
                serializer.extractBody(
                        fakeRequestResponse.getUrl(),
                        fakeRequestResponse.getRequestMethod(),
                        fakeRequestResponse.getRequestBody().orElse(BodyInserters.empty())
                )
        );
    }



    static String serialize(RequestBodySerializer serializer, ClientRequest receivedRequest, List<FakeRequestResponse> requestResponsesList) {
        return String.format("FakeWebClient : Cant find suitable mocks for the request\n"
                        + "%s\n\n"
                        + "=== Enqueued Mock Requests ===\n"
                        + "%s",
                serializeRequest(serializer, receivedRequest),
                requestResponsesList.stream().map(r -> serializeMockRequest(serializer, r))
                        .collect(Collectors.joining("\n\n"))
        );
    }

    public NoMockFoundException(RequestBodySerializer serializer, ClientRequest clientHttpRequest, List<FakeRequestResponse> requestResponsesList) {
        super(serialize(serializer, clientHttpRequest, requestResponsesList));
    }
}
