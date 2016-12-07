package com.nix.api.soap;

import com.nix.model.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "userService")
public interface UserWebService {

    @WebMethod(operationName = "allUsers")
    List<User> getAllUsers();

//    @WebMethod
    User getUserByLogin(@WebParam(name = "login") String login);

//    @WebMethod
    void createUser(@WebParam(name = "user") User user);

//    @WebMethod
    void updateUser(@WebParam(name = "user") User user);

//    @WebMethod
    void deleteUser(@WebParam(name = "login") String login);

}
