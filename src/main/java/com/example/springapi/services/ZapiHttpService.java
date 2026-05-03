package com.example.springapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ZapiHttpService {

    private final WebClient.Builder webClientBuilder;

    public Map<String, Object> get(String endpoint, String instanceId, String instanceToken, String clientToken){
        try{
            String url = String.format("https://api.z-api.io/instances/%s/token/%s/%s", instanceId, instanceToken, endpoint);

            return webClientBuilder.build()
                    .get()
                    .uri(url)
                    .header("Client-Token", clientToken)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> post(String endpoint, Object body, String instanceId, String instanceToken, String clientToken){
        try {
            String url = String.format("https://api.z-api.io/instances/%s/token/%s/%s", instanceId, instanceToken, endpoint);

            return webClientBuilder.build()
                    .post()
                    .uri(url)
                    .header("Client-Token", clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Map<String, Object> put(String endpoint, Object body, String instanceId, String instanceToken, String clientToken){
        try{
            String url = String.format("https://api.z-api.io/instances/%s/token/%s/%s", instanceId, instanceToken, endpoint);

            return webClientBuilder.build()
                    .put()
                    .uri(url)
                    .header("Client-token", clientToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> delete(String endpoint, String instanceId, String instanceToken, String clientToken){
        try{
            String url = String.format("https://api.z-api.io/instances/%s/token/%s/%s", instanceId, instanceToken, endpoint);

            return webClientBuilder.build()
                    .delete()
                    .uri(url)
                    .header("Client-Token", clientToken)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}