package com.example.springapi.core.boundary.output.instance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestartUseCaseOutput {

    private Boolean value;
    private String error;
}
