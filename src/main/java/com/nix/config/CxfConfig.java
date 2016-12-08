package com.nix.config;

import com.nix.api.soap.other.DateAdapter;
import com.nix.api.soap.other.MyIntercept;
import com.nix.api.soap.UserWebService;
import com.nix.api.soap.UserWebServiceImpl;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validator;
import javax.xml.ws.Endpoint;

@Configuration
//@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class CxfConfig {


//    @Autowired
//    private SpringBus cxfBus;

    @Autowired
    Validator validator;

    @Bean(destroyMethod = "shutdown", name = "cxf")
    public SpringBus cxf() {
        SpringBus springBus = new SpringBus();

        springBus.getInInterceptors().add(loggingInInterceptor());
        springBus.getInInterceptors().add(myIntercept());

        springBus.getInInterceptors().add(loggingInInterceptor());

        return springBus;
    }

    @Bean
    public UserWebService userWebService() {
        return new UserWebServiceImpl();
    }

    @Bean
    public Endpoint usersEndpoint() {
        Endpoint endpoint = new EndpointImpl(cxf(), userWebService());
        endpoint.publish("/users");
        return endpoint;
    }

    @Bean
    public MyIntercept myIntercept() {
        return new MyIntercept();
    }

    @Bean()
    public LoggingInInterceptor loggingInInterceptor() {
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        loggingInInterceptor.setPrettyLogging(true);
        return loggingInInterceptor;
    }

    @Bean
    public JAXBDataBinding jaxbDataBinding(){
        JAXBDataBinding jaxbDataBinding = new JAXBDataBinding();

        jaxbDataBinding.getConfiguredXmlAdapters().add(new DateAdapter());
        return jaxbDataBinding;
    }


}
