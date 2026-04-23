package com.example.springapi.core.boundary.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendCallUseCaseInput {

    private String phone;
    private String callAudioUrl;
}
