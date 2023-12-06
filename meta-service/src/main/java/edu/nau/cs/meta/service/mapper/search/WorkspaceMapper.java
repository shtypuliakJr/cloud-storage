package edu.nau.cs.meta.service.mapper.search;

import edu.nau.cs.meta.service.dto.search.FileSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FolderSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.WorkspaceResultDTO;
import edu.nau.cs.meta.service.entity.FolderObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class WorkspaceMapper {

    private final FileSearchResultMapper fileSearchResultMapper;

    public WorkspaceResultDTO mapToWorkspace(FolderObject rootFolderObject) {

        List<FileSearchResultDTO> childFiles = rootFolderObject.getCurrentFolderChildFiles().stream()
                .map(fileObject -> fileSearchResultMapper.mapToFileSearchResultDTO(fileObject, true))
                .toList();
        List<FolderSearchResultDTO> childFolders = Optional.of(getChildFolders(rootFolderObject, Long.MAX_VALUE))
                .filter(folders -> !folders.isEmpty())
                .orElse(null);

        return WorkspaceResultDTO.builder()
                .childFolders(childFolders)
                .childFiles(childFiles)
                .userId(rootFolderObject.getUser().getId())
                .build();
    }

    private FolderSearchResultDTO mapToChildFolder(FolderObject folderObject, long depth) {

        List<FileSearchResultDTO> childFiles = folderObject.getCurrentFolderChildFiles().stream()
                .map(fileObject -> fileSearchResultMapper.mapToFileSearchResultDTO(fileObject, true))
                .toList();
        List<FolderSearchResultDTO> childFolders = Optional.of(getChildFolders(folderObject, depth))
                .filter(folders -> !folders.isEmpty())
                .orElse(null);

        String parentFolderId = Optional.ofNullable(folderObject.getParentFolder())
                .map(FolderObject::getId)
                .orElse(null);

        return new FolderSearchResultDTO(
                folderObject.getId(),
                folderObject.getFolderName(),
                folderObject.getFolderPath(),
                folderObject.getCreatedAt(),
                folderObject.getUpdatedAt(),
                childFolders,
                childFiles,
                parentFolderId
        );
    }

    private List<FolderSearchResultDTO> getChildFolders(FolderObject folderObject, long depth) {
        return depth == 0
                ? Collections.emptyList()
                : folderObject.getCurrentFolderChildFolders().stream()
                .map(childFolder -> this.mapToChildFolder(childFolder, depth - 1))
                .toList();
    }

}
