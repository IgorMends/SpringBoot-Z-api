package com.example.springapi.domain.usecases.webhook;

import com.example.springapi.core.boundary.input.ReceivedCallbackInput;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.entity.webhook.Text;
import com.example.springapi.domain.port.MessageRepository;
import com.example.springapi.infrastructure.persistence.mongo.message.MessageMongoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TextUsecase {

    private final MessageRepository messageRepository;

    public TextUsecase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void execute(ReceivedCallbackInput input){
        try{

            if(input.getText().isEmpty()){
                return;
            }

            Text text = input.getText().get();

            Map<String, Object> metadata = new HashMap<>();

            text.getDescription().ifPresent(value ->
                    metadata.put("description", value));

            text.getTitle().ifPresent(value ->
                    metadata.put("title", value));

            text.getUrl().ifPresent(value ->
                    metadata.put("url", value));

            text.getThumbnailUrl().ifPresent(value ->
                    metadata.put("thumbnailUrl", value));

            Message message = Message.builder()
                    .type("TEXT")
                    .phone(input.getPhone())
                    .content(text.getMessage())
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
