package edu.nau.cs.meta.service.mapper;

import edu.nau.cs.meta.service.dto.FileObjectDTO;
import edu.nau.cs.meta.service.dto.FolderObjectDTO;
import edu.nau.cs.meta.service.entity.FolderObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FolderObjectMapper {

    private final FileObjectMapper fileObjectMapper;

    public FolderObjectDTO mapFolderObjectToDTO(FolderObject folderObject, Integer folderDepth, boolean isGetFileWithChunks) {
        List<FolderObjectDTO> folderObjectDTOs = null;
        List<FileObjectDTO> fileObjectDTOs = null;
        if (folderDepth > 0) {
            folderObjectDTOs = folderObject.getCurrentFolderChildFolders().stream()
                    .map(folder -> mapFolderObjectToDTO(folder, folderDepth - 1, isGetFileWithChunks))
                    .toList();
        }
        if (isGetFileWithChunks) {
            fileObjectDTOs = folderObject.getCurrentFolderChildFiles().stream()
                    .map(fileObjectMapper::mapFileObjectToDTO)
                    .toList();
        }
        return FolderObjectDTO.builder()
                .withFolderObjectId(folderObject.getId())
                .withFolderName(folderObject.getFolderName())
                .withFolderPath(folderObject.getFolderPath())
                .withCreatedAt(folderObject.getCreatedAt())
                .withUpdatedAt(folderObject.getUpdatedAt())
                .withUserId(folderObject.getUser().getId())
                .withTag(folderObject.getTag().getTagName())
                .withParentFolderId(Optional.ofNullable(folderObject.getParentFolder()).map(FolderObject::getId).orElse(null))
                .withCurrentFolderChildFolders(folderObjectDTOs)
                .withCurrentFolderChildFiles(fileObjectDTOs)
                .build();

    }

    public FolderObject mapFolderObjectToEntity(FolderObjectDTO folderObjectDTO) {
        return FolderObject.builder()
                .withId(folderObjectDTO.getFolderObjectId())
                .withFolderName(folderObjectDTO.getFolderName())
                .withFolderPath(folderObjectDTO.getFolderPath())
                .withCreatedAt(folderObjectDTO.getCreatedAt())
                .withUpdatedAt(folderObjectDTO.getUpdatedAt())
                .build();
    }

}
