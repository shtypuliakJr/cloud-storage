package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.FileChunkDTO;

import java.util.List;

public interface ChunkService {

    FileChunkDTO getChunkData(String userId, String chunkId);

    FileChunkDTO saveChunkData(String userId, FileChunkDTO chunkObjectDTO);

    FileChunkDTO editChunkData(String userId, String chunkId, FileChunkDTO fileChunkDTO);

    void deleteChunkData(String userId, String chunkId);

    void deleteChunksData(String userId, List<String> chunkIds);

}
