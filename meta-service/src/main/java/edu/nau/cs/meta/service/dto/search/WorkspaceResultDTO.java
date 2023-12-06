package edu.nau.cs.meta.service.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceResultDTO {

    private List<FolderSearchResultDTO> childFolders;

    private List<FileSearchResultDTO> childFiles;

    private String userId;

}
