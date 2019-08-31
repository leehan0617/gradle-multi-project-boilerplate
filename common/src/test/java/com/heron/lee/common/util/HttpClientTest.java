package com.heron.lee.common.util;

import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class HttpClientTest {

    @Test
    public void httpClientTest () {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .executor(Executors.newFixedThreadPool(2))
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("https://api.github.com")).build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, handler);
        future.thenAccept(response -> {
            Map<String, List<String>> headersMap = response.headers().map();
            System.out.println("########## Headers ############");
            headersMap.entrySet().iterator().forEachRemaining(entry -> System.out.println("Key: " + entry.getKey() + ", values:" + entry.getValue()));
            System.out.println("statusCode: " + response.statusCode());
            System.out.println("version:" + response.version());
            System.out.println("body:" + response.body());
        });

        try {
            Thread.sleep(3000);
            System.out.println("main end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
