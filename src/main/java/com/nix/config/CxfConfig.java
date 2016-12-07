package com.nix.config;

import com.nix.api.soap.UserWebService;
import com.nix.api.soap.UserWebServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.xml.ws.Endpoint;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class CxfConfig {


    @Autowired
    private Bus cxfBus;

    @Bean
    public UserWebService userWebService(){
        return new UserWebServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(cxfBus, userWebService());
        endpoint.publish("/users");
        return endpoint;
    }

}
