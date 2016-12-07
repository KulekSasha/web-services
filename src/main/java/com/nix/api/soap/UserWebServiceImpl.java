package com.nix.api.soap;

import com.nix.model.User;
import com.nix.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

@Component
@WebService(endpointInterface = "com.nix.api.soap.UserWebService",
        portName = "UserWsImpl")
public class UserWebServiceImpl implements UserWebService {

    public static final Logger log = LoggerFactory.getLogger(UserWebServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public List<User> getAllUsers() {
        log.debug("invoke getAllUsers");
        return userService.findAll();
    }

    @Override
    public User getUserByLogin(String login) {
        log.debug("invoke getUserByLogin with login: {}", login);
        return userService.findByLogin(login);
    }

    @Override
    public void createUser(User newUser) {
        userService.create(newUser);
    }

    @Override
    public void updateUser(User userUpd) {
        userService.create(userUpd);
    }

    @Override
    public void deleteUser(String login) {
        userService.remove(userService.findByLogin(login));
    }
}
