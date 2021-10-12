package com.spilkor.frame.application;

import com.spilkor.frame.model.BaseEntity;
import com.spilkor.frame.web.FrameServiceInitializer;
import com.spilkor.service.ServiceHelper;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.artur.helpers.LaunchUtil;


@SpringBootApplication(scanBasePackageClasses = {ServiceHelper.class, FrameServiceInitializer.class})
@EntityScan(basePackageClasses = {BaseEntity.class})
@Configuration
@EnableVaadin(value = {"com.spilkor.frame.web"})
@EnableJpaRepositories("com.spilkor.frame.repository")
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class FrameApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(FrameApplication.class, args));
    }

}
