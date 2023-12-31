package edu.nau.cs.file.service.service.chunk;

import edu.nau.cs.file.service.dto.payload.FileUploadPayload;
import edu.nau.cs.file.service.dto.payload.S3FileChunkPayload;

import java.util.List;

public interface ChunkService {

    List<S3FileChunkPayload> processFile(FileUploadPayload fileUploadPayload, String userId, String fileId);

    List<S3FileChunkPayload> processFile(FileUploadPayload fileUploadPayload, String filePath);

}
