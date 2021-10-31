package io.github.naveen17797.fakewebclient.serializer;

import org.springframework.core.codec.ByteBufferEncoder;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.codec.*;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.codec.multipart.MultipartHttpMessageWriter;
import org.springframework.http.codec.xml.Jaxb2XmlEncoder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.http.client.reactive.MockClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import java.net.URI;
import java.util.*;

public class DefaultRequestBodySerializer implements RequestBodySerializer {

    private final BodyInserter.Context context;

    DefaultRequestBodySerializer() {
        final List<HttpMessageWriter<?>> messageWriters = new ArrayList<>();
        messageWriters.add(new EncoderHttpMessageWriter<>(new ByteBufferEncoder()));
        messageWriters.add(new EncoderHttpMessageWriter<>(CharSequenceEncoder.textPlainOnly()));
        messageWriters.add(new ResourceHttpMessageWriter());
        messageWriters.add(new EncoderHttpMessageWriter<>(new Jaxb2XmlEncoder()));
        Jackson2JsonEncoder jsonEncoder = new Jackson2JsonEncoder();
        messageWriters.add(new EncoderHttpMessageWriter<>(jsonEncoder));
        messageWriters.add(new ServerSentEventHttpMessageWriter(jsonEncoder));
        messageWriters.add(new FormHttpMessageWriter());
        messageWriters.add(new EncoderHttpMessageWriter<>(CharSequenceEncoder.allMimeTypes()));
        messageWriters.add(new MultipartHttpMessageWriter(messageWriters));

        this.context = new BodyInserter.Context() {
            @Override
            public List<HttpMessageWriter<?>> messageWriters() {
                return messageWriters;
            }

            @Override
            public Optional<ServerHttpRequest> serverRequest() {
                return Optional.empty();
            }

            @Override
            public Map<String, Object> hints() {
                return new HashMap<>();
            }
        };
    }


    @Override
    public String extractBody(URI uri, HttpMethod method, BodyInserter<?, ? super ClientHttpRequest> requestBody) {
        MockClientHttpRequest clientHttpRequest = new MockClientHttpRequest(method, uri);
        requestBody.insert(clientHttpRequest, this.context).block();
        return clientHttpRequest.getBodyAsString().block();
    }
}
