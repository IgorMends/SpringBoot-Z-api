package com.example.springapi.infrastructure.persistence.mongo.chat;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "chats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDocument {

    @Id
    private String id;
    private String phone;
    private String name;
    private String lid;
    private Boolean isGroup;
    private Boolean isGroupAnnouncement;
    private String archived;
    private String pinned;
    private String profileThumbnail;
    private String isMuted;
    private String isMarkedSpam;
    private Map<Object, String> tags;
}
