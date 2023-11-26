package edu.nau.cs.file.service.dto.transfer;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(setterPrefix = "with")
public class FolderObjectDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7235437695874712161L;

    private String folderObjectId;

    private String folderName;

    private String folderPath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String userId;

    private String tag;

    private String parentFolderId;

    private List<FolderObjectDTO> currentFolderChildFolders;

    private List<FileObjectDTO> currentFolderChildFiles;

}
