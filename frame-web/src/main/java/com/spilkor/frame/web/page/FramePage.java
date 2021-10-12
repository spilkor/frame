package com.spilkor.frame.web.page;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class FramePage extends VerticalLayout {

    public abstract String getPageTitle();

    public void init(){};

    public abstract void refresh();

}
