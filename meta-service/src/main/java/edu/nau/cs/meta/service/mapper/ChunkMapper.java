package edu.nau.cs.meta.service.mapper;

import edu.nau.cs.meta.service.dto.ChunkObjectDTO;
import edu.nau.cs.meta.service.entity.Chunk;
import org.springframework.stereotype.Component;

@Component
public class ChunkMapper {

    public ChunkObjectDTO mapChunkToDTO(Chunk chunk) {
        return ChunkObjectDTO.builder()
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

    public Chunk mapChunkToEntity(ChunkObjectDTO chunkObjectDTO) {
        return Chunk.builder()
                .id(chunkObjectDTO.getChunkId())
                .chunkOrder(chunkObjectDTO.getChunkOrder())
                .chunkSize(chunkObjectDTO.getChunkSize())
                .chunkChecksum(chunkObjectDTO.getChunkChecksum())
                .s3Key(chunkObjectDTO.getS3Key())
                .createdAt(chunkObjectDTO.getCreatedAt())
                .updatedAt(chunkObjectDTO.getUpdatedAt())
                .build();
    }

}
