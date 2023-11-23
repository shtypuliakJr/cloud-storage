package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.FileChunkUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.dto.S3FileChunkPayload;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.service.chunk.ChunkService;
import edu.nau.cs.file.service.service.upload.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final AwsS3Service awsS3Service;
    private final ChunkService chunkService;
    private final RestTemplate restTemplate;

    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public FileUploadDTO uploadFile(FileUploadPayload fileUploadPayload, String userId, String path, String folderParentId) {
        final String fileId = UUID.randomUUID().toString();
        List<S3FileChunkPayload> s3FileChunkPayloads = chunkService.processFile(fileUploadPayload, userId, fileId);

        awsS3Service.uploadObjects(s3FileChunkPayloads, bucket);

        ResponseEntity<FileObjectDTO> response = restTemplate.postForEntity(URI.create("http://cs-meta-service:8080/cs-api/files"),
                FileObjectDTO.builder()
                        .withOriginalName(fileUploadPayload.getOriginalFilename())
                        .withObjectType(fileUploadPayload.getContentType())
                        .withIsFolder(false)
                        .withS3Path(null)
                        .withChunks(s3FileChunkPayloads.stream().map(s3FileChunkPayload -> FileChunkDTO.builder()
                                        .chunkOrder(s3FileChunkPayload.getChunkPosition())
                                        .s3Key(s3FileChunkPayload.getS3Key())
                                        .chunkSize(s3FileChunkPayload.getSize())
                                        .build())
                                .collect(Collectors.toList()))
                        .withUserId(userId)
                        .build(),
                FileObjectDTO.class);

        List<FileChunkUploadDTO> fileChunkUploadDTOs = s3FileChunkPayloads.stream()
                .map(s3FileChunkPayload -> FileChunkUploadDTO.builder()
                        .withChunkId(s3FileChunkPayload.getChunkId())
                        .withChunkObjectKey(s3FileChunkPayload.getS3Key())
                        .withChunkPosition(s3FileChunkPayload.getChunkPosition())
                        .withChecksumCRC32(s3FileChunkPayload.getCheckSum())
                        .build())
                .toList();

        return FileUploadDTO.builder()
                .withOriginalFilename(fileUploadPayload.getOriginalFilename())
                .withFileChunkUploads(fileChunkUploadDTOs)
                .build();
    }

    @Override
    public List<FileUploadDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads, String userId, String path, String folderParentId) {
        return null;
    }

}
