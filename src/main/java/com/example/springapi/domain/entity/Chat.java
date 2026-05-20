package com.example.springapi.domain.entity;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

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
