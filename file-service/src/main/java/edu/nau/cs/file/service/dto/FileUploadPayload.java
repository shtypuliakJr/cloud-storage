package edu.nau.cs.file.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder(setterPrefix = "with")
public class FileUploadPayload {

    public String s3Key;

    public InputStream body;

    public long size;

}
