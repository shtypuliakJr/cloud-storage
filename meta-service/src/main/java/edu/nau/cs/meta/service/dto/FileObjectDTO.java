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

    private String fileObjectId;

    private String fileName;

    private Long size;

    private String fileType;

    private String filePath;

    private String s3Path;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime parentFolderId;

    private List<FileChunkDTO> chunks;

    private String userId;

    private String tag;

}
