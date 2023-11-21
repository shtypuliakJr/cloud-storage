package edu.nau.cs.file.service.service.get.impl;

import edu.nau.cs.file.service.dto.FileChunkDTO;
import edu.nau.cs.file.service.service.get.GetFileChunkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFileChunkServiceImpl implements GetFileChunkService {

    @Override
    public FileChunkDTO getFileChunk(String fileId, String fileChunkId) {
        return null;
    }

    @Override
    public List<FileChunkDTO> getFileChunks(List<String> fileChunkIds) {
        return null;
    }

}
