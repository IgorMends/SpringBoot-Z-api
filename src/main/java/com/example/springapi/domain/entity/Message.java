package com.example.springapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String id;
    private String type;
    private String phone;
    private String content;
    private String zaapId;
    private String messageId;
    private String status;
    private Date sentAt;
    private Map<String, Object> metadata;
}
