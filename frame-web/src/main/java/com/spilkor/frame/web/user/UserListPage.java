package com.spilkor.frame.web.user;

import com.spilkor.frame.model.User;
import com.spilkor.frame.web.page.FramePage;
import com.spilkor.frame.web.page.StaticPage;
import com.spilkor.service.ServiceHelper;
import com.spilkor.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.selection.SelectionListener;

import java.util.ArrayList;
import java.util.List;

@StaticPage(pageId = "userlist")
public class UserListPage extends FramePage {

    private final UserService userService = ServiceHelper.getService(UserService.class);
    private UserEditComponent userEditComponent = null;

    public UserListPage() {
        List<User> users = new ArrayList<>(userService.findAll());

        Grid<User> grid = new Grid<>(User.class);
        grid.setItems(users);
        grid.setColumns(
                "id",
                "entityDescriptorId",
                "state",
                "userName",
                "password"
        );
        add(grid);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener((SelectionListener<Grid<User>, User>) event -> {
            if (userEditComponent != null){
                remove(userEditComponent);
            }
            userEditComponent = null;
            User user = event.getAllSelectedItems().stream().findFirst().orElse(null);
            if (user != null){
                userEditComponent = new UserEditComponent(user, grid);
                add(userEditComponent);
            }
        });
    }

    @Override
    public String getPageTitle() {
        return "User List";
    }

    @Override
    public void refresh() {

    }

    private class UserEditComponent extends VerticalLayout {
        public UserEditComponent(User user, Grid<User> grid) {
            TextField userNameField = new TextField("User name");
            userNameField.setValue(user.getUserName());
            add(userNameField);

            TextField passwordField = new TextField("Password");
            passwordField.setValue(user.getPassword());
            add(passwordField);

            Button saveButton = new Button("Save");
            saveButton.addClickListener(event -> {
                user.setUserName(userNameField.getValue());
                user.setPassword(passwordField.getValue());
                userService.save(user);
                grid.getDataProvider().refreshAll();
            });
            add(saveButton);
        }
    }
}