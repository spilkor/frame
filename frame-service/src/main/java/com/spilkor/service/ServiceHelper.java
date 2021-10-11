package com.spilkor.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ServiceHelper.applicationContext = applicationContext;
    }

    public static <T extends FrameService> T getService(Class<T> serviceClazz) {
        return applicationContext.getBean(serviceClazz);
    }
}
