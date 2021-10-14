package com.spilkor.frame.repository.menuitem;

import com.spilkor.frame.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, String>, CustomMenuItemRepository {

}