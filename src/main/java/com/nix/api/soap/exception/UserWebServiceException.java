package com.nix.api.soap.exception;

import javax.xml.ws.WebFault;
import javax.xml.ws.WebServiceException;
import java.util.List;

@WebFault
public class UserWebServiceException extends WebServiceException {

    private List<ServiceExceptionDetails> faultDetails;

    public UserWebServiceException(List<ServiceExceptionDetails> faultDetails) {
        this.faultDetails = faultDetails;
    }

    public UserWebServiceException(String message, List<ServiceExceptionDetails> faultDetails) {
        super(message);
        this.faultDetails = faultDetails;
    }

    public List<ServiceExceptionDetails> getFaultDetails() {
        return faultDetails;
    }


}
