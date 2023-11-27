package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.FileChunkUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.service.upload.UploadFileChunkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadFileChunkServiceImpl implements UploadFileChunkService {


    @Override
    public FileChunkDTO uploadFileChunk(String fileId, FileUploadPayload filePayload, String userId, Integer chunkPosition, Integer chunksCount) {
        return null;
    }

    @Override
    public List<FileChunkDTO> uploadFileChunks(String fileId, List<FileUploadPayload> fileUploadPayloads, String userId, Integer chunkPosition, Integer chunksCount) {
        return null;
    }

}
