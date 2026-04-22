package com.example.springapi.infrastructure.persistence;

import com.example.springapi.domain.entity.Instance;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataInstanceRepository extends CrudRepository<InstanceEntity, String> {

    Optional<InstanceEntity> findByInstanceId(String id);
}
