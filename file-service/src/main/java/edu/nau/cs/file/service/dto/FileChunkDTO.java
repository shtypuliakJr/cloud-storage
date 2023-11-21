package edu.nau.cs.file.service.dto;

import lombok.Data;

import java.io.InputStream;

@Data
public class FileChunkDTO {

    private String originalFileName;

    private String chunkId;

    private Long size;

    private InputStream body;

}
