package com.example.springapi.infrastructure.persistence.mongo.chat;

import com.example.springapi.infrastructure.persistence.mongo.message.MessageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMongoRepository extends MongoRepository<ChatDocument, String> {

    Optional<ChatDocument> findByPhone(String phone);

}
