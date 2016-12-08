package com.nix.api.soap.exception;

public class ServiceExceptionDetails {

    private String invalidField;
    private String validationMessage;

    public ServiceExceptionDetails(String invalidField, String validationMessage) {
        this.invalidField = invalidField;
        this.validationMessage = validationMessage;
    }

    public String getInvalidField() {
        return invalidField;
    }

    public void setInvalidField(String invalidField) {
        this.invalidField = invalidField;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}