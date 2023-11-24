package edu.nau.cs.meta.service.mapper;

import edu.nau.cs.meta.service.dto.FileChunkDTO;
import edu.nau.cs.meta.service.dto.FileObjectDTO;
import edu.nau.cs.meta.service.entity.FileObject;
import edu.nau.cs.meta.service.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FileObjectMapper {

    private final ChunkMapper chunkMapper;

    public FileObjectDTO mapFileObjectToDTO(FileObject fileObject) {
        List<FileChunkDTO> fileChunkDTOs = fileObject.getChunks().stream()
                .map(chunkMapper::mapChunkToDTO)
                .toList();
        return FileObjectDTO.builder()
                .withFileObjectId(fileObject.getId())
                .withFileName(fileObject.getFileName())
                .withFileType(fileObject.getObjectType())
                .withS3Path(fileObject.getS3Path())
                .withCreatedAt(fileObject.getCreatedAt())
                .withUpdatedAt(fileObject.getUpdatedAt())
                .withChunks(fileChunkDTOs)
                .withSize(fileChunkDTOs.stream().map(FileChunkDTO::getChunkSize).reduce(Long::sum).orElse(null))
                .withUserId(fileObject.getUser().getId())
                .withTag(Optional.ofNullable(fileObject.getTag()).map(Tag::getTagName).orElse(null))
                .build();

    }

    public FileObject mapFileObjectToEntity(FileObjectDTO fileObjectDTO) {
        return FileObject.builder()
                .withId(fileObjectDTO.getFileObjectId())
                .withFileName(fileObjectDTO.getFileName())
                .withObjectType(fileObjectDTO.getFileType())
                .withFilePath(fileObjectDTO.getFilePath())
                .withS3Path(fileObjectDTO.getS3Path())
                .withCreatedAt(fileObjectDTO.getCreatedAt())
                .withUpdatedAt(fileObjectDTO.getUpdatedAt())
                .build();
    }

}
