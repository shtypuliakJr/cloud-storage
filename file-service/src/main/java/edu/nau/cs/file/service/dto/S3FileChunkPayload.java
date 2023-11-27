package edu.nau.cs.file.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;
import java.time.LocalDateTime;

@Data
@Builder
public class S3FileChunkPayload {

    private String chunkId;

    private String s3Key;

    private long chunkOrder;

    private Long chunkSize;

    private InputStream body;

    private Long checksum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String fileObjectId;

}
