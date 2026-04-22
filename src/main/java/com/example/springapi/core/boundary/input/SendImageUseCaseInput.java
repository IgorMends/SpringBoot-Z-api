package com.example.springapi.core.boundary.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendImageUseCaseInput {

    private String phone;
    private String image;
    private String caption;
    private Integer delayMessage;
    private Boolean viewOnce;
}
