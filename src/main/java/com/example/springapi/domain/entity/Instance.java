package com.example.springapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Instance {

    private String id;
    private String name;
    private Date created;
    private String paymentStatus;
    private Boolean connectionStatus;
    private Boolean autoReadMessage;
    private Boolean callRejectAuto;
    private String receivedCallbackUrl;
}
