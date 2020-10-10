package com.ingenico.ebanking.exception;

public class ExceptionHandler {

    public static ResponseError errorResponse(Exception e) {
        ResponseError ex = null;
        if (e instanceof ResponseException) {
            ex = new ResponseError();
            ex.setErrorCode(((ResponseException) e).getExceptionCode());
            ex.setErrorDescription(((ResponseException) e).getExceptionDescription());
            ex.setDeveloperMessage(((ResponseException) e).getDeveloperMessage());
        } else {
            ex = new ResponseError();
            ex.setErrorCode("ERR-GNR-01");
            ex.setErrorDescription("Generic Error");
        }
        return ex;
    }

    private ExceptionHandler() {
        throw new IllegalStateException("Utility class");
    }

}