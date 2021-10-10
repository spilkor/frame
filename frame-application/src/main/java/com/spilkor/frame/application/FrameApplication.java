package com.spilkor.frame.application;

import com.spilkor.frame.model.BaseEntity;
import com.spilkor.frame.web.SecurityUtils;
import com.spilkor.frame.web.view.DashBoardView;
import com.spilkor.frame.web.view.LoginView;
import com.spilkor.service.ServiceHelper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.artur.helpers.LaunchUtil;


@SpringBootApplication(scanBasePackageClasses = {ServiceHelper.class})
@EntityScan(basePackageClasses = {BaseEntity.class})
@Configuration
@EnableVaadin(value = {"com.spilkor.frame.web"})
@EnableJpaRepositories("com.spilkor.frame.repository")
public class FrameApplication extends SpringBootServletInitializer implements VaadinServiceInitListener {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(FrameApplication.class, args));
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    public static String PATH_BEFORE_LOGIN_REDIRECT = "PATH_BEFORE_LOGIN_REDIRECT";

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (LoginView.class.equals(event.getNavigationTarget())){
            if (SecurityUtils.isUserLoggedIn()){
                event.forwardTo(DashBoardView.class);
            }
        } else if(!SecurityUtils.isUserLoggedIn()) {
            String pathWithParameters = event.getLocation().getPathWithQueryParameters();
            VaadinSession.getCurrent().setAttribute(PATH_BEFORE_LOGIN_REDIRECT, ".".equals(pathWithParameters) ? "" : pathWithParameters);
            event.forwardTo(LoginView.class);
        }
    }
}
