package edu.nau.cs.meta.service.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(setterPrefix = "with")
public class FileObjectDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3606020079001136077L;

    private String id;

    private String originalName;

    private String objectType;

    private Boolean isFolder;

    private String s3Path;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<FileChunkDTO> chunks;

    private Long size;

    private String userId;

    private String tag;

}
