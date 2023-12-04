package edu.nau.cs.meta.service.mapper.search;

import edu.nau.cs.meta.service.dto.search.ChunkSearchResultDTO;
import edu.nau.cs.meta.service.entity.Chunk;
import org.springframework.stereotype.Component;

@Component
public class ChunkSearchResultMapper {

    public ChunkSearchResultDTO mapToChunkSearchResultDTO(Chunk chunk) {
        return new ChunkSearchResultDTO(
                chunk.getId(),
                chunk.getChunkOrder(),
                chunk.getChunkSize(),
                chunk.getChunkChecksum(),
                chunk.getCreatedAt(),
                chunk.getUpdatedAt(),
                chunk.getFileObject().getId()
        );
    }

}
