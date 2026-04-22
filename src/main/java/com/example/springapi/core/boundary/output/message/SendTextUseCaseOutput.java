package com.example.springapi.core.boundary.output.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendTextUseCaseOutput {

    private String zaapId;
    private String messageId;
    private String id;
    private String error;
}
