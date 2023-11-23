package edu.nau.cs.meta.service.exception;

public class CsFileObjectDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "File or folder does not exists for user = %s and objectId = %s";

    public CsFileObjectDoesNotExistsException(String userId, String fileObjectId) {
        this(String.format(MESSAGE, userId, fileObjectId));
    }

    public CsFileObjectDoesNotExistsException(String message) {
        super(message);
    }
}
