package com.literatura.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GutendexClient {

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        client = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    public JsonNode searchBookByTitle(String title) throws Exception {
        // Codificar el título antes de usarlo en la URL
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://gutendex.com/books/?search=" + encodedTitle))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readTree(response.body());
    }

    public JsonNode searchBookByAuthor(String authorName) throws Exception {
        // Codificar el nombre del autor para la URL
        String encodedAuthorName = URLEncoder.encode(authorName, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://gutendex.com/books/?search=" + encodedAuthorName))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readTree(response.body());
    }


}
