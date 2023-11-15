package edu.nau.cs.file.service.service.upload;

import edu.nau.cs.file.service.dto.FileChunkMetaDTO;
import edu.nau.cs.file.service.dto.FileChunkUploadPayload;

import java.util.List;

public interface UploadFileChunkService {

    FileChunkMetaDTO uploadFileChunk(FileChunkUploadPayload fileChunkUploadPayload);

    List<FileChunkMetaDTO> uploadFileChunks(List<FileChunkUploadPayload> fileChunkUploadPayloads);

}
