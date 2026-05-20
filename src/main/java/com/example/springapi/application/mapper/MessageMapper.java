package com.example.springapi.application.mapper;

import com.example.springapi.domain.entity.Message;
import com.example.springapi.infrastructure.persistence.mongo.message.MessageDocument;

public class MessageMapper {

    public static Message toDomain(MessageDocument document) {
        if (document == null) return null;

        return Message.builder()
                .id(document.getId())
                .type(document.getType())
                .phone(document.getPhone())
                .content(document.getContent())
                .zaapId(document.getZaapId())
                .messageId(document.getMessageId())
                .status(document.getStatus())
                .sentAt(document.getSentAt())
                .metadata(document.getMetadata())
                .build();
    }

    public static MessageDocument toDocument(Message message) {
        if (message == null) return null;

        return MessageDocument.builder()
                .id(message.getId())
                .type(message.getType())
                .phone(message.getPhone())
                .content(message.getContent())
                .zaapId(message.getZaapId())
                .messageId(message.getMessageId())
                .status(message.getStatus())
                .sentAt(message.getSentAt())
                .metadata(message.getMetadata())
                .build();
    }
}
