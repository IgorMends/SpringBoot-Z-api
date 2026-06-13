package com.example.springapi.presentation.controllers.message;

import com.example.springapi.core.boundary.input.*;
import com.example.springapi.core.boundary.output.message.SendAudioUseCaseOutput;
import com.example.springapi.core.boundary.output.message.SendCallUseCaseOutput;
import com.example.springapi.core.boundary.output.message.SendImageUseCaseOutput;
import com.example.springapi.core.boundary.output.message.SendTextUseCaseOutput;
import com.example.springapi.domain.interactor.webhook.ReceivedCallBackInteractor;
import com.example.springapi.domain.usecases.messages.SendAudioUseCase;
import com.example.springapi.domain.usecases.messages.SendCallUseCase;
import com.example.springapi.domain.usecases.messages.SendImageUseCase;
import com.example.springapi.domain.usecases.messages.SendTextUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final SendTextUseCase sendTextUseCase;
    private final SendImageUseCase sendImageUseCase;
    private final SendAudioUseCase sendAudioUseCase;
    private final SendCallUseCase sendCallUseCase;
    private final ReceivedCallBackInteractor receivedCallBackInteractor;
    private final ObjectMapper objectMapper;

    @PostMapping("{instanceId}/token/{instanceToken}/send-text")
    public ResponseEntity<SendTextUseCaseOutput> sendText(@RequestBody SendTextUseCaseInput body, @PathVariable String instanceId, @PathVariable String instanceToken, @RequestHeader("Client-token") String ClientToken){

        SendTextUseCaseOutput response = sendTextUseCase.execute(body, instanceId, instanceToken, ClientToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("{instanceId}/token/{instanceToken}/send-image")
    public ResponseEntity<SendImageUseCaseOutput> sendImage(@RequestBody SendImageUseCaseInput body, @PathVariable String instanceId, @PathVariable String instanceToken, @RequestHeader("Client-token") String ClientToken){

        SendImageUseCaseOutput response = sendImageUseCase.execute(body, instanceId, instanceToken, ClientToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("{instanceId}/token/{instanceToken}/send-audio")
    public ResponseEntity<SendAudioUseCaseOutput> sendAudio(@RequestBody SendAudioUseCaseInput body, @PathVariable String instanceId, @PathVariable String instanceToken, @RequestHeader("Client-token") String ClientToken){

        SendAudioUseCaseOutput response = sendAudioUseCase.execute(body, instanceId, instanceToken, ClientToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("{instanceId}/token/{instanceToken}/send-call")
    public ResponseEntity<SendCallUseCaseOutput> sendCall(@RequestBody SendCallUseCaseInput body, @PathVariable String instanceId, @PathVariable String instanceToken, @RequestHeader("Client-token") String ClientToken){

        SendCallUseCaseOutput response = sendCallUseCase.execute(body, instanceId, instanceToken, ClientToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("{instanceId}/token/{instanceToken}/receivedCallback")
    public ResponseEntity<Void> receivedCallback(@RequestBody ReceivedCallbackInput body){

        this.receivedCallBackInteractor.execute(body);

        return ResponseEntity.ok().build();
    }
}

