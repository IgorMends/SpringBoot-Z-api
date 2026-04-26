package com.example.springapi.domain.usecases.instance;

import com.example.springapi.core.boundary.output.instance.DeviceDataUseCaseOutput;
import com.example.springapi.domain.entity.DeviceEntity;
import com.example.springapi.domain.entity.Instance;
import com.example.springapi.services.ZapiHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeviceDataUseCase {

    private final ZapiHttpService zapiHttpService;

    public DeviceDataUseCaseOutput execute(String instanceId, String instanceToken){
        try{

            Map<String, Object> response = zapiHttpService.get("device", instanceId, instanceToken);

            Map<String, Object> deviceMap = (Map<String, Object>) response.get("device");
            DeviceEntity deviceEntity= new DeviceEntity();

            if(StringUtils.hasText(deviceMap.get("sessionName").toString())){
                deviceEntity.setSessionName(deviceMap.get("sessionName").toString());
            }else {
                deviceEntity.setSessionName("");
            }

            if(StringUtils.hasText(deviceMap.get("device_model").toString())){
                deviceEntity.setDeviceModel(deviceMap.get("device_model").toString());
            }else {
                deviceEntity.setDeviceModel("");
            }

            DeviceDataUseCaseOutput output = DeviceDataUseCaseOutput.builder()
                    .phone(response.get("phone") != null ? response.get("phone").toString() : null)
                    .lid(response.get("lid") != null ? response.get("lid").toString() : null)
                    .imgUrl(response.get("imgUrl") != null ? response.get("imgUrl").toString() : null)
                    .about(response.get("about") != null ? response.get("about").toString() : null)
                    .name(response.get("name") != null ? response.get("name").toString() : null)
                    .device(deviceEntity)
                    .originalDevice(response.get("originalDevice") != null ? response.get("originalDevice").toString() : null)
                    .sessionId(response.get("sessionId") != null ? Integer.parseInt(response.get("sessionId").toString()) : null)
                    .isBusiness(response.get("isBusiness") != null ? (Boolean) response.get("isBusiness") : null)
                    .build();

            return output;

        }catch (Exception e){
            return DeviceDataUseCaseOutput.builder().error(e.getMessage()).build();
        }
    }
}
