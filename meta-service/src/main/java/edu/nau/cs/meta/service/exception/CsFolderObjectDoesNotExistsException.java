package edu.nau.cs.meta.service.exception;

public class CsFolderObjectDoesNotExistsException extends RuntimeException {

    private static final String MESSAGE = "Folder does not exists for user = %s and folderId = %s";

    public CsFolderObjectDoesNotExistsException(String userId, String folderId) {
        this(String.format(MESSAGE, userId, folderId));
    }

    public CsFolderObjectDoesNotExistsException(String message) {
        super(message);
    }

}
