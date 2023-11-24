package edu.nau.cs.meta.service.exception;

public class CsUserDoesNotExistsByIdException extends RuntimeException {

    private static final String MESSAGE = "User does not exists by id = %s";

    public CsUserDoesNotExistsByIdException(String userId) {
        super(String.format(MESSAGE, userId));
    }

}
