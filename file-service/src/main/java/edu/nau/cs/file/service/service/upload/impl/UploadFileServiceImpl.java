package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.FileChunkUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.dto.S3FileChunkPayload;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.s3.S3Item;
import edu.nau.cs.file.service.service.chunk.ChunkService;
import edu.nau.cs.file.service.service.upload.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

        List<S3Item> s3Items = awsS3Service.uploadObjects(s3FileChunkPayloads, bucket);

        ResponseEntity<String> response = restTemplate.getForEntity(URI.create("http://cs-meta-service:8080/hello/world"), String.class);
        System.out.println("response body = " + response.getBody());
        Iterator<S3Item> s3ItemIterator = s3Items.iterator();

        List<FileChunkUploadDTO> fileChunkUploadDTOs = s3FileChunkPayloads.stream()
                .map(s3FileChunkPayload -> FileChunkUploadDTO.builder()
                        .withChunkId(s3FileChunkPayload.getChunkId())
                        .withChunkObjectKey(s3FileChunkPayload.getS3Key())
                        .withChunkPosition(s3FileChunkPayload.getChunkPosition())
                        .withChecksumCRC32(s3FileChunkPayload.getCheckSum())
                        .withChecksumCRC32FromS3(s3ItemIterator.next().getChecksumCRC32())
                        .build())
                .toList();

        return FileUploadDTO.builder()
                .withOriginalFilename(fileUploadPayload.getOriginalFilename())
                .withFileChunkUploadDTOs(fileChunkUploadDTOs)
                .build();
    }

    @Override
    public List<FileUploadDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads, String userId, String path, String folderParentId) {
        return null;
    }

}
