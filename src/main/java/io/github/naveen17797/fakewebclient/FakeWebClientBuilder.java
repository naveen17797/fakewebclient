package io.github.naveen17797.fakewebclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilderFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FakeWebClientBuilder implements WebClient.Builder {

    private String baseUrl;
    private Map<String, ?> defaultUriVariables;
    private UriBuilderFactory uriBuilderFactory;
    private MultiValueMap<String, String> defaultHeader;
    private Consumer<HttpHeaders> headersConsumer;
    private Consumer<MultiValueMap<String, String>> cookiesConsumer;
    private Consumer<WebClient.RequestHeadersSpec<?>> defaultRequest;
    private ExchangeFilterFunction filter;
    private Consumer<List<ExchangeFilterFunction>> filtersConsumer;
    private ClientHttpConnector connector;
    private Consumer<ClientCodecConfigurer> configurer;
    private ExchangeStrategies strategies;
    private Consumer<ExchangeStrategies.Builder> exchangeStrategiesConfigurer;
    private ExchangeFunction exchangeFunction;
    private Consumer<WebClient.Builder> builderConsumer;

    @Override
    public WebClient.Builder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public WebClient.Builder defaultUriVariables(Map<String, ?> defaultUriVariables) {
        this.defaultUriVariables = defaultUriVariables;
        return this;
    }

    @Override
    public WebClient.Builder uriBuilderFactory(UriBuilderFactory uriBuilderFactory) {
        this.uriBuilderFactory = uriBuilderFactory;
        return this;
    }

    @Override
    public WebClient.Builder defaultHeader(String header, String... values) {
        return null;
    }

    @Override
    public WebClient.Builder defaultHeaders(Consumer<HttpHeaders> headersConsumer) {
        this.headersConsumer = headersConsumer;
        return this;
    }

    @Override
    public WebClient.Builder defaultCookie(String cookie, String... values) {
        return null;
    }

    @Override
    public WebClient.Builder defaultCookies(Consumer<MultiValueMap<String, String>> cookiesConsumer) {
        this.cookiesConsumer = cookiesConsumer;
        return this;
    }

    @Override
    public WebClient.Builder defaultRequest(Consumer<WebClient.RequestHeadersSpec<?>> defaultRequest) {
        this.defaultRequest = defaultRequest;
        return this;
    }

    @Override
    public WebClient.Builder filter(ExchangeFilterFunction filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public WebClient.Builder filters(Consumer<List<ExchangeFilterFunction>> filtersConsumer) {

        this.filtersConsumer = filtersConsumer;
        return this;
    }

    @Override
    public WebClient.Builder clientConnector(ClientHttpConnector connector) {
        this.connector = connector;
        return this;
    }

    @Override
    public WebClient.Builder codecs(Consumer<ClientCodecConfigurer> configurer) {

        this.configurer = configurer;
        return this;
    }

    @Override
    public WebClient.Builder exchangeStrategies(ExchangeStrategies strategies) {
        this.strategies = strategies;
        return this;
    }

    @Override
    public WebClient.Builder exchangeStrategies(Consumer<ExchangeStrategies.Builder> exchangeStrategiesConfigurer) {
        this.exchangeStrategiesConfigurer = exchangeStrategiesConfigurer;
        return this;
    }

    @Override
    public WebClient.Builder exchangeFunction(ExchangeFunction exchangeFunction) {
        this.exchangeFunction = exchangeFunction;
        return this;
    }

    @Override
    public WebClient.Builder apply(Consumer<WebClient.Builder> builderConsumer) {
        this.builderConsumer = builderConsumer;
        return this;
    }

    @Override
    public WebClient.Builder clone() {
        return this;
    }

    @Override
    public WebClient build() {
        return new FakeWebClient();
    }


}
