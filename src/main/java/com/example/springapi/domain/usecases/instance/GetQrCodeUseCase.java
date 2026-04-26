package com.example.springapi.domain.usecases.instance;


import com.example.springapi.core.boundary.output.instance.GetQrCodeUseCaseOutput;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetQrCodeUseCase {

    private final ZapiHttpService zapiHttpService;

    public GetQrCodeUseCaseOutput execute(String instanceId, String instanceToken){
        try{
            Map<String, Object> response= zapiHttpService.get("qr-code/image",instanceId, instanceToken);

            return GetQrCodeUseCaseOutput.builder()
                    .value(response.get("value") != null ? response.get("value").toString() : null)
                    .image(response.get("image") != null ? response.get("image").toString() : null)
                    .status(response.get("status") != null ? response.get("status").toString() : null)
                    .build();
        } catch (Exception e) {
            return GetQrCodeUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
