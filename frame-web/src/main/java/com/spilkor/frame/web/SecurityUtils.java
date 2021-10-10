package com.spilkor.frame.web;

import com.spilkor.frame.model.dto.UserDTO;
import com.vaadin.flow.server.VaadinSession;

public class SecurityUtils {

    private SecurityUtils(){

    }

    public static UserDTO getUser(){
        return (UserDTO) VaadinSession.getCurrent().getAttribute("USER");
    }

    public static boolean isUserLoggedIn(){
        return getUser() != null;
    }

}
