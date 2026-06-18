package com.example.springapi.domain.usecases.webhook;

import com.example.springapi.core.boundary.input.ReceivedCallbackInput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.entity.webhook.Image;
import com.example.springapi.domain.port.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ImageUseCase {

    private final MessageRepository messageRepository;

    public ImageUseCase(MessageRepository messageRepository) {this.messageRepository = messageRepository;}

    public void execute(ReceivedCallbackInput input){
        try {
            if (!input.getImage().isPresent()) {
                return;
            }

            Image image = input.getImage().get();

            Map<String, Object> metadata = new HashMap<>();

            image.getMimeType().ifPresent(value ->
                    metadata.put("mimeType", value));

            image.getDownloadError().ifPresent(value ->
                    metadata.put("downloadError", value)
            );

            image.getThumbnailUrl().ifPresent(value ->
                    metadata.put("thumbnailUrl", value)
            );

            image.getCaption().ifPresent(value ->
                    metadata.put("caption", value)
            );

            image.getWidth().ifPresent(value ->
                    metadata.put("width", value)
            );

            image.getHeight().ifPresent(value ->
                    metadata.put("height", value)
            );

            image.getViewOnce().ifPresent(value ->
                    metadata.put("viewOnce", value)
            );

            Message message = Message.builder()
                    .type("IMAGE")
                    .phone(input.getPhone())
                    .content(image.getImageUrl())
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
