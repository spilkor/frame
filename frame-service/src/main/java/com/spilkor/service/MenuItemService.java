package com.spilkor.service;

import com.spilkor.frame.model.MenuItem;
import com.spilkor.frame.model.User;
import com.spilkor.frame.repository.menuitem.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuItemService extends FrameService {

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }


    public MenuItem findById(String id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    public void save(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

}
