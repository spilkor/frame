package com.spilkor.frame.web.view;

import com.spilkor.frame.model.dto.UserDTO;
import com.spilkor.service.ServiceHelper;
import com.spilkor.service.UserService;
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
        add(userNameField);
        add(passwordField);
        Button loginButton = new Button("login");
        add(loginButton);
        add(errorLabel);
        errorLabel.setVisible(false);

        loginButton.addClickListener(e -> {
            String userName = userNameField.getValue();
            String password = passwordField.getValue();

            if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)){
                addError("Missing input");
            } else {
                UserService userService = ServiceHelper.getService(UserService.class);
                UserDTO userDTO = userService.login(userName, password);
                VaadinSession.getCurrent().setAttribute("USER", userDTO);
                if (userDTO == null){
                    addError("Bad input");
                } else {
                    String PATH_BEFORE_LOGIN_REDIRECT = (String) VaadinSession.getCurrent().getAttribute("PATH_BEFORE_LOGIN_REDIRECT");
                    VaadinSession.getCurrent().setAttribute("PATH_BEFORE_LOGIN_REDIRECT", null);
                    loginButton.getUI().ifPresent(ui -> ui.navigate(PATH_BEFORE_LOGIN_REDIRECT == null ? "" : PATH_BEFORE_LOGIN_REDIRECT));
                }
            }
        });

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);


    }

    private void addError(String error){
        errorLabel.setText(error);
        errorLabel.setVisible(true);
    }

}
