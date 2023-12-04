package edu.nau.cs.meta.service.mapper.search;

import edu.nau.cs.meta.service.dto.search.FileSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FolderSearchResultDTO;
import edu.nau.cs.meta.service.entity.FolderObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FolderSearchResultMapper {

    private final FileSearchResultMapper fileSearchResultMapper;

    public FolderSearchResultDTO mapToFolderSearchResultDTO(FolderObject folderObject) {
        return this.mapToFolderSearchResultDTO(folderObject, 1L);
    }

    public FolderSearchResultDTO mapToFolderSearchResultDTO(FolderObject folderObject, long depth) {
        List<FileSearchResultDTO> childFiles = folderObject.getCurrentFolderChildFiles().stream()
                .map(fileSearchResultMapper::mapToFileSearchResultDTO)
                .toList();
        List<FolderSearchResultDTO> childFolders = depth == 0
                ? null
                : folderObject.getCurrentFolderChildFolders().stream()
                .map(childFolder -> this.mapToFolderSearchResultDTO(childFolder, depth - 1))
                .toList();

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

}
