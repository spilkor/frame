package com.spilkor.frame.web;

import com.spilkor.frame.properties.VaadinSessionAttribute;
import com.spilkor.frame.web.user.UserListPage;
import com.spilkor.frame.web.view.DashBoardView;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;

import java.util.ArrayList;
import java.util.List;


@PWA(name = "Frame Application", shortName = "Frame App", enableInstallPrompt = false)
@Tag("vaadin-app-layout")
@Theme(themeFolder = "myapp")
public class MainLayout extends AppLayout implements RouterLayout  {

    public static class MenuItemInfo {

        private String text;
        private String navigationTarget;

        public MenuItemInfo(String text, String navigationTarget) {
            this.text = text;
            this.navigationTarget = navigationTarget;
        }

        public String getText() {
            return text;
        }

        public String getNavigationTarget() {
            return navigationTarget;
        }
    }

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassName("text-secondary");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("m-0", "text-l");

        Header header = new Header(toggle, viewTitle);
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center",
                "w-full");

        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("Frame App");
        appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
        nav.getElement().setAttribute("aria-labelledby", "views");

        H3 views = new H3("Views");
        views.addClassNames("flex", "h-m", "items-center", "mx-m", "my-0", "text-s", "text-tertiary");
        views.setId("views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("list-none", "m-0", "p-0");
        nav.add(list);

        for (Button link : createLinks()) {
            ListItem item = new ListItem(link);
            list.add(item);
        }
        return nav;
    }

    private List<Button> createLinks() {
        MenuItemInfo[] menuItems = new MenuItemInfo[]{
                new MenuItemInfo("Dashboard", ""),
                new MenuItemInfo("User list", "page/user-list"),
                new MenuItemInfo("User edit", "entity/User/3"),
                new MenuItemInfo("Login", "login")

        };
        List<Button> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

//    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
//        RouterLink link = new RouterLink();
//        link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
////        link.setRoute(menuItemInfo.getView());
//
//
//
//        Span icon = new Span();
//        icon.addClassNames("me-s", "text-l");
//        if (!menuItemInfo.getIconClass().isEmpty()) {
//            icon.addClassNames(menuItemInfo.getIconClass());
//        }
//
//        Span text = new Span(menuItemInfo.getText());
//        text.addClassNames("font-medium", "text-s");
//
//        link.add(icon, text);
//
//        return link;
//    }

    private static Button createLink(MenuItemInfo menuItemInfo) {
        Button link = new Button(menuItemInfo.getText());
        link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
//        link.setRoute(menuItemInfo.getView());

        link.addClassNames("font-medium", "text-s");

        link.addClickListener((ComponentEventListener<ClickEvent<Button>>) event -> UI.getCurrent().navigate(menuItemInfo.getNavigationTarget()));


        return link;
    }

    private Footer createFooter() {
        Footer footer = new Footer();
        footer.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

        Button logoutButton = new Button("Logout");
        logoutButton.addClickListener(event -> {
            VaadinSession.getCurrent().setAttribute(VaadinSessionAttribute.USER, null);
            UI.getCurrent().getPage().reload();
        });
        footer.add(logoutButton);

        return footer;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        if (title != null){
            return title.value();
        } else if(getContent() instanceof HasDynamicTitle){
            return ((HasDynamicTitle) getContent()).getPageTitle();
        } else {
            return null;
        }
    }
}
