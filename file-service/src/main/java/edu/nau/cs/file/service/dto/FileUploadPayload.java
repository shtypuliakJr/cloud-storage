package edu.nau.cs.file.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder(setterPrefix = "with")
public class FileUploadPayload {

    public String originalFilename;

    public String contentType;

    public InputStream body;

    public long size;

}
