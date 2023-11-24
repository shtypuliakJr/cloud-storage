package edu.nau.cs.meta.service.mapper;

import edu.nau.cs.meta.service.dto.FileChunkDTO;
import edu.nau.cs.meta.service.entity.Chunk;
import org.springframework.stereotype.Component;

@Component
public class ChunkMapper {

    public FileChunkDTO mapChunkToDTO(Chunk chunk) {
        return FileChunkDTO.builder()
                .withChunkId(chunk.getId())
                .withChunkOrder(chunk.getChunkOrder())
                .withChunkSize(chunk.getChunkSize())
                .withChunkChecksum(chunk.getChunkChecksum())
                .withS3Key(chunk.getS3Key())
                .withCreatedAt(chunk.getCreatedAt())
                .withUpdatedAt(chunk.getUpdatedAt())
                .withFileObjectId(chunk.getFileObject().getId())
                .build();
    }

    public Chunk mapChunkToEntity(FileChunkDTO fileChunkDTO) {
        return Chunk.builder()
                .id(fileChunkDTO.getChunkId())
                .chunkOrder(fileChunkDTO.getChunkOrder())
                .chunkSize(fileChunkDTO.getChunkSize())
                .chunkChecksum(fileChunkDTO.getChunkChecksum())
                .s3Key(fileChunkDTO.getS3Key())
                .createdAt(fileChunkDTO.getCreatedAt())
                .updatedAt(fileChunkDTO.getUpdatedAt())
                .build();
    }

}
