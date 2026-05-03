package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.input.SendImageUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendImageUseCaseOutput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.port.MessageRepository;
import com.example.springapi.services.ZapiHttpService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendImageUseCase {

    private final ZapiHttpService zapiHttpService;
    private final MessageRepository messageRepository;

    public SendImageUseCase(ZapiHttpService zapiHttpService, MessageRepository messageRepository) {
        this.zapiHttpService = zapiHttpService;
        this.messageRepository = messageRepository;
    }

    public SendImageUseCaseOutput execute(SendImageUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try {

            Map<String, Object> response = zapiHttpService.post("send-image", body, instanceId, instanceToken, clientToken);

            String zaapId = response.get("zaapId") != null ? response.get("zaapId").toString() : null;
            String messageId = response.get("messageId") != null ? response.get("messageId").toString() : null;
            String id = response.get("id") != null ? response.get("id").toString() : null;

            Message message = Message.builder()
                    .type("image")
                    .phone(body.getPhone())
                    .content(body.getImage())
                    .zaapId(zaapId)
                    .messageId(messageId)
                    .status("sent")
                    .sentAt(new Date())
                    .metadata(Map.of("instanceId", instanceId, "caption", body.getCaption(), "delayMessage", body.getDelayMessage(), "viewOnce", body.getViewOnce()))
                    .build();

            messageRepository.save(message);

            return SendImageUseCaseOutput.builder()
                    .messageId(messageId)
                    .zaapId(zaapId)
                    .id(id)
                    .build();
        } catch (Exception e) {
            return SendImageUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
