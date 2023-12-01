package edu.nau.cs.file.service.service.delete;

import edu.nau.cs.file.service.dto.delete.ChunkObjectDeleteDTO;

import java.util.List;

public interface DeleteFileChunkService {

    ChunkObjectDeleteDTO deleteFileChunk(String fileId, String chunkId, String userId);

    List<ChunkObjectDeleteDTO> deleteFileChunks(String fileId, List<String> chunkIds, String userId);

}
