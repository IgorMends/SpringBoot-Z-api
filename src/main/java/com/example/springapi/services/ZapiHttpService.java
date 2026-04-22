package com.example.springapi.services;

import com.example.springapi.config.ZapiConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ZapiHttpService {

    private final ZapiConfig zapiConfig;
    private final WebClient.Builder webClientBuilder;

    public Map<String, Object> get(String endpoint){
        try{
            String url = zapiConfig.getBaseUrl() + endpoint;

            return webClientBuilder.build()
                    .get()
                    .uri(url)
                    .header("Client-Token", zapiConfig.getClientToken())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> post(String endpoint, Object body){
        try {
            String url = zapiConfig.getBaseUrl() + endpoint;

            return webClientBuilder.build()
                    .post()
                    .uri(url)
                    .header("Client-Token", zapiConfig.getClientToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Map<String, Object> put(String endpoint, Object body){
        try{
            String url = zapiConfig.getBaseUrl() + endpoint;

            return webClientBuilder.build()
                    .put()
                    .uri(url)
                    .header("Client-token", zapiConfig.getClientToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> delete(String endpoint){
        try{
            String url = zapiConfig.getBaseUrl() + endpoint;

            return webClientBuilder.build()
                    .delete()
                    .uri(url)
                    .header("Client-Token", zapiConfig.getClientToken())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}