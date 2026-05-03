package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.input.SendCallUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendCallUseCaseOutput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.port.MessageRepository;
import com.example.springapi.services.ZapiHttpService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendCallUseCase {

    private final ZapiHttpService zapiHttpService;
    private final MessageRepository messageRepository;

    public SendCallUseCase(ZapiHttpService zapiHttpService, MessageRepository messageRepository) {
        this.zapiHttpService = zapiHttpService;
        this.messageRepository = messageRepository;
    }

    public SendCallUseCaseOutput execute(SendCallUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try{

            Map<String, Object> response = zapiHttpService.post("send-call", body, instanceId, instanceToken, clientToken);

            String zaapId = response.get("zaapId") != null ? response.get("zaapId").toString() : null;
            String messageId = response.get("messageId") != null ? response.get("messageId").toString() : null;
            String id = response.get("id") != null ? response.get("id").toString() : null;

            Message message = Message.builder()
                    .type("call")
                    .phone(body.getPhone())
                    .content(body.getCallAudioUrl())
                    .zaapId(zaapId)
                    .messageId(messageId)
                    .status("sent")
                    .sentAt(new Date())
                    .metadata(Map.of("instanceId", instanceId))
                    .build();

            messageRepository.save(message);

            return SendCallUseCaseOutput.builder()
                    .messageId(messageId)
                    .zaapId(zaapId)
                    .id(id)
                    .build();

        } catch (Exception e) {
            return SendCallUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
