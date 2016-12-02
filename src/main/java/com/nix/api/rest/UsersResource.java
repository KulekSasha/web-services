package com.nix.api.rest;

import com.nix.model.User;
import com.nix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UsersResource {


    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GET
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GET
    @Path("{login}")
    public Response getUserByLogin(@PathParam("login") String login) {

        User user = userService.findByLogin(login);

        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("").build();
        }
    }
}
