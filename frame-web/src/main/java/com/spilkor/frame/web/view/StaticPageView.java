package com.spilkor.frame.web.view;

import com.spilkor.frame.web.MainLayout;
import com.spilkor.frame.web.page.FramePage;
import com.spilkor.frame.web.page.StaticFramePage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Route(value = "page/:pageId", layout = MainLayout.class)
public class StaticPageView extends VerticalLayout implements BeforeEnterObserver, HasDynamicTitle {

    private String pageId;
    private String pageTitle;

    public StaticPageView() {
        add(new Label(pageId));
    }

    @Override
    public String getPageTitle() {
        return pageTitle;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        pageId = event.getRouteParameters().get("pageId").orElse(null);

        FramePage framePage = null;
        try {
            framePage = createPageInstance(pageId);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            UI.getCurrent().navigate(RouteNotFoundError.class);
        }

        if (framePage != null){
            add(framePage);
        }
    }

    private FramePage createPageInstance(String pageId) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(StaticFramePage.class));
        for (BeanDefinition bd : scanner.findCandidateComponents("com.spilkor.frame.web")){
            Class clazz = BeanGenerator.class.getClassLoader().loadClass(bd.getBeanClassName());
            if (clazz.isAnnotationPresent(StaticFramePage.class)){
                StaticFramePage staticFramePage = (StaticFramePage) clazz.getAnnotation(StaticFramePage.class);
                if (staticFramePage.pageId().equals(pageId)){
                    Constructor<Object> constructor = clazz.getConstructor();
                    Object instance = constructor.newInstance();
                    if (instance instanceof FramePage){
                        pageTitle = staticFramePage.pageTitle();
                        return (FramePage) instance;
                    }
                }
            }
        }
        throw new ClassNotFoundException();
    }

}
