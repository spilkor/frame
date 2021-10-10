package com.spilkor.frame.repository.entitydescriptor;

import com.spilkor.frame.model.EntityDescriptor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomEntityDescriptorRepositoryImpl implements CustomEntityDescriptorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public EntityDescriptor advancedSearch() {
        return new EntityDescriptor();
    }
}
