# fakewebclient
Fake web client for spring unit tests, 

```

<dependency>
  <groupId>io.github.naveen17797</groupId>
  <artifactId>fakewebclient</artifactId>
  <scope>test</scope>
</dependency>

```


### Why?
Couldnt mock it properly with mockito, unable to find a nice alternative which can work on unit tests.

### Sample Usage

```java

FakeWebClientBuilder fakeWebClientBuilder = FakeWebClientBuilder.useDefaultWebClientBuilder();

        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
        .withRequestUrl("https://google.com/foo")
        .withRequestMethod(HttpMethod.POST)
        .withRequestBody(BodyInserters.fromFormData("foo", "bar"))
        .replyWithResponse("test")
        .replyWithResponseStatusCode(200)
        .build();



        WebClient client =
        FakeWebClientBuilder.useDefaultWebClientBuilder()
        .baseUrl("https://google.com")
        .addRequestResponse(fakeRequestResponse)
        .build();


        assertEquals("test", client.method(HttpMethod.POST)
        .uri(uriBuilder -> uriBuilder.path("/foo").build())
        .body(BodyInserters.fromFormData("foo", "bar"))
        .exchange().block()
        .bodyToMono(String.class).block());

        Assertions.assertTrue(fakeWebClientBuilder.assertAllResponsesDispatched());
```