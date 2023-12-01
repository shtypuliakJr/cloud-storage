package edu.nau.cs.file.service.dto.payload;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder(setterPrefix = "with")
public class FileUploadPayload {

    private String originalFilename;

    private String contentType;

    private InputStream body;

    private long size;

}
