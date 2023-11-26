package edu.nau.cs.file.service.dto.transfer;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "with")
public class FileChunkDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7607023141380020931L;

    private String chunkId;

    private Long chunkOrder;

    private Long chunkSize;

    private Long chunkChecksum;

    private String s3Key;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String fileObjectId;

}
