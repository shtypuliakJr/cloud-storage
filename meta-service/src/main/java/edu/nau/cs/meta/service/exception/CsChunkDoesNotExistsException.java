package edu.nau.cs.meta.service.exception;

public class CsChunkDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "Chunk does not exists by chunkId = %s";

    public CsChunkDoesNotExistsException(String fileObjectId) {
        super(String.format(MESSAGE, fileObjectId));
    }

}
