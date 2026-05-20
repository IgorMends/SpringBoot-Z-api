package com.example.springapi.infrastructure.persistence.mongo.message;

import com.example.springapi.application.mapper.MessageMapper;
import com.example.springapi.domain.entity.Message;
import com.example.springapi.domain.port.MessageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageMongoRepository mongoRepository;

    public MessageRepositoryImpl(MessageMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Message save(Message message) {
        MessageDocument document = MessageMapper.toDocument(message);
        MessageDocument saved = mongoRepository.save(document);
        return MessageMapper.toDomain(saved);
    }

    @Override
    public Optional<Message> findById(String id) {
        return mongoRepository.findById(id)
                .map(MessageMapper::toDomain);
    }

    @Override
    public List<Message> findByPhone(String phone) {
        return mongoRepository.findByPhone(phone)
                .stream()
                .map(MessageMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> findAll() {
        return mongoRepository.findAll()
                .stream()
                .map(MessageMapper::toDomain)
                .collect(Collectors.toList());
    }
}
