package edu.nau.cs.file.service.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private String parentFolderId;

    private List<FileChunkDTO> chunks;

    private String userId;

    private String tag;

}
