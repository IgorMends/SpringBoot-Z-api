package com.example.springapi.core.boundary.output.instance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstanceDataUseCaseOutput {

    private String id;
    private String token;
    private String name;
    private Boolean connected;
    private String created;
    private Long due;
    private String paymentStatus;
    private String callRejectMessage;
    private Boolean callRejectAuto;
    private Boolean autoReadMessage;
    private Boolean receiveCallbackSentByMe;
    private String receivedAndDeliveryCallbackUrl;
    private String presenceChatCallbackUrl;
    private String disconnectedCallbackUrl;
    private String deliveryCallbackUrl;
    private String connectedCallbackUrl;
    private String messageStatusCallbackUrl;
    private String receivedCallbackUrl;
    private String error;
}
