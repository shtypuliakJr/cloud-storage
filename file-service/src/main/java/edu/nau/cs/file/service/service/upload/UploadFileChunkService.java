package edu.nau.cs.file.service.service.upload;

import edu.nau.cs.file.service.dto.FileChunkUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;

import java.util.List;

public interface UploadFileChunkService {

    FileChunkUploadDTO uploadFileChunk(String fileId, FileUploadPayload filePayload, String userId, Integer chunkPosition, Integer chunksCount);

    List<FileChunkUploadDTO> uploadFileChunks(String fileId, List<FileUploadPayload> fileUploadPayloads, String userId, Integer chunkPosition, Integer chunksCount);

}
