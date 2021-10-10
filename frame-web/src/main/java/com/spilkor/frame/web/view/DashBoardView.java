package com.spilkor.frame.web.view;

import com.spilkor.frame.web.MainLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Dashboard")
@Route(value = "", layout = MainLayout.class)
public class DashBoardView extends HorizontalLayout {

    public DashBoardView() {
        add(new Label("DASHBOARD"));
    }

}
