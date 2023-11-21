package edu.nau.cs.file.service.dto;

import lombok.Data;

import java.io.InputStream;

@Data
public class FileObjectDTO {

    private String originalFileName;

    private Long size;

    private InputStream body;

}
