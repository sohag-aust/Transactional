package org.spring;

import org.spring.config.ProductConfig;
import org.spring.service.ProductService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
        context.registerShutdownHook(); // this is used for triggering context.close() method

        ProductService productService = context.getBean(ProductService.class);
        productService.saveProductInfo();

        context.close();
    }
}