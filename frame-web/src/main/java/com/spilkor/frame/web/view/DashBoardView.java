package com.spilkor.frame.web.view;

import com.spilkor.frame.model.User;
import com.spilkor.frame.repository.user.UserRepository;
import com.spilkor.frame.web.MainLayout;
import com.spilkor.service.ServiceHelper;
import com.spilkor.service.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Dashboard")
@Route(value = "", layout = MainLayout.class)
public class DashBoardView extends VerticalLayout {

    private final UserService userService = ServiceHelper.getService(UserService.class);

    public DashBoardView() {
        add(new Label("DASHBOARD"));

        List<User> users = new ArrayList<>(userService.findAll());

        Grid<User> grid = new Grid<>(User.class);
        grid.setItems(users);

        //grid.removeColumnByKey("id");

        grid.setColumns(
                "id",
                "entityDescriptorId",
                "state",
                "userName",
                "password"
        );

        add(grid);
    }

}
