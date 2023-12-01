package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.payload.FileUploadPayload;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.service.upload.UploadFileChunkService;
import edu.nau.cs.file.service.service.upload.UploadFileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static edu.nau.cs.file.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
public class UploadFileController implements UploadFileControllerApi {

    private final UploadFileService uploadFileService;
    private final UploadFileChunkService uploadFileChunkService;
    private final String userId = USER_ID;

    @Override
    public ResponseEntity<FileObjectDTO> uploadFile(MultipartFile file, String path, String folderParentId) {
        return ResponseEntity.ok(uploadFileService.uploadFile(createFilePayload(file), userId, path, folderParentId));
    }

    @Override
    public ResponseEntity<List<FileObjectDTO>> uploadBulkFiles(List<MultipartFile> files, String path, String folderParentId) {
        List<FileUploadPayload> fileUploadPayloads = files.stream().map(this::createFilePayload).toList();
        return ResponseEntity.ok(uploadFileService.uploadFiles(fileUploadPayloads, userId, path, folderParentId));
    }

    @Override
    public ResponseEntity<FileChunkDTO> uploadFileChunk(String fileId, MultipartFile chunk, Integer chunkPosition, Integer chunksCount) {
        return ResponseEntity.ok(uploadFileChunkService.uploadFileChunk(fileId, createFilePayload(chunk), userId, chunkPosition, chunksCount));
    }

    @Override
    public ResponseEntity<List<FileChunkDTO>> uploadBulkFileChunks(String fileId, List<MultipartFile> chunks, Integer chunkPosition, Integer chunksCount) {
        List<FileUploadPayload> fileUploadPayloads = chunks.stream().map(this::createFilePayload).toList();
        return ResponseEntity.ok(uploadFileChunkService.uploadFileChunks(fileId, fileUploadPayloads, userId, chunkPosition, chunksCount));
    }

    @SneakyThrows
    private FileUploadPayload createFilePayload(MultipartFile file) {
        return FileUploadPayload.builder()
                .withOriginalFilename(file.getOriginalFilename())
                .withContentType(file.getContentType())
                .withBody(file.getInputStream())
                .withSize(file.getSize())
                .build();
    }

}
