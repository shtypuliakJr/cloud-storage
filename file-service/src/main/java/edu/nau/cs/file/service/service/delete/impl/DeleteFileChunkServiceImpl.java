package edu.nau.cs.file.service.service.delete.impl;

import edu.nau.cs.file.service.dto.FileChunkDeleteDTO;
import edu.nau.cs.file.service.service.delete.DeleteFileChunkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteFileChunkServiceImpl implements DeleteFileChunkService {

    @Override
    public FileChunkDeleteDTO deleteFileChunk(String fileId, String fileChunkId) {
        return null;
    }

    @Override
    public List<FileChunkDeleteDTO> deleteFileChunks(List<String> fileChunkIds) {
        return null;
    }

}
