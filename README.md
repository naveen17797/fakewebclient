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
        FakeRequestResponse fakeRequestResponse = new FakeRequestResponseBuilder()
                .forUrl("https://google.com")
                .withRequestMethod(HttpMethod.GET)
                .withRequestHeader("foo", "bar")
                .replyWithResponse("test")
                .replyWithResponseStatusCode(200)
                .build();


        WebClient client =
                FakeWebClientBuilder.useDefaultWebClientBuilder()
                        .addRequestResponse(fakeRequestResponse)
                        .build();


        assertEquals("test",

                client
                        .method(HttpMethod.GET)
                        .uri(URI.create("https://google.com"))
                        .header("foo", "bar")
                        .exchange()
                        .block()
                        .bodyToMono(String.class).block());

```