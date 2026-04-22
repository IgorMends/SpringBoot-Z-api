package com.example.springapi.presentation.controllers.message;

import com.example.springapi.core.boundary.input.SendAudioUseCaseInput;
import com.example.springapi.core.boundary.input.SendImageUseCaseInput;
import com.example.springapi.core.boundary.input.SendTextUseCaseInput;
import com.example.springapi.core.boundary.output.message.SendAudioUseCaseOutput;
import com.example.springapi.core.boundary.output.message.SendImageUseCaseOutput;
import com.example.springapi.core.boundary.output.message.SendTextUseCaseOutput;
import com.example.springapi.domain.usecases.messages.SendAudioUseCase;
import com.example.springapi.domain.usecases.messages.SendImageUseCase;
import com.example.springapi.domain.usecases.messages.SendTextUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final SendTextUseCase sendTextUseCase;
    private final SendImageUseCase sendImageUseCase;
    private final SendAudioUseCase sendAudioUseCase;
    private final ObjectMapper objectMapper;

    @PostMapping("/send-text")
    public ResponseEntity<SendTextUseCaseOutput> sendText(@RequestBody SendTextUseCaseInput request){

        Map<String, Object> params = objectMapper.convertValue(request, Map.class);

        SendTextUseCaseOutput response = sendTextUseCase.execute(params);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/send-image")
    public ResponseEntity<SendImageUseCaseOutput> sendImage(@RequestBody SendImageUseCaseInput request){
        Map<String, Object> params = objectMapper.convertValue(request, Map.class);

        SendImageUseCaseOutput response = sendImageUseCase.execute(params);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/send-audio")
    public ResponseEntity<SendAudioUseCaseOutput> sendAudio(@RequestBody SendAudioUseCaseInput request){
        Map<String, Object> params = objectMapper.convertValue(request, Map.class);

        SendAudioUseCaseOutput response = sendAudioUseCase.execute(params);

        return ResponseEntity.ok(response);
    }
}
