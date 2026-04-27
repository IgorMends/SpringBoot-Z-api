package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.input.SendCallUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendCallUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendCallUseCase {

    private final ZapiHttpService zapiHttpService;

    public SendCallUseCaseOutput execute(SendCallUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try{

            Map<String, Object> response = zapiHttpService.post("send-call", body, instanceId, instanceToken, clientToken);

            return SendCallUseCaseOutput.builder()
                    .messageId(response.get("messageId") != null ? response.get("messageId").toString() : null)
                    .zaapId(response.get("zaapId") != null ? response.get("zaapId").toString() : null)
                    .id(response.get("id") != null ? response.get("id").toString() : null)
                    .build();

        } catch (Exception e) {
            return SendCallUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
