package com.example.springapi.core.boundary.output.instance;

import com.example.springapi.domain.entity.DeviceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDataUseCaseOutput {

    private String phone;
    private String lid;
    private String imgUrl;
    private String about;
    private String name;
    private DeviceEntity device;
    private String originalDevice;
    private Integer sessionId;
    private Boolean isBusiness;
    private String error;
}
