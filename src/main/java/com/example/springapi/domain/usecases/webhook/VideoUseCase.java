package com.example.springapi.domain.usecases.webhook;

import com.example.springapi.core.boundary.input.ReceivedCallbackInput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.entity.webhook.Video;
import com.example.springapi.domain.port.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class VideoUseCase {

    private final MessageRepository messageRepository;

    public VideoUseCase(MessageRepository messageRepository){this.messageRepository = messageRepository;}

    public void execute(ReceivedCallbackInput input){
        try{

            if(input.getVideo().isEmpty()){
                return;
            }

            Video video = input.getVideo().get();

            Map<String, Object> metadata = new HashMap<>();

            video.getCaption().ifPresent(value ->
                    metadata.put("caption", value));

            video.getMimeType().ifPresent(value ->
                    metadata.put("mimeType", value));

            video.getWidth().ifPresent(value ->
                    metadata.put("width", value));

            video.getHeight().ifPresent(value ->
                    metadata.put("height", value));

            video.getViewOnce().ifPresent(value ->
                    metadata.put("viewOnce", value));

            video.getSeconds().ifPresent(value ->
                    metadata.put("seconds", value));

            video.getIsGif().ifPresent(value ->
                    metadata.put("isGif", value));

            Message message = Message.builder()
                    .type("VIDEO")
                    .phone(input.getPhone())
                    .content(video.getVideoUrl())
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
