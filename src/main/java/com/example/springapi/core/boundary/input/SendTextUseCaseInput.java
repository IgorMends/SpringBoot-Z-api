package com.example.springapi.core.boundary.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendTextUseCaseInput {
    private String phone;
    private String message;
    private Integer delayMessage;
    private Integer delayTyping;
    private String editMessage;
}
