package com.spilkor.frame.repository.frameentity;

import com.spilkor.frame.model.FrameEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomFrameEntityRepositoryImpl implements CustomFrameEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <CLAZZ extends FrameEntity> CLAZZ find(Class<CLAZZ> clazz, String id) {
        return entityManager.find(clazz, id);
    }

}
