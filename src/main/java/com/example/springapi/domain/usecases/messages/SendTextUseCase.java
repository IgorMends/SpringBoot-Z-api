package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.input.SendTextUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendTextUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendTextUseCase {

    private final ZapiHttpService zapiHttpService;

    public SendTextUseCaseOutput execute(SendTextUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try{

            Map<String, Object> response = zapiHttpService.post("send-text", body, instanceId, instanceToken, clientToken);

            return SendTextUseCaseOutput.builder()
                    .messageId(response.get("messageId") != null ? response.get("messageId").toString() : null)
                    .zaapId(response.get("zaapId") != null ? response.get("zaapId").toString() : null)
                    .id(response.get("id") != null ? response.get("id").toString() : null)
                    .build();
        } catch (Exception e) {
            return SendTextUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
