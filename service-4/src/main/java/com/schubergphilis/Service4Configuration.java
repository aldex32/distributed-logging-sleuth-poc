package com.schubergphilis;

import com.schubergphilis.filter.SleuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class Service4Configuration {

    public static void main(String[] args) {
        SpringApplication.run(Service4Configuration.class, args);
    }

    @Bean
    Sampler sampler() {
        return info -> true;
    }

    @Bean
    public FilterRegistrationBean sleuthFilterRegistration() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getSleuthFilter());

        return filterRegistrationBean;
    }

    @Bean
    public Filter getSleuthFilter() {
        return new SleuthFilter();
    }
}
