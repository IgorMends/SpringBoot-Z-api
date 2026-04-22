package com.example.springapi.infrastructure.persistence;

import com.example.springapi.application.mapper.InstanceMapper;
import com.example.springapi.domain.entity.Instance;
import com.example.springapi.domain.port.InstanceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InstanceRepositoryImpl implements InstanceRepository {

    private final SpringDataInstanceRepository springDataInstanceRepository;

    public InstanceRepositoryImpl(SpringDataInstanceRepository springDataInstanceRepository){
        this.springDataInstanceRepository = springDataInstanceRepository;
    }

    @Override
    public Instance save(Instance instance, Boolean exists){
        if(exists){
            Optional<InstanceEntity> entity = findByInstanceIdEntity(instance.getId());
            InstanceEntity existing = entity.get();

            existing.setName(instance.getName());
            existing.setPaymentStatus(instance.getPaymentStatus());
            existing.setConnectionStatus(instance.getConnectionStatus());

            InstanceEntity saved = springDataInstanceRepository.save(existing);
            return InstanceMapper.toDomain(saved);
        }else {
            InstanceEntity entity = InstanceMapper.toEntity(instance);
            InstanceEntity saved = springDataInstanceRepository.save(entity);
            return InstanceMapper.toDomain(saved);
        }
    }

    @Override
    public Optional<Instance> findById(String id){
        return springDataInstanceRepository.findById(id).map(InstanceMapper::toDomain);
    }

    @Override public Optional<Instance> findByInstanceId(String id){
        return springDataInstanceRepository.findByInstanceId(id).map(InstanceMapper::toDomain);
    }

    @Override public Optional<InstanceEntity> findByInstanceIdEntity(String id){
        return springDataInstanceRepository.findByInstanceId(id);
    }
}
