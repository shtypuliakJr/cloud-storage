package edu.nau.cs.meta.service.exception;

public class CsChunkDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "Chunk does not exists for user = %s and chunkId = %s";

    public CsChunkDoesNotExistsException(String userId, String chunkId) {
        this(String.format(MESSAGE, userId, chunkId));
    }

    public CsChunkDoesNotExistsException(String message) {
        super(message);
    }

}
