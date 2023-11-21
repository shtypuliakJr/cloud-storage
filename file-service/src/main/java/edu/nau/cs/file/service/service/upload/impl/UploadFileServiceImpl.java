package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.FileChunkPayload;
import edu.nau.cs.file.service.dto.FileChunkUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.s3.S3Item;
import edu.nau.cs.file.service.service.upload.UploadFileService;
import edu.nau.cs.file.service.util.S3KeyPathResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final AwsS3Service awsS3Service;
    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public FileUploadDTO uploadFile(FileUploadPayload fileUploadPayload, String userId, String path, String folderParentId) {
        final String fileId = UUID.randomUUID().toString();
        final String chunkId = UUID.randomUUID().toString();

        String chunkPath = S3KeyPathResolver.toChunkPath(userId, fileId, chunkId);

        FileChunkPayload fileChunkPayload = FileChunkPayload.builder()
                .chunkId(chunkId)
                .s3Key(chunkPath)
                .size(fileUploadPayload.getSize())
                .body(fileUploadPayload.getBody())
                .build();

        S3Item s3Item = awsS3Service.uploadObject(fileChunkPayload, bucket);
        return FileUploadDTO.builder()
                .withOriginalFilename(fileUploadPayload.getOriginalFilename())
                .build();
    }

    @Override
    public List<FileUploadDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads, String userId, String path, String folderParentId) {
        return null;
    }

    @Override
    public FileChunkUploadDTO uploadFileChunk(String fileId, FileUploadPayload filePayload, String userId, int chunkPosition, int chunkCount) {
        return null;
    }

    @Override
    public List<FileChunkUploadDTO> uploadFileChunks(String fileId, List<FileUploadPayload> filePayload, String userId, int chunkPosition, int chunkCount) {
        return null;
    }

}
