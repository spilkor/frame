package com.spilkor.frame.web.view;

import com.spilkor.frame.web.MainLayout;
import com.vaadin.flow.router.*;

import javax.servlet.http.HttpServletResponse;

@ParentLayout(MainLayout.class)
public class InternalServerErrorView extends InternalServerError {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<Exception> parameter) {
        getElement().setText("Internal server error!");
        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }

}