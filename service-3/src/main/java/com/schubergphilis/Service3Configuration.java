package com.schubergphilis;

import com.schubergphilis.filter.SleuthFilter;
import com.schubergphilis.rest.interceptor.SleuthInInterceptor;
import com.schubergphilis.rest.interceptor.SleuthOutInterceptor;
import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.Collection;

@SpringBootApplication
public class Service3Configuration {

    public static void main(String[] args) {
        SpringApplication.run(Service3Configuration.class, args);
    }

    @Bean
    Sampler sampler() {
        return info -> true;
    }

    @Bean
    public WebClient webClient(@Value("${service4.url:http://localhost:8085}") String service4Url) {
        return WebClient.create(service4Url + "/sayHello");
    }

    @Configuration
    protected static class SleuthWebClientConfiguration {

        @Autowired
        public Collection<WebClient> webClients;

        @Bean
        public SleuthOutInterceptor sleuthOutInterceptor() {
            return new SleuthOutInterceptor();
        }

        @Bean
        public SleuthInInterceptor sleuthInInterceptor() {
            return new SleuthInInterceptor();
        }

        @PostConstruct
        public void init() {
            if (this.webClients != null) {
                for (WebClient webClient : this.webClients) {
                    ClientConfiguration clientConfiguration = WebClient.getConfig(webClient);
                    clientConfiguration.getOutInterceptors().add(sleuthOutInterceptor());
                    clientConfiguration.getInInterceptors().add(sleuthInInterceptor());
                }
            }
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
}
