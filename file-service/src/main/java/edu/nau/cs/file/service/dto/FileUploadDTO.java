package edu.nau.cs.file.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder(setterPrefix = "with")
public class FileUploadDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4919367166367521763L;

    @JsonProperty(value = "originalFilename")
    private String originalFilename;

    @JsonProperty(value = "fileChunkUploads")
    private List<FileChunkUploadDTO> fileChunkUploads;

}
