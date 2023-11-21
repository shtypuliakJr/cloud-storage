package edu.nau.cs.file.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder(setterPrefix = "with")
public class FileChunkUploadDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -9177303946655964383L;

    private String chunkId;

    private String chunkObjectKey;

    private Long chunkPosition;

    private Long checksumCRC32;

}
