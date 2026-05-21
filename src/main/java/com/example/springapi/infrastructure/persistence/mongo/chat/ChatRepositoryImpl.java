package com.example.springapi.infrastructure.persistence.mongo.chat;

import com.example.springapi.application.mapper.ChatMapper;
import com.example.springapi.domain.entity.Chat;
import com.example.springapi.domain.port.ChatRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ChatRepositoryImpl implements ChatRepository {

    private final ChatMongoRepository mongoRepository;

    public ChatRepositoryImpl(ChatMongoRepository chatRepository){this.mongoRepository = chatRepository;}

    @Override
    public Chat save(Chat chat){
        ChatDocument document = ChatMapper.toDocument(chat);
        ChatDocument saved = mongoRepository.save(document);
        return ChatMapper.toDomain(saved);
    }

    @Override
    public Optional<Chat> findById(String id){
        return mongoRepository.findById(id)
                .map(ChatMapper::toDomain);
    }

    @Override
    public Optional<Chat> findByPhone(String phone){
        return mongoRepository.findByPhone(phone)
                .map(ChatMapper::toDomain);
    }

    @Override
    public List<Chat> findAll() {
        return mongoRepository.findAll()
                .stream()
                .map(ChatMapper::toDomain)
                .collect(Collectors.toList());
    }
}
