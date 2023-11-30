package edu.nau.cs.meta.service.exception;

public class CsChunkDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "File or folder does not exists for user = %s and objectId = %s";

    public CsChunkDoesNotExistsException(String userId, String fileObjectId) {
        this(String.format(MESSAGE, userId, fileObjectId));
    }

    public CsChunkDoesNotExistsException(String message) {
        super(message);
    }

}
