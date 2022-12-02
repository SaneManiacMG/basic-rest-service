package com.tutorial.restfulservice.inteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ProductServiceInteceptorAppConfig implements WebMvcConfigurer {

    @Autowired
    ProductServiceInteceptor productServiceInteceptor;

    public void addInteceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInteceptor);
    }
}
