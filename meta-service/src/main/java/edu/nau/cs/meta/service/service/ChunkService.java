package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.ChunkObjectDTO;

import java.util.List;

public interface ChunkService {

    ChunkObjectDTO getChunkData(String userId, String chunkId);

    ChunkObjectDTO saveChunkData(String userId, ChunkObjectDTO chunkObjectDTO);

    ChunkObjectDTO editChunkData(String userId, String chunkId, ChunkObjectDTO chunkObjectDTO);

    void deleteChunkData(String userId, String chunkId);

    void deleteChunksData(String userId, List<String> chunkIds);

}
