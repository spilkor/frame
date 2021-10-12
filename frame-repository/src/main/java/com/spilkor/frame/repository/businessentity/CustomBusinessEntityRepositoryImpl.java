package com.spilkor.frame.repository.businessentity;

import com.spilkor.frame.model.BusinessEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomBusinessEntityRepositoryImpl implements CustomBusinessEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <CLAZZ extends BusinessEntity> CLAZZ find(Class<CLAZZ> clazz, Long id) {
        return entityManager.find(clazz, id);
    }

}
