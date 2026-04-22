package com.example.springapi.core.boundary.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendAudioUseCaseInput {

    private String phone;
    private String audio;
    private Integer delayMessage;
    private Integer delayTyping;
    private Boolean viewOnce;
    private Boolean waveform;
}
