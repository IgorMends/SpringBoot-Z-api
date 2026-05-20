package com.example.springapi.application.mapper;

import com.example.springapi.domain.entity.Chat;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.infrastructure.persistence.mongo.chat.ChatDocument;
import com.example.springapi.infrastructure.persistence.mongo.message.MessageDocument;

import java.util.Map;

public class ChatMapper {

    public static Chat toDomain(ChatDocument document) {
        if (document == null) return null;

        return Chat.builder()
                .phone(document.getPhone())
                .name(document.getName())
                .lid(document.getLid())
                .isGroup(document.getIsGroup())
                .isGroupAnnouncement(document.getIsGroupAnnouncement())
                .archived(document.getArchived())
                .pinned(document.getPinned())
                .profileThumbnail(document.getProfileThumbnail())
                .isMuted(document.getIsMuted())
                .isMarkedSpam(document.getIsMarkedSpam())
                .tags(document.getTags())
                .build();
    }

    public static ChatDocument toDocument(Chat chat) {
        if (chat == null) return null;

        return ChatDocument.builder()
                .id(null)
                .phone(chat.getPhone())
                .name(chat.getName())
                .lid(chat.getLid())
                .isGroup(chat.getIsGroup())
                .isGroupAnnouncement(chat.getIsGroupAnnouncement())
                .archived(chat.getArchived())
                .pinned(chat.getPinned())
                .profileThumbnail(chat.getProfileThumbnail())
                .isMuted(chat.getIsMuted())
                .isMarkedSpam(chat.getIsMarkedSpam())
                .tags(chat.getTags())
                .build();
    }
}
