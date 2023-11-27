package edu.nau.cs.meta.service.exception;

public class RestException extends RuntimeException {

    public RestException() {
        super();
    }

    public RestException(String message) {
        super(message);
    }

}
