package com.example.springapi.domain.usecases.messages;

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

    public SendTextUseCaseOutput execute(Map<String, Object> params){
        try{
            Map<String, Object> body = new HashMap<>();

            body.put("phone", params.get("phone"));
            body.put("message", params.get("message"));

            if (StringUtils.hasText(params.get("delayMessage").toString())){
                body.put("delayMessage", params.get("delayMessage"));
            }

            if (StringUtils.hasText(params.get("delayTyping").toString())){
                body.put("delayTyping", params.get("delayTyping"));
            }

            if (StringUtils.hasText(params.get("editMessage").toString())){
                body.put("editMessage", params.get("editMessage"));
            }

            Map<String, Object> response = zapiHttpService.post("/send-text", body);

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
