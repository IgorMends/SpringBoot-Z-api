package com.example.springapi.domain.usecases.instance;

import com.example.springapi.core.boundary.output.instance.RestartUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestartUseCase {

    private final ZapiHttpService zapiHttpService;

    public RestartUseCaseOutput execute(String instanceId, String instanceToken, String clientToken){
        try {
            Map<String, Object> response = zapiHttpService.get("restart", instanceId, instanceToken, clientToken);

            return RestartUseCaseOutput.builder()
                    .value(response.get("value") != null ? (Boolean)response.get("value") : null)
                    .build();
        }catch (Exception e){
            return RestartUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
