package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.input.SendAudioUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendAudioUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendAudioUseCase {

    private final ZapiHttpService zapiHttpService;

    public SendAudioUseCaseOutput execute (SendAudioUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try{

            Map<String,Object> response = zapiHttpService.post("send-audio", body, instanceId, instanceToken, clientToken);

            return SendAudioUseCaseOutput.builder()
                    .zaapId(response.get("zaapId") != null ? response.get("zaapId").toString() : null)
                    .messageId(response.get("messageId") != null ? response.get("messageId").toString() : null)
                    .id(response.get("id") != null ? response.get("id").toString() : null)
                    .build();

        } catch (Exception e) {
            return SendAudioUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
