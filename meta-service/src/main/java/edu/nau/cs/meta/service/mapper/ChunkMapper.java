package edu.nau.cs.meta.service.mapper;

import edu.nau.cs.meta.service.dto.FileChunkDTO;
import edu.nau.cs.meta.service.entity.Chunk;
import org.springframework.stereotype.Component;

@Component
public class ChunkMapper {

    public FileChunkDTO mapChunkToDTO(Chunk chunk) {
        return FileChunkDTO.builder()
                .chunkId(chunk.getId())
                .fileId(chunk.getFileObject().getId())
                .chunkOrder(chunk.getChunkOrder())
                .s3Key(chunk.getS3Key())
                .chunkSize(chunk.getChunkSize())
                .createdAt(chunk.getCreatedAt())
                .updatedAt(chunk.getUpdatedAt())
                .build();
    }

    public Chunk mapChunkToEntity(FileChunkDTO fileChunkDTO) {
        return Chunk.builder()
                .id(fileChunkDTO.getChunkId())
                .chunkOrder(fileChunkDTO.getChunkOrder())
                .s3Key(fileChunkDTO.getS3Key())
                .chunkSize(fileChunkDTO.getChunkSize())
                .createdAt(fileChunkDTO.getCreatedAt())
                .updatedAt(fileChunkDTO.getUpdatedAt())
                .build();
    }

}
