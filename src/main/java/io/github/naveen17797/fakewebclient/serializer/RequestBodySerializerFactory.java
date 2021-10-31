package io.github.naveen17797.fakewebclient.serializer;

import java.util.Optional;

public class RequestBodySerializerFactory {

    private static Optional<RequestBodySerializer> instance = Optional.empty();

    public static RequestBodySerializer getInstance() {
        if (instance.isEmpty()) {
            instance = Optional.of(new DefaultRequestBodySerializer());
        }
        return instance.get();
    }

}
