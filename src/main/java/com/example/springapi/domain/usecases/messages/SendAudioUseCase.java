package com.example.springapi.domain.usecases.messages;

import com.example.springapi.core.boundary.input.SendAudioUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendAudioUseCaseOutput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.port.MessageRepository;
import com.example.springapi.services.ZapiHttpService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SendAudioUseCase {

    private final ZapiHttpService zapiHttpService;
    private final MessageRepository messageRepository;

    public SendAudioUseCase(ZapiHttpService zapiHttpService, MessageRepository messageRepository) {
        this.zapiHttpService = zapiHttpService;
        this.messageRepository = messageRepository;
    }

    public SendAudioUseCaseOutput execute (SendAudioUseCaseInput body, String instanceId, String instanceToken, String clientToken){
        try{

            Map<String,Object> response = zapiHttpService.post("send-audio", body, instanceId, instanceToken, clientToken);

            String zaapId = response.get("zaapId") != null ? response.get("zaapId").toString() : null;
            String messageId = response.get("messageId") != null ? response.get("messageId").toString() : null;
            String id = response.get("id") != null ? response.get("id").toString() : null;

            Message message = Message.builder()
                    .type("audio")
                    .phone(body.getPhone())
                    .content(body.getAudio())
                    .zaapId(zaapId)
                    .messageId(messageId)
                    .status("sent")
                    .sentAt(new Date())
                    .metadata(Map.of(
                            "instanceId", instanceId,
                            "delayMessage", Objects.nonNull(body.getDelayMessage()) ? body.getDelayMessage() : -1,
                            "delayTyping", Objects.nonNull(body.getDelayTyping()) ? body.getDelayTyping() : -1,
                            "viewOnce", Objects.nonNull(body.getViewOnce()) ? body.getViewOnce() : false,
                            "waveform", Objects.nonNull(body.getWaveform()) ? body.getWaveform() : false))
                    .build();

            messageRepository.save(message);

            return SendAudioUseCaseOutput.builder()
                    .zaapId(zaapId)
                    .messageId(messageId)
                    .id(id)
                    .build();

        } catch (Exception e) {
            return SendAudioUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
