package com.example.springapi.domain.port;

import com.example.springapi.domain.entity.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatRepository {

    Chat save(Chat chat);

    Optional<Chat> findById(String id);

    Optional<Chat> findByPhone(String phone);

    List<Chat> findAll();
}
