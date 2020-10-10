package com.ingenico.ebanking.exception;

/**
 *
 * @author caydogdu
 *
 *         This is a custom exception class for business rules
 */
public class ResponseException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final String exceptionCode;

    private final String exceptionDescription;

    private final String developerMessage;

    public ResponseException(String exceptionCode, String exceptionDescription, String developerMessage) {
        super();
        this.exceptionCode = exceptionCode;
        this.exceptionDescription = exceptionDescription;
        this.developerMessage = developerMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

}
