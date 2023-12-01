package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.payload.FileUploadPayload;
import edu.nau.cs.file.service.dto.payload.S3FileChunkPayload;
import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.mapper.ChunkObjectDTOMapper;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.service.chunk.ChunkService;
import edu.nau.cs.file.service.service.rest.RestFileMetaService;
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
    private final ChunkService chunkService;
    private final RestFileMetaService restMetaService;

    private final ChunkObjectDTOMapper chunkObjectDTOMapper;

    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public FileObjectDTO uploadFile(FileUploadPayload fileUploadPayload, String userId, String path, String folderParentId) {
        final String fileId = UUID.randomUUID().toString();
        String filePathS3Key = S3KeyPathResolver.toFilePath(userId, fileId);

        List<S3FileChunkPayload> s3FileChunkPayloads = chunkService.processFile(fileUploadPayload, filePathS3Key);

        FileObjectDTO fileObjectDTO = FileObjectDTO.builder()
                .withFileObjectId(fileId)
                .withFileName(fileUploadPayload.getOriginalFilename())
                .withFileType(fileUploadPayload.getContentType())
                .withS3Path(filePathS3Key)
                .withParentFolderId(folderParentId)
                .withChunks(s3FileChunkPayloads.stream().map(chunkObjectDTOMapper::mapFileChunkObjectToDTO).toList())
                .withUserId(userId)
                .build();

        FileObjectDTO response = restMetaService.saveFileObject(fileObjectDTO);

        awsS3Service.uploadObjects(s3FileChunkPayloads, bucket);

        return response;
    }

    @Override
    public List<FileObjectDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads, String userId, String path, String folderParentId) {
        return fileUploadPayloads.stream()
                .map(fileUploadPayload -> this.uploadFile(fileUploadPayload, userId, path, folderParentId))
                .toList();
    }

}
