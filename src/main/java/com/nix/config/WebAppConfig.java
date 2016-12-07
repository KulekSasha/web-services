package com.nix.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static java.util.Arrays.asList;

@Configuration
@EnableWebMvc
@ComponentScan({"com.nix.controller"})
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final int SECONDS_IN_YEAR = 60 * 60 * 24 * 365;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
                .setCachePeriod(SECONDS_IN_YEAR);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }


////    Apache CXF
//    @Bean(name= Bus.DEFAULT_BUS_ID)
//    public SpringBus springBus() {
//        SpringBus springBus = new SpringBus();
//        springBus.setInInterceptors(asList(loggingInInterceptor()));
//        springBus.setOutInterceptors(asList(loggingOutInterceptor()));
//        return springBus;
//    }
//
//    @Bean
//    public LoggingInInterceptor loggingInInterceptor(){
//        return new LoggingInInterceptor();
//    }
//
//    @Bean
//    public LoggingOutInterceptor loggingOutInterceptor(){
//        return new LoggingOutInterceptor();
//    }

}
