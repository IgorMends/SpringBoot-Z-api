package com.example.springapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Instance {

    private String id;
    private String name;
    private String paymentStatus;
    private String connectionStatus;
}
