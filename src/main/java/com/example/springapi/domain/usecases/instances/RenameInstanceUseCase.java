package com.example.springapi.domain.usecases.instances;

import com.example.springapi.core.boundary.input.RenameInstanceUseCaseInput;
import com.example.springapi.core.boundary.output.instance.RenameInstanceUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RenameInstanceUseCase {

    private final ZapiHttpService zapiHttpService;

    public RenameInstanceUseCaseOutput execute(RenameInstanceUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try{

            Map<String, Object> response = zapiHttpService.put("update-name", body, instanceId, instanceToken, clientToken);

            return RenameInstanceUseCaseOutput.builder()
                    .value(response.get("value") != null ? response.get("value").toString() : null)
                    .build();
        }catch (Exception e){
            return RenameInstanceUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
