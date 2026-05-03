package com.example.springapi.infrastructure.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageMongoRepository extends MongoRepository<MessageDocument, String> {

    List<MessageDocument> findByPhone(String phone);
}
