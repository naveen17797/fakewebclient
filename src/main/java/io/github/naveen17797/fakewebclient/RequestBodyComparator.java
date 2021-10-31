package io.github.naveen17797.fakewebclient;

import io.github.naveen17797.fakewebclient.serializer.RequestBodySerializer;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import java.util.Optional;

public class RequestBodyComparator {

    private final RequestBodySerializer requestBodySerializer;

    public RequestBodyComparator(RequestBodySerializer requestBodySerializer) {
        this.requestBodySerializer = requestBodySerializer;
    }


    public boolean compare(

            FakeRequestResponse fakeRequestResponse,
            Optional<BodyInserter<?, ? super ClientHttpRequest>> expectedBody,
            BodyInserter<?, ? super ClientHttpRequest> requestBody
    ) {

        String r1 = extractBody(fakeRequestResponse, expectedBody);
        String r2 = extractBody(fakeRequestResponse, requestBody);
        return r1.equals(r2);

    }

    private String extractBody(FakeRequestResponse fakeRequestResponse, Optional<BodyInserter<?, ? super ClientHttpRequest>> requestBodyOptional) {

        if (requestBodyOptional.isEmpty()) {
            return "";
        }

        return this.extractBody(fakeRequestResponse, requestBodyOptional.get());

    }

    private String extractBody(FakeRequestResponse fakeRequestResponse, BodyInserter<?, ? super ClientHttpRequest> requestBody) {
        return this.requestBodySerializer.extractBody(
                fakeRequestResponse.getUrl(),
                fakeRequestResponse.getRequestMethod(),
                requestBody
        );
    }


}
