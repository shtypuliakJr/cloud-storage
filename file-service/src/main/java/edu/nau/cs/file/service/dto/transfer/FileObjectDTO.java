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
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
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
