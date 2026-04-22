package com.example.springapi.core.boundary.output.instance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisconnectUseCaseOutput {

    private String value;
    private String error;
}
