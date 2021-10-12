package com.spilkor.frame.web;

import com.spilkor.frame.properties.VaadinSessionAttribute;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;


@PWA(name = "Frame Application", shortName = "Frame App", enableInstallPrompt = false)
@Tag("vaadin-app-layout")
public class MainLayout extends VerticalLayout implements RouterLayout {

    public MainLayout() {
        Button logoutButton = new Button("Logout");
        logoutButton.addClickListener(event -> {
            VaadinSession.getCurrent().setAttribute(VaadinSessionAttribute.USER, null);
            UI.getCurrent().getPage().reload();
        });
        add(logoutButton);
    }

}
