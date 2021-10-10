package com.spilkor.frame.repository.user;

import com.spilkor.frame.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User advancedSearch() {
        return new User();
    }
}
