package com.nix.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nix.config.JerseyAppConfig;
import com.nix.model.Role;
import com.nix.model.User;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class UsersResourceTest extends JerseyTest {

    private static ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyWebTestContainerFactory();
    }

    @Override
    protected DeploymentContext configureDeployment() {
        return ServletDeploymentContext
                .forServlet(new ServletContainer(new JerseyAppConfig()))
                .contextParam("contextClass",
                        "org.springframework.web.context.support.AnnotationConfigWebApplicationContext")
                .contextParam("contextConfigLocation", "com.nix.config.AppConfig")
                .addListener(ContextLoaderListener.class)
                .build();
    }

    @Test
    public void getAllUsers() throws Exception {
        int expectedListSize = 5;

        Response output = target("users")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        List<User> list = jsonMapper.readValue(output.readEntity(String.class),
                TypeFactory.defaultInstance().constructCollectionType(List.class,
                        User.class));

        assertEquals("should return status 200", Response.Status.OK.getStatusCode(),
                output.getStatus());
        assertEquals("should contain 5 user", expectedListSize, list.size());
    }

    @Test
    public void getUserByLogin() throws Exception {
        Response output = target("users/testUser_1")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        jsonMapper.setTimeZone(TimeZone.getDefault());
        User user = jsonMapper.readValue(output.readEntity(String.class), User.class);

        assertEquals("should return status 200", Response.Status.OK.getStatusCode(),
                output.getStatus());
        assertEquals("users should be equal", getExpectedUser(), user);
    }


    private User getExpectedUser() {
        return new User(1L, "testUser_1", "testUser_1",
                "testUser_1@gmail.com", "Ivan", "Ivanov",
                new GregorianCalendar(1986, Calendar.JANUARY, 1).getTime(),
                new Role(1L, "Admin"));
    }
}