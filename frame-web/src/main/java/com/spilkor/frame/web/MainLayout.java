package com.spilkor.frame.web;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;


@PWA(name = "My App", shortName = "My App", enableInstallPrompt = false)
@PageTitle("Main")
@Tag("vaadin-app-layout")
public class MainLayout extends VerticalLayout implements RouterLayout {

    public MainLayout() {
        add(new Button("LOGOUT"));
    }

}
