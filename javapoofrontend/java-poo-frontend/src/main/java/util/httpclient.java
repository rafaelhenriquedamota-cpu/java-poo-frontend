package util;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpClient {
    private final java.net.http.HttpClient client;
    private final String baseUrl;
    private final Duration timeout = Duration.ofSeconds(10);

    public HttpClient() {
        this(null);
    }

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = java.net.http.HttpClient.newBuilder()
                .connectTimeout(timeout)
                .build();
    }

    private String buildUrl(String url) {
        if (url == null) throw new IllegalArgumentException("url == null");
        if (url.startsWith("http") || baseUrl == null || baseUrl.isEmpty()) {
            return url;
        }
        if (baseUrl.endsWith("/") && url.startsWith("/")) {
            return baseUrl + url.substring(1);
        } else if (!baseUrl.endsWith("/") && !url.startsWith("/")) {
            return baseUrl + "/" + url;
        } else {
            return baseUrl + url;
        }
    }

    private void checkStatus(HttpResponse<String> response) throws IOException {
        int code = response.statusCode();
        if (code < 200 || code >= 300) {
            throw new IOException("HTTP error: " + code + " - " + response.body());
        }
    }

    public String get(String url) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(url)))
                .GET()
                .timeout(timeout)
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        checkStatus(resp);
        return resp.body();
    }

    public String post(String url, String json) throws IOException, InterruptedException {
        return post(url, json, "application/json");
    }

    public String post(String url, String body, String contentType) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(url)))
                .header("Content-Type", contentType)
                .POST(HttpRequest.BodyPublishers.ofString(body == null ? "" : body))
                .timeout(timeout)
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        checkStatus(resp);
        return resp.body();
    }

    public String put(String url, String json) throws IOException, InterruptedException {
        return put(url, json, "application/json");
    }

    public String put(String url, String body, String contentType) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(url)))
                .header("Content-Type", contentType)
                .PUT(HttpRequest.BodyPublishers.ofString(body == null ? "" : body))
                .timeout(timeout)
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        checkStatus(resp);
        return resp.body();
    }

    public String delete(String url) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(url)))
                .DELETE()
                .timeout(timeout)
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        checkStatus(resp);
        return resp.body();
    }
}
