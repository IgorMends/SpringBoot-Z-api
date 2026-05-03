package com.example.springapi.domain.port;

import com.example.springapi.domain.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    Message save(Message message);

    Optional<Message> findById(String id);

    List<Message> findByPhone(String phone);

    List<Message> findAll();
}
