package com.spilkor.frame.web.view;

import com.spilkor.frame.web.MainLayout;
import com.spilkor.frame.web.page.FramePage;
import com.spilkor.frame.web.page.StaticPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Route(value = "page/:pageId", layout = MainLayout.class)
public class StaticPageView extends VerticalLayout implements BeforeEnterObserver, HasDynamicTitle {

    FramePage framePage = null;

    private String pageTitle;

    @Override
    public String getPageTitle() {
        return pageTitle;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (framePage != null){
            remove(framePage);
        }//FXME

        String pageId = event.getRouteParameters().get("pageId").orElse(null);

        try {
            framePage = createFramePageInstance(pageId);
            pageTitle = framePage.getPageTitle();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            UI.getCurrent().navigate(RouteNotFoundError.class);
        }

        if (framePage != null){
            add(framePage);
        }
    }

    private FramePage createFramePageInstance(String pageId) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(StaticPage.class));
        for (BeanDefinition bd : scanner.findCandidateComponents("com.spilkor.frame.web")){
            Class clazz = BeanGenerator.class.getClassLoader().loadClass(bd.getBeanClassName());
            if (clazz.isAnnotationPresent(StaticPage.class)){
                StaticPage staticPage = (StaticPage) clazz.getAnnotation(StaticPage.class);
                if (staticPage.pageId().equals(pageId)){
                    Constructor<Object> constructor = clazz.getConstructor();
                    Object instance = constructor.newInstance();
                    if (instance instanceof FramePage){
                        return (FramePage) instance;
                    }
                }
            }
        }
        throw new ClassNotFoundException();
    }

}
