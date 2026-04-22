package com.example.springapi.domain.port;

import com.example.springapi.domain.entity.Instance;
import com.example.springapi.infrastructure.persistence.InstanceEntity;

import java.util.Optional;

public interface InstanceRepository {

    Instance save(Instance instance, Boolean exists);
    Optional<Instance> findById(String id);
    Optional<Instance> findByInstanceId(String id);
    Optional<InstanceEntity> findByInstanceIdEntity(String id);
}
