package com.rabobank.upload.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Common configuration class
 * @author kumaran
 *
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/rabobank").setViewName("forward:/index.html");
    }

}