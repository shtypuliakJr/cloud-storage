package edu.nau.cs.meta.service.exception;

public class CsUserAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "User already exists by username = %s";

    public CsUserAlreadyExistsException(String username) {
        super(String.format(MESSAGE, username));
    }

}
