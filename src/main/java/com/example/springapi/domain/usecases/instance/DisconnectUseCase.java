package com.example.springapi.domain.usecases.instance;

import com.example.springapi.core.boundary.output.instance.DisconnectUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DisconnectUseCase {

    private final ZapiHttpService zapiHttpService;

    public DisconnectUseCaseOutput execute(){
        try{
            Map<String, Object> response = zapiHttpService.get("/disconnect");

            return DisconnectUseCaseOutput.builder().value(response.get("value") != null ? response.get("value").toString() : null).build();
        }catch (Exception e){
            return DisconnectUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
