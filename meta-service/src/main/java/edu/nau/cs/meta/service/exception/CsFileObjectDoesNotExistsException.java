package edu.nau.cs.meta.service.exception;

public class CsFileObjectDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "Chunk does not exists for user = %s and chunkId = %s";

    public CsFileObjectDoesNotExistsException(String userId, String chunkId) {
        this(String.format(MESSAGE, userId, chunkId));
    }

    public CsFileObjectDoesNotExistsException(String message) {
        super(message);
    }

}
