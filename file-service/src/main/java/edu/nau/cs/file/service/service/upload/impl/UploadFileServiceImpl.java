package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.FileChunkUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.dto.S3FileChunkPayload;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.service.chunk.ChunkService;
import edu.nau.cs.file.service.service.rest.RestMetaService;
import edu.nau.cs.file.service.service.upload.UploadFileService;
import edu.nau.cs.file.service.util.S3KeyPathResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final AwsS3Service awsS3Service;
    private final ChunkService chunkService;
    private final RestMetaService restMetaService;

    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public FileUploadDTO uploadFile(FileUploadPayload fileUploadPayload, String userId, String path, String folderParentId) {
        final String fileId = UUID.randomUUID().toString();
        String filePathS3Key = S3KeyPathResolver.toFilePath(userId, fileId);

        List<S3FileChunkPayload> s3FileChunkPayloads = chunkService.processFile(fileUploadPayload, filePathS3Key);

        awsS3Service.uploadObjects(s3FileChunkPayloads, bucket);
        FileObjectDTO fileObjectDTO = FileObjectDTO.builder()
                .withFileName(fileUploadPayload.getOriginalFilename())
                .withFileType(fileUploadPayload.getContentType())
                .withS3Path(filePathS3Key)
                .withParentFolderId(folderParentId)
                .withChunks(s3FileChunkPayloads.stream().map(s3FileChunkPayload -> FileChunkDTO.builder()
                                .withChunkOrder(s3FileChunkPayload.getChunkOrder())
                                .withChunkSize(s3FileChunkPayload.getSize())
                                .withChunkChecksum(s3FileChunkPayload.getChecksum())
                                .withS3Key(s3FileChunkPayload.getS3Key())
                                .build())
                        .collect(Collectors.toList()))
                .withUserId(userId)
                .build();

        FileObjectDTO response = restMetaService.saveFileObject(fileObjectDTO);

        List<FileChunkUploadDTO> fileChunkUploadDTOs = s3FileChunkPayloads.stream()
                .map(s3FileChunkPayload -> FileChunkUploadDTO.builder()
                        .withChunkId(s3FileChunkPayload.getChunkId())
                        .withChunkObjectKey(s3FileChunkPayload.getS3Key())
                        .withChunkPosition(s3FileChunkPayload.getChunkOrder())
                        .withChecksumCRC32(s3FileChunkPayload.getChecksum())
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
