package com.borroom.backend;

import com.borroom.backend.utils.CrossFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BorRoom {

    public static void main(String[] args) {
        SpringApplication.run(BorRoom.class, args);
    }

    @Bean
    public FilterRegistrationBean FilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CrossFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("CrossFilter");
        registration.setOrder(1);
        return registration;
    }
}
