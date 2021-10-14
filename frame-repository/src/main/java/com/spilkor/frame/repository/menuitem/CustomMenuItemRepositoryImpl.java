package com.spilkor.frame.repository.menuitem;

import com.spilkor.frame.model.MenuItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomMenuItemRepositoryImpl implements CustomMenuItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public MenuItem advancedSearch() {
        return new MenuItem();
    }
}
