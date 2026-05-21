package com.example.springapi.presentation.controllers.chat;

import com.example.springapi.core.boundary.output.chat.ChatMetaDataUseCaseOutput;
import com.example.springapi.domain.usecases.chats.ChatMetaDataUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatMetaDataUseCase chatMetaDataUseCase;

    @GetMapping("{instanceId}/token/{instanceToken}/chats/{phone}")
    public ResponseEntity<ChatMetaDataUseCaseOutput> getQrCode(@PathVariable String instanceId, @PathVariable String instanceToken, @PathVariable String phone,@RequestHeader("Client-token") String ClientToken){
        ChatMetaDataUseCaseOutput response = chatMetaDataUseCase.execute(instanceId, instanceToken, ClientToken, phone);

        return ResponseEntity.ok(response);
    }

}
