package edu.nau.cs.meta.service.exception;

public class CsFileObjectDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "File does not exists for user = %s and fileId = %s";

    public CsFileObjectDoesNotExistsException(String userId, String fileId) {
        this(String.format(MESSAGE, userId, fileId));
    }

    public CsFileObjectDoesNotExistsException(String message) {
        super(message);
    }

}
