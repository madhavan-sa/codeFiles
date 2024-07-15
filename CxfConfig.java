package com.example.demo.config;

import com.example.demo.controller.FileUploadController;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

    @Bean
    public JAXRSServerFactoryBean cxfServer() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(FileUploadController.class);
        factoryBean.setAddress("/api");
        factoryBean.getFeatures().add(new Swagger2Feature());
        return factoryBean;
    }
}
