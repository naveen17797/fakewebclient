package io.github.naveen17797.fakewebclient.serializer;

public class RequestBodySerializerFactory {

    public static RequestBodySerializer getInstance() {
        return new DefaultRequestBodySerializer();
    }

}
