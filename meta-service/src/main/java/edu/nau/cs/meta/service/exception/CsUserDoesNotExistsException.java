package edu.nau.cs.meta.service.exception;

public class CsUserDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "User already exists for by id = %s";

    public CsUserDoesNotExistsException(String userId) {
        super(String.format(MESSAGE, userId));
    }

}
