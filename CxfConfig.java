package com.example.demo.config;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {

    @Bean
    public JAXRSServerFactoryBean cxfServer() {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.setResourceClasses(FileUploadController.class);
        factoryBean.setAddress("/api");
        return factoryBean;
    }
}
