package io.github.naveen17797.fakewebclient.exceptions;

import io.github.naveen17797.fakewebclient.FakeRequestResponse;
import io.github.naveen17797.fakewebclient.serializer.RequestBodySerializer;
import io.github.naveen17797.fakewebclient.serializer.RequestBodySerializerFactory;

import java.util.ArrayList;
import java.util.List;


public class ResponseNotDelieverdException extends RuntimeException {

    public ResponseNotDelieverdException(List<FakeRequestResponse> requestResponses) {
        super(buildExceptionMessage(requestResponses));
    }

    private static String buildExceptionMessage(List<FakeRequestResponse> requestResponses) {
        StringBuilder message = new StringBuilder("The following responses are not matched by fakewebclient\n\n");
        for (FakeRequestResponse requestResponse : requestResponses) {
            message.append(serialize(requestResponse));
        }
        return message.toString();
    }

    static String serialize(FakeRequestResponse requestResponse) {
        RequestBodySerializer requestBodySerializer = RequestBodySerializerFactory.getInstance();
        String requestBody =
                requestResponse.getRequestBody().isPresent() ?
                        requestBodySerializer.extractBody(requestResponse.getUrl(),
                                requestResponse.getRequestMethod(), requestResponse.getRequestBody().get()) : "";
        return String.format("Url: %s\nMethod: %s\nRequest Status Code: %d\nRequest Headers: %s\nRequest Body: %s\nResponse Headers: %s\n",
                requestResponse.getUrl(),
                requestResponse.getRequestMethod(),
                requestResponse.getHttpStatus().value(),
                requestResponse.getRequestHeaders(),
                requestBody,
                requestResponse.getResponseHeaders()
        );
    }
}
