package edu.nau.cs.file.service.service.upload;

import edu.nau.cs.file.service.dto.payload.FileUploadPayload;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;

import java.util.List;

public interface UploadFileChunkService {

    FileChunkDTO uploadFileChunk(String fileId, FileUploadPayload filePayload, String userId, Integer chunkPosition, Integer chunksCount);

    List<FileChunkDTO> uploadFileChunks(String fileId, List<FileUploadPayload> fileUploadPayloads, String userId, Integer chunkPosition, Integer chunksCount);

}
