package io.github.naveen17797.fakewebclient;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class FakeRequestHeadersUriSpec implements WebClient.RequestHeadersUriSpec {


    private MediaType[] acceptableMediaTypes;
    private Charset[] acceptableCharsets;
    private ZonedDateTime ifModifiedSince;
    private String[] ifNoneMatches;
    private Function exchangeToFluxResponseHandler;
    private Function exchangeToMonoResponseHandler;
    private Consumer httpRequestConsumer;
    private Function contextModifier;
    private Consumer attributesConsumer;
    private Consumer headersConsumer;
    private Consumer cookiesConsumer;
    private URI uri;
    private Function function;
    private Object[] uriVariables;
    private String uriString;
    private Function uriFunction;
    private Map uriVariablesMap;


    @Override
    public WebClient.RequestHeadersSpec accept(MediaType... acceptableMediaTypes) {
        this.acceptableMediaTypes = acceptableMediaTypes;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec acceptCharset(Charset... acceptableCharsets) {
        this.acceptableCharsets = acceptableCharsets;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec cookie(String name, String value) {
        return null;
    }

    @Override
    public WebClient.RequestHeadersSpec ifModifiedSince(ZonedDateTime ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec ifNoneMatch(String... ifNoneMatches) {
        this.ifNoneMatches = ifNoneMatches;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec header(String headerName, String... headerValues) {
        return null;
    }

    @Override
    public WebClient.RequestHeadersSpec attribute(String name, Object value) {
        return null;
    }

    @Override
    public WebClient.ResponseSpec retrieve() {
        return null;
    }

    @Override
    public Mono<ClientResponse> exchange() {
        return null;
    }

    @Override
    public Flux exchangeToFlux(Function exchangeToFluxResponseHandler) {
        this.exchangeToFluxResponseHandler = exchangeToFluxResponseHandler;
        return Flux.just(this);
    }

    @Override
    public Mono exchangeToMono(Function exchangeToMonoResponseHandler) {
        this.exchangeToMonoResponseHandler = exchangeToMonoResponseHandler;
        return Mono.just(this);
    }

    @Override
    public WebClient.RequestHeadersSpec httpRequest(Consumer httpRequestConsumer) {
     this.httpRequestConsumer = httpRequestConsumer;
     return this;
    }

    @Override
    public WebClient.RequestHeadersSpec context(Function contextModifier) {
        this.contextModifier = contextModifier;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec attributes(Consumer attributesConsumer) {
        this.attributesConsumer = attributesConsumer;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec headers(Consumer headersConsumer) {
        this.headersConsumer = headersConsumer;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec cookies(Consumer cookiesConsumer) {
       this.cookiesConsumer = cookiesConsumer;
       return this;
    }

    @Override
    public WebClient.RequestHeadersSpec<?> uri(URI uri) {
        this.uri = uri;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec<?> uri(String uri, Object... uriVariables) {
       this.uriString = uri;
       this.uriVariables = uriVariables;
       return this;
    }

    @Override
    public WebClient.RequestHeadersSpec<?> uri(Function uriFunction) {
        this.uriFunction = uriFunction;
        return this;
    }

    @Override
    public WebClient.RequestHeadersSpec<?> uri(String uri, Function uriFunction) {
       this.uriString = uri;
       this.uriFunction = function;
       return this;
    }

    @Override
    public WebClient.RequestHeadersSpec<?> uri(String uri, Map uriVariablesMap) {
       this.uriString = uri;
       this.uriVariablesMap = uriVariablesMap;
       return this;
    }
}
