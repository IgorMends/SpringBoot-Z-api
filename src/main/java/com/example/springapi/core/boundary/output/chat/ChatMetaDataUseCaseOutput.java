package com.example.springapi.core.boundary.output.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMetaDataUseCaseOutput {

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
