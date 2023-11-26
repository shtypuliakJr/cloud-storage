package edu.nau.cs.file.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder
public class S3FileChunkPayload {

    private String chunkId;

    private String s3Key;

    private long chunkOrder;

    private long size;

    private InputStream body;

    private Long checkSum;

}
