package com.spilkor.frame.web.user;

import com.spilkor.frame.model.User;
import com.spilkor.frame.web.page.AbstractBaseEntityPage;
import com.spilkor.frame.web.page.EntityPage;
import com.vaadin.flow.component.html.Label;

@EntityPage(entityDescriptorId = "User", entityClass = User.class)
public class UserPage<T extends User> extends AbstractBaseEntityPage<T> {

    public UserPage(T entity) {
        super(entity);
    }

    @Override
    public String getPageTitle() {
        return "User";
    }

    @Override
    public void init() {
        super.init();

        add(new Label(getEntity().getUserName()));
    }

    @Override
    public void refresh() {

    }

}