package com.nix.api.soap.exception;

import javax.xml.ws.WebFault;
import javax.xml.ws.WebServiceException;
import java.util.List;

@WebFault
public class UserValidationException extends WebServiceException {

    private List<ValidationExceptionDetails> faultDetails;

    public UserValidationException(List<ValidationExceptionDetails> faultDetails) {
        this.faultDetails = faultDetails;
    }

    public UserValidationException(String message, List<ValidationExceptionDetails> faultDetails) {
        super(message);
        this.faultDetails = faultDetails;
    }

    public List<ValidationExceptionDetails> getFaultDetails() {
        return faultDetails;
    }


}
