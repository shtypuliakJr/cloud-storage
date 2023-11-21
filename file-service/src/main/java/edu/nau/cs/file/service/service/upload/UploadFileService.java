package edu.nau.cs.file.service.service.upload;

import edu.nau.cs.file.service.dto.FileChunkUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;

import java.util.List;

public interface UploadFileService {

    FileUploadDTO uploadFile(FileUploadPayload fileUploadPayload, String userId, String path, String folderParentId);

    List<FileUploadDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads, String userId, String path, String folderParentId);

    FileChunkUploadDTO uploadFileChunk(String fileId, FileUploadPayload filePayload, String userId, int chunkPosition, int chunkCount);

    List<FileChunkUploadDTO> uploadFileChunks(String fileId, List<FileUploadPayload> filePayload, String userId, int chunkPosition, int chunkCount);

}
