package edu.nau.cs.meta.service.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FolderSearchResultDTO {

    private String id;

    private String folderName;

    private String objectPath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<FolderSearchResultDTO> childFolders;

    private List<FileSearchResultDTO> childFiles;

    private String parentFolderId;

}
