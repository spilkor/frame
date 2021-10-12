package com.spilkor.frame.web.view;

import com.spilkor.frame.web.MainLayout;
import com.spilkor.frame.web.SecurityUtils;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dashboard")
@Route(value = "", layout = MainLayout.class)
public class DashBoardView extends VerticalLayout {

    public DashBoardView() {
        Label welcomeLabel = new Label("Welcome " + SecurityUtils.getUser().getName() + "!");
        add(welcomeLabel);
    }
}
