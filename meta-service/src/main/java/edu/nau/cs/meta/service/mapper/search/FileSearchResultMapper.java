package edu.nau.cs.meta.service.mapper.search;

import edu.nau.cs.meta.service.dto.search.ChunkSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FileSearchResultDTO;
import edu.nau.cs.meta.service.entity.FileObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FileSearchResultMapper {

    private final ChunkSearchResultMapper chunkSearchResultMapper;

    public FileSearchResultDTO mapToFileSearchResultDTO(FileObject fileObject) {
        return this.mapToFileSearchResultDTO(fileObject, false);
    }

    public FileSearchResultDTO mapToFileSearchResultDTO(FileObject fileObject, boolean isChunksIncluded) {
        List<ChunkSearchResultDTO> chunks = fileObject.getChunks().stream()
                .map(chunkSearchResultMapper::mapToChunkSearchResultDTO)
                .toList();

        return FileSearchResultDTO.builder()
                .id(fileObject.getId())
                .fileName(fileObject.getFileName())
                .filePath(fileObject.getFilePath())
                .fileSize(chunks.stream().map(ChunkSearchResultDTO::getChunkChecksum).reduce(0L, Long::sum))
                .createdAt(fileObject.getCreatedAt())
                .updatedAt(fileObject.getUpdatedAt())
                .chunksCount((long) chunks.size())
                .chunks(isChunksIncluded ? chunks : null)
                .parentFolderId(fileObject.getParentFolderObject().getId())
                .build();
    }

}
