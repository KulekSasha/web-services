package com.nix.config;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.annotation.Priority;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Priority(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebAppConfig.class, AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("contextConfigLocation", "NOTNULL");

        super.onStartup(servletContext);

        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("JerseyServlet",
                ServletContainer.class.getName());
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/api/rest/*");
        servletRegistration.setInitParameter("javax.ws.rs.Application",
                JerseyAppConfig.class.getName());
    }


}
