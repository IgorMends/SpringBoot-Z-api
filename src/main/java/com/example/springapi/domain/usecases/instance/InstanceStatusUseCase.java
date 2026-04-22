package com.example.springapi.domain.usecases.instance;

import com.example.springapi.core.boundary.output.instance.InstanceStatusUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class InstanceStatusUseCase {

    private final ZapiHttpService zapiHttpService;

    public InstanceStatusUseCaseOutput execute(){
        try {

            Map<String, Object> response = zapiHttpService.get("/status");

            return InstanceStatusUseCaseOutput.builder()
                    .connected(response.get("connected") != null ? (Boolean)response.get("connected") : null)
                    .created(response.get("created") != null ? response.get("created").toString() : null)
                    .smartphoneConnected(response.get("smartphoneConnected") != null ? (Boolean)response.get("smartphoneConnected") : null)
                    .build();
        } catch (Exception e) {
            return InstanceStatusUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
