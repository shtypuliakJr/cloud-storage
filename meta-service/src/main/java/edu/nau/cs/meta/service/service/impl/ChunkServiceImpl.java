package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dto.FileChunkDTO;
import edu.nau.cs.meta.service.repository.ChunkRepository;
import edu.nau.cs.meta.service.service.ChunkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChunkServiceImpl implements ChunkService {

    private final ChunkRepository chunkRepository;

    @Override
    public FileChunkDTO getChunkData(String userId, String chunkId) {
        return null;
    }

    @Override
    public FileChunkDTO saveChunkData(String userId, FileChunkDTO chunkObjectDTO) {
        return null;
    }

    @Override
    public FileChunkDTO editChunkData(String userId, String chunkId, FileChunkDTO fileChunkDTO) {
        return null;
    }

    @Override
    public void deleteChunkData(String userId, String chunkId) {

    }

    @Override
    public void deleteChunksData(String userId, List<String> chunkIds) {

    }

}
