package com.example.springapi.domain.usecases.webhook;

import com.example.springapi.core.boundary.input.ReceivedCallbackInput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.entity.webhook.Audio;
import com.example.springapi.domain.port.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AudioUseCase {

    private final MessageRepository messageRepository;

    public AudioUseCase(MessageRepository messageRepository){this.messageRepository = messageRepository;}

    public void execute(ReceivedCallbackInput input){
        try{

            if(input.getAudio().isEmpty()){
                return;
            }

            Audio audio = input.getAudio().get();

            Map<String, Object> metadata = new HashMap<>();

            audio.getPtt().ifPresent(value ->
                    metadata.put("ptt", value));

            audio.getSeconds().ifPresent(value ->
                    metadata.put("seconds", value));

            audio.getSeconds().ifPresent(value ->
                    metadata.put("mimeType", value));

            audio.getViewOnce().ifPresent(value->
                    metadata.put("viewOnce", value));

            Message message = Message.builder()
                    .type("AUDIO")
                    .phone(input.getPhone())
                    .content(audio.getAudioUrl())
                    .zaapId(input.getInstanceId())
                    .messageId(input.getMessageId())
                    .status(input.getStatus().orElse("RECEIVED"))
                    .sentAt(
                            input.getMomment()
                                    .map(Date::new)
                                    .orElse(new Date())
                    )
                    .metadata(metadata)
                    .build();

            messageRepository.save(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
