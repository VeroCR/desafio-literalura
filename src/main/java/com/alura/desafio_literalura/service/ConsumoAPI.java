package com.alura.desafio_literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.http.HttpClient.Redirect;

public class ConsumoAPI {
    public String obtenerDatos(String url) {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray results = jsonObject.getJSONArray("results");
        JSONObject firstBook = results.getJSONObject(0);
        System.out.println(firstBook);
        String result = firstBook.toString();
        System.out.println(result);
        return result;
    }
}
