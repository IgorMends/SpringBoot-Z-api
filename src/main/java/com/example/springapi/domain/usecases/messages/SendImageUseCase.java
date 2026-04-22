package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.output.message.SendImageUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendImageUseCase {

    private final ZapiHttpService zapiHttpService;

    public SendImageUseCaseOutput execute(Map<String, Object> params){
        try {
            Map<String, Object> body = new HashMap<>();

            body.put("phone", params.get("phone"));
            body.put("image", params.get("image"));

            if(StringUtils.hasText(params.get("caption").toString())){
                body.put("caption", params.get("caption"));
            }

            if(StringUtils.hasText(params.get("delayMessage").toString())){
                body.put("delayMessage", params.get("delayMessage"));
            }

            if(StringUtils.hasText(params.get("viewOnce").toString())){
                body.put("viewOnce", params.get("viewOnce"));
            }

            Map<String, Object> response = zapiHttpService.post("/send-image", body);

            return SendImageUseCaseOutput.builder()
                    .messageId(response.get("messageId") != null ? response.get("messageId").toString() : null)
                    .zaapId(response.get("zaapId") != null ? response.get("zaapId").toString() : null)
                    .id(response.get("id") != null ? response.get("id").toString() : null)
                    .build();
        } catch (Exception e) {
            return SendImageUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
