package io.github.naveen17797.fakewebclient.serializer;

import io.github.naveen17797.fakewebclient.FakeRequestResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import java.net.URI;

public interface RequestBodySerializer {

    String extractBody(URI uri, HttpMethod method, BodyInserter<?, ? super ClientHttpRequest> requestBody);

}
