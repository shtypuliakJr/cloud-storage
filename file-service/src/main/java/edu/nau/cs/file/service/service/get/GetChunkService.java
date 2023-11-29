package edu.nau.cs.file.service.service.get;

import edu.nau.cs.file.service.dto.ChunkGetResponseDTO;

import java.util.List;

public interface GetChunkService {

    ChunkGetResponseDTO getChunk(String userId, String fileId, String fileChunkId);

    ChunkGetResponseDTO getArchivedFileChunks(String usedId, String fileId, List<String> chunkIds);
}
