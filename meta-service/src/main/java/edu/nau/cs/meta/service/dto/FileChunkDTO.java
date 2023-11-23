package edu.nau.cs.meta.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FileChunkDTO {

    private String chunkId;

    private String fileId;

    private Long chunkOrder;

    private String s3Key;

    private Long chunkSize;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
