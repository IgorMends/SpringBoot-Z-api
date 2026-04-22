package com.example.springapi.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ZapiConfig {

    @Value("${zapi.instance.id}")
    private String instanceId;

    @Value("${zapi.instance.token}")
    private String instanceToken;

    @Value("${zapi.client.token}")
    private String clientToken;

    public String getBaseUrl(){
        return String.format("https://api.z-api.io/instances/%s/token/%s", instanceId, instanceToken);
    }
}
