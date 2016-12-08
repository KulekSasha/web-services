package com.nix.api.soap;

import com.nix.api.soap.exception.UserValidationException;
import com.nix.model.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "userWebService")
//    @XmlElement(name = "birthday", required = true)
//@XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
public interface UserWebService {

    @WebMethod(operationName = "allUsers")
    List<User> getAllUsers();

    //    @WebMethod
    User getUserByLogin(@WebParam(name = "login") String login);

    //    @WebMethod
    void createUser(@WebParam(name = "user") User user) throws UserValidationException;

    //    @WebMethod
    void updateUser(@WebParam(name = "user") User user);

    //    @WebMethod
    void deleteUser(@WebParam(name = "login") String login);

}
