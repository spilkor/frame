package com.spilkor.frame.web.menuitem;

import com.spilkor.frame.model.MenuItem;
import com.spilkor.frame.model.User;
import com.spilkor.frame.web.page.FramePage;
import com.spilkor.frame.web.page.StaticPage;
import com.spilkor.service.MenuItemService;
import com.spilkor.service.ServiceHelper;
import com.spilkor.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.SelectionListener;

import java.util.ArrayList;
import java.util.List;

@StaticPage(pageId = "menu-item-list")
public class MenuItemListPage extends FramePage {

    private final MenuItemService menuItemService = ServiceHelper.getService(MenuItemService.class);
    private MenuItemEditComponent menuItemEditComponent = null;

    public MenuItemListPage() {
        List<MenuItem> menuItems = new ArrayList<>(menuItemService.findAll());

        Grid<MenuItem> grid = new Grid<>(MenuItem.class);
        grid.setItems(menuItems);
        grid.setColumns(
                "id",
                "entityDescriptorId",
                "state",
                "pageId",
                "text"
        );
        add(grid);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener((SelectionListener<Grid<MenuItem>, MenuItem>) event -> {
            if (menuItemEditComponent != null){
                remove(menuItemEditComponent);
            }
            menuItemEditComponent = null;
            MenuItem menuItem = event.getAllSelectedItems().stream().findFirst().orElse(null);
            if (menuItem != null){
                menuItemEditComponent = new MenuItemEditComponent(menuItem, grid);
                add(menuItemEditComponent);
            }
        });
    }

    @Override
    public String getPageTitle() {
        return "Menu item List";
    }

    @Override
    public void refresh() {

    }

    private class MenuItemEditComponent extends VerticalLayout {
        public MenuItemEditComponent(MenuItem menuItem, Grid<MenuItem> grid) {
            TextField parentIdField = new TextField("Parent Id");
            parentIdField.setValue(menuItem.getPageId());
            add(parentIdField);

            TextField pageIdField = new TextField("Page id");
            pageIdField.setValue(menuItem.getPageId());
            add(pageIdField);

            TextField textField = new TextField("Text");
            textField.setValue(menuItem.getText());
            add(textField);


            Button saveButton = new Button("Save");
            saveButton.addClickListener(event -> {
                menuItem.setPageId(pageIdField.getValue());
                menuItem.setText(textField.getValue());
                menuItem.setParentId(parentIdField.getValue());
                menuItemService.save(menuItem);
                grid.getDataProvider().refreshAll();
            });
            add(saveButton);
        }
    }
}