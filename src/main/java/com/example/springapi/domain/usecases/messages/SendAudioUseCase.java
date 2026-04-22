package com.example.springapi.domain.usecases.messages;

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

    public SendAudioUseCaseOutput execute (Map<String, Object> params){
        try{
            Map<String, Object> body = new HashMap<>();

            body.put("phone", params.get("phone"));
            body.put("audio", params.get("audio"));

            if(StringUtils.hasText(params.get("delayMessage").toString())){
                body.put("delayMessage", params.get("delayMessage"));
            }

            if(StringUtils.hasText(params.get("delayTyping").toString())){
                body.put("delayTyping", params.get("delayTyping"));
            }

            if(StringUtils.hasText(params.get("viewOnce").toString())){
                body.put("viewOnce", params.get("viewOnce"));
            }

            if(StringUtils.hasText(params.get("waveform").toString())){
                body.put("waveform", params.get("waveform"));
            }

            Map<String,Object> response = zapiHttpService.post("/send-audio", body);

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
