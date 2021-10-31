package io.github.naveen17797.fakewebclient.exchangefunction;

import io.github.naveen17797.fakewebclient.FakeExchangeFunction;
import io.github.naveen17797.fakewebclient.FakeWebClientBuilder;
import io.github.naveen17797.fakewebclient.RequestBodyComparator;
import io.github.naveen17797.fakewebclient.serializer.RequestBodySerializer;
import io.github.naveen17797.fakewebclient.serializer.RequestBodySerializerFactory;
import org.springframework.web.reactive.function.client.ExchangeFunction;

public class ExchangeFunctionFactory {

    public static ExchangeFunction getInstance(FakeWebClientBuilder builder) {
        RequestBodySerializer serializer = RequestBodySerializerFactory.getInstance();
        RequestBodyComparator comparator = new RequestBodyComparator(serializer);
        return new FakeExchangeFunction(builder, comparator);
    }

}
