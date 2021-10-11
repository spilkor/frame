package com.spilkor.frame.web;

import com.spilkor.frame.properties.VaadinSessionAttribute;
import com.spilkor.frame.web.view.DashBoardView;
import com.spilkor.frame.web.view.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

@Component
public class FrameServiceInitializer implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (LoginView.class.equals(event.getNavigationTarget())){
            if (SecurityUtils.isUserLoggedIn()){
                event.forwardTo(DashBoardView.class);
            }
        } else if(!SecurityUtils.isUserLoggedIn()) {
            String pathWithParameters = event.getLocation().getPathWithQueryParameters();
            VaadinSession.getCurrent().setAttribute(VaadinSessionAttribute.PATH_BEFORE_LOGIN_REDIRECT, ".".equals(pathWithParameters) ? "" : pathWithParameters);
            event.forwardTo(LoginView.class);
        }
    }
}
