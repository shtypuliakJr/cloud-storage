package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.FileChunkMetaDTO;
import edu.nau.cs.file.service.dto.FileChunkUploadPayload;
import edu.nau.cs.file.service.service.upload.UploadFileChunkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadFileChunkServiceImpl implements UploadFileChunkService {

    @Override
    public FileChunkMetaDTO uploadFileChunk(FileChunkUploadPayload fileChunkUploadPayload) {
        return null;
    }

    @Override
    public List<FileChunkMetaDTO> uploadFileChunks(List<FileChunkUploadPayload> fileChunkUploadPayloads) {
        return null;
    }

}
