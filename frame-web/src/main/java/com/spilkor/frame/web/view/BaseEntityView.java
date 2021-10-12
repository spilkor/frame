package com.spilkor.frame.web.view;

import com.spilkor.frame.model.BaseEntity;
import com.spilkor.frame.model.FrameEntity;
import com.spilkor.frame.model.User;
import com.spilkor.frame.web.MainLayout;
import com.spilkor.frame.web.page.AbstractBaseEntityPage;
import com.spilkor.frame.web.page.EntityPage;
import com.spilkor.service.BusinessEntityService;
import com.spilkor.service.FrameEntityService;
import com.spilkor.service.ServiceHelper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Route(value = "entity/:entityDescriptorId/:id", layout = MainLayout.class)
public class BaseEntityView extends VerticalLayout implements BeforeEnterObserver, HasDynamicTitle {

    private String pageTitle;
    private final BusinessEntityService businessEntityService = ServiceHelper.getService(BusinessEntityService.class);
    private final FrameEntityService frameEntityService = ServiceHelper.getService(FrameEntityService.class);
    private AbstractBaseEntityPage abstractBaseEntityPage = null;

    @Override
    public String getPageTitle() {
        return pageTitle;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (abstractBaseEntityPage != null){
            remove(abstractBaseEntityPage);
        } //FIXME

        String entityDescriptorId = event.getRouteParameters().get("entityDescriptorId").orElse(null);
        String id = event.getRouteParameters().get("id").orElse(null);

        try {
            abstractBaseEntityPage = createPageInstance(entityDescriptorId, id);
            pageTitle = abstractBaseEntityPage.getPageTitle();
            abstractBaseEntityPage.init();
            abstractBaseEntityPage.refresh();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            UI.getCurrent().navigate(RouteNotFoundError.class);
        }

        if (abstractBaseEntityPage != null){
            add(abstractBaseEntityPage);
        }
    }

    private AbstractBaseEntityPage createPageInstance(String entityDescriptorId, String id) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(EntityPage.class));
        for (BeanDefinition bd : scanner.findCandidateComponents("com.spilkor.frame.web")){
            Class clazz = BeanGenerator.class.getClassLoader().loadClass(bd.getBeanClassName());
            if (clazz.isAnnotationPresent(EntityPage.class)){
                EntityPage entityPage = (EntityPage) clazz.getAnnotation(EntityPage.class);
                if (entityPage.entityDescriptorId().equals(entityDescriptorId)){
                    Class baseEntityClass = User.class; //FIXME from bedi
                    BaseEntity baseEntity =
                            FrameEntity.class.isAssignableFrom(baseEntityClass) ?
                                    frameEntityService.find(baseEntityClass, id) :
                                    businessEntityService.find(baseEntityClass, Long.valueOf(id));
                    Constructor<Object> constructor = clazz.getConstructor(entityPage.entityClass());
                    Object instance = constructor.newInstance(baseEntity);
                    if (instance instanceof AbstractBaseEntityPage){
                        AbstractBaseEntityPage abstractBaseEntityPage = (AbstractBaseEntityPage) instance;
                        return abstractBaseEntityPage;
                    }
                }
            }
        }
        throw new ClassNotFoundException();
    }

}
