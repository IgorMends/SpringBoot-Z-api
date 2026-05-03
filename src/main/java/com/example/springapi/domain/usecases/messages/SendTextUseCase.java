package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.input.SendTextUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendTextUseCaseOutput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.port.MessageRepository;
import com.example.springapi.services.ZapiHttpService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SendTextUseCase {

    private final ZapiHttpService zapiHttpService;
    private final MessageRepository messageRepository;

    public SendTextUseCase(ZapiHttpService zapiHttpService, MessageRepository messageRepository) {
        this.zapiHttpService = zapiHttpService;
        this.messageRepository = messageRepository;
    }

    public SendTextUseCaseOutput execute(SendTextUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try{

            Map<String, Object> response = zapiHttpService.post("send-text", body, instanceId, instanceToken, clientToken);

            String zaapId = response.get("zaapId") != null ? response.get("zaapId").toString() : null;
            String messageId = response.get("messageId") != null ? response.get("messageId").toString() : null;
            String id = response.get("id") != null ? response.get("id").toString() : null;

            Message message = Message.builder()
                    .type("text")
                    .phone(body.getPhone())
                    .content(body.getMessage())
                    .zaapId(zaapId)
                    .messageId(messageId)
                    .status("sent")
                    .sentAt(new Date())
                    .metadata(Map.of("instanceId", instanceId, "delayMessage", Objects.nonNull(body.getDelayMessage()) ? body.getDelayMessage() : -1, "delayTyping", Objects.nonNull(body.getDelayTyping()) ? body.getDelayTyping() : -1, "editMessage", StringUtils.hasText(body.getEditMessage()) ? body.getEditMessage() : ""))
                    .build();

            messageRepository.save(message);

            return SendTextUseCaseOutput.builder()
                    .messageId(messageId)
                    .zaapId(zaapId)
                    .id(id)
                    .build();
        } catch (Exception e) {
            return SendTextUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
