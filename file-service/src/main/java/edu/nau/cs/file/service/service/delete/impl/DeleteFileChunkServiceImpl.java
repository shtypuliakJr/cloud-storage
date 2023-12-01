package edu.nau.cs.file.service.service.delete.impl;

import edu.nau.cs.file.service.dto.delete.ChunkObjectDeleteDTO;
import edu.nau.cs.file.service.service.delete.DeleteFileChunkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteFileChunkServiceImpl implements DeleteFileChunkService {

    @Override
    public ChunkObjectDeleteDTO deleteFileChunk(String fileId, String chunkId, String userId) {
        return null;
    }

    @Override
    public List<ChunkObjectDeleteDTO> deleteFileChunks(String fileId, List<String> chunkIds, String userId) {
        return null;
    }

}
