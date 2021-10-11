package com.spilkor.frame.web.view;

import com.spilkor.frame.model.dto.UserDTO;
import com.spilkor.frame.properties.VaadinSessionAttribute;
import com.spilkor.service.ServiceHelper;
import com.spilkor.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.util.StringUtils;

@PageTitle("Login")
@Route(value = "login")
public class LoginView extends VerticalLayout {

    private TextField userNameField = new TextField("User name");
    private TextField passwordField = new TextField("Password");
    private Label errorLabel = new Label("");

    public LoginView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(userNameField);

        add(passwordField);

        Button loginButton = new Button("Login");
        add(loginButton);
        loginButton.addClickListener(e -> {
            String userName = userNameField.getValue();
            String password = passwordField.getValue();
            if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)){
                addError("Missing input");
            } else {
                UserService userService = ServiceHelper.getService(UserService.class);
                UserDTO userDTO = userService.login(userName, password);
                VaadinSession.getCurrent().setAttribute(VaadinSessionAttribute.USER, userDTO);
                if (userDTO == null){
                    addError("Bad input");
                } else {
                    String pathBeforeLoginRedirect = (String) VaadinSession.getCurrent().getAttribute(VaadinSessionAttribute.PATH_BEFORE_LOGIN_REDIRECT);
                    VaadinSession.getCurrent().setAttribute(VaadinSessionAttribute.PATH_BEFORE_LOGIN_REDIRECT, null);
                    UI.getCurrent().navigate(pathBeforeLoginRedirect == null ? "" : pathBeforeLoginRedirect);
                }
            }
        });

        add(errorLabel);
        errorLabel.setVisible(false);
    }

    private void addError(String error){
        errorLabel.setText(error);
        errorLabel.setVisible(true);
    }

}
