package com.trololesha.mathanvariantsgenerator.exceptions;

public class VGException extends Exception {
    public enum errorCodeType {
        NON_CORRECT_GROUP, NON_CORRECT_STUDENT, NON_CORRECT_BOUNDS,
        SHORT_DEFINING_ARRAY,
        OTHER
    }
    private errorCodeType errorCode = errorCodeType.OTHER;

    public VGException() {
        super();
    }

    public VGException(String message) {
        super(message);
    }

    public VGException(errorCodeType errorcode) {
        super();
        this.errorCode = errorcode;
    }

    public VGException(String message, errorCodeType errorcode) {
        super(message);
        this.errorCode = errorcode;
    }

    public errorCodeType getErrorCode() {
        return errorCode;
    }
}
