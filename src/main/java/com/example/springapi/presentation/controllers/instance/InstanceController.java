package com.example.springapi.presentation.controllers.instance;

import com.example.springapi.core.boundary.input.RenameInstanceUseCaseInput;
import com.example.springapi.core.boundary.output.instance.*;
import com.example.springapi.domain.usecases.instance.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@RestController
@RequestMapping("/instance")
@RequiredArgsConstructor
public class InstanceController {

    private final GetQrCodeUseCase getQrCodeUseCase;
    private final DisconnectUseCase disconectUseCase;
    private final RestartUseCase restartUseCase;
    private final InstanceStatusUseCase instanceStatusUseCase;
    private final InstanceDataUseCase instanceDataUseCase;
    private final DeviceDataUseCase deviceDataUseCase;
    private final RenameInstanceUseCase renameInstanceUseCase;
    private final ObjectMapper objectMapper;

    @GetMapping("/qr-code")
    public ResponseEntity<GetQrCodeUseCaseOutput> getQrCode(){
        GetQrCodeUseCaseOutput response = getQrCodeUseCase.execute();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/disconnect")
    public ResponseEntity<DisconnectUseCaseOutput> disconnectInstance(){
        DisconnectUseCaseOutput response = disconectUseCase.execute();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/restart")
    public ResponseEntity<RestartUseCaseOutput> restartInstance(){
        RestartUseCaseOutput response = restartUseCase.execute();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/status")
    public ResponseEntity<InstanceStatusUseCaseOutput> instanceStatus(){
        InstanceStatusUseCaseOutput response = instanceStatusUseCase.execute();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<InstanceDataUseCaseOutput> instanceData(){
        InstanceDataUseCaseOutput response = instanceDataUseCase.execute();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/device")
    public ResponseEntity<DeviceDataUseCaseOutput> deviceData(){
        DeviceDataUseCaseOutput response = deviceDataUseCase.execute();

        return ResponseEntity.ok(response);
    }

    @PutMapping("update-name")
    public ResponseEntity<RenameInstanceUseCaseOutput> renameInstance(@RequestBody RenameInstanceUseCaseInput input){
        Map<String, Object> body = objectMapper.convertValue(input, Map.class);

        RenameInstanceUseCaseOutput response = renameInstanceUseCase.execute(body);

        return ResponseEntity.ok(response);
    }

}
