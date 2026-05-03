package com.example.springapi.infrastructure.persistence.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Document(collection = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDocument {

    @Id
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
