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

    @GetMapping("{instanceId}/token/{instanceToken}/qr-code")
    public ResponseEntity<GetQrCodeUseCaseOutput> getQrCode(@PathVariable String instanceId, @PathVariable String instanceToken){
        GetQrCodeUseCaseOutput response = getQrCodeUseCase.execute(instanceId, instanceToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{instanceId}/token/{instanceToken}/disconnect")
    public ResponseEntity<DisconnectUseCaseOutput> disconnectInstance(@PathVariable String instanceId, @PathVariable String instanceToken){
        DisconnectUseCaseOutput response = disconectUseCase.execute(instanceId, instanceToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{instanceId}/token/{instanceToken}/restart")
    public ResponseEntity<RestartUseCaseOutput> restartInstance(@PathVariable String instanceId, @PathVariable String instanceToken){
        RestartUseCaseOutput response = restartUseCase.execute(instanceId, instanceToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{instanceId}/token/{instanceToken}/status")
    public ResponseEntity<InstanceStatusUseCaseOutput> instanceStatus(@PathVariable String instanceId, @PathVariable String instanceToken){
        InstanceStatusUseCaseOutput response = instanceStatusUseCase.execute(instanceId, instanceToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{instanceId}/token/{instanceToken}/me")
    public ResponseEntity<InstanceDataUseCaseOutput> instanceData(@PathVariable String instanceId, @PathVariable String instanceToken){
        InstanceDataUseCaseOutput response = instanceDataUseCase.execute(instanceId, instanceToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{instanceId}/token/{instanceToken}/device")
    public ResponseEntity<DeviceDataUseCaseOutput> deviceData(@PathVariable String instanceId, @PathVariable String instanceToken){
        DeviceDataUseCaseOutput response = deviceDataUseCase.execute(instanceId, instanceToken);

        return ResponseEntity.ok(response);
    }

    @PutMapping("update-name")
    public ResponseEntity<RenameInstanceUseCaseOutput> renameInstance(@RequestBody RenameInstanceUseCaseInput input){
        Map<String, Object> body = objectMapper.convertValue(input, Map.class);

        RenameInstanceUseCaseOutput response = renameInstanceUseCase.execute(body);

        return ResponseEntity.ok(response);
    }

}
