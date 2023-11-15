package edu.nau.cs.file.service.service.delete.impl;

import edu.nau.cs.file.service.dto.FileChunkMetaDTO;
import edu.nau.cs.file.service.service.delete.DeleteFileChunkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteFileChunkServiceImpl implements DeleteFileChunkService {

    @Override
    public FileChunkMetaDTO deleteFileChunk(String fileChunkId) {
        return null;
    }

    @Override
    public List<FileChunkMetaDTO> deleteFileChunks(List<String> fileChunkIds) {
        return null;
    }

}
