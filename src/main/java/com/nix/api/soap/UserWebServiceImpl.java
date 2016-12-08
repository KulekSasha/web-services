package com.nix.api.soap;

import com.nix.api.soap.exception.UserValidationException;
import com.nix.api.soap.exception.ValidationExceptionDetails;
import com.nix.model.User;
import com.nix.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@WebService(endpointInterface = "com.nix.api.soap.UserWebService")
public class UserWebServiceImpl implements UserWebService {

    public static final Logger log = LoggerFactory.getLogger(UserWebServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Validator validator;


    @Override
    public List<User> getAllUsers() {
        log.debug("invoke getAllUsers");
        return userService.findAll();
    }

    @Override
    public User getUserByLogin(String login) throws WebServiceException {
        log.debug("invoke getUserByLogin with login: {}", login);
        User user = userService.findByLogin(login);

        if (user != null) {
            return user;
        } else {
            throw new WebServiceException("user not found");
        }
    }

    @Override
    public void createUser(User newUser) throws UserValidationException {
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);

        if (violations.size() > 0) {
            List<ValidationExceptionDetails> listErrors = new ArrayList<>();

            violations.forEach(v -> listErrors.add(
                    new ValidationExceptionDetails(v.getPropertyPath().toString(), v.getMessage())));

            throw new UserValidationException(listErrors);
        }

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
