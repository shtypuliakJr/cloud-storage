package edu.nau.cs.file.service.service.delete.impl;

import edu.nau.cs.file.service.dto.ChunkObjectDeleteDTO;
import edu.nau.cs.file.service.dto.FileObjectDeleteDTO;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.service.delete.DeleteFileService;
import edu.nau.cs.file.service.service.rest.RestFileMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteFileServiceImpl implements DeleteFileService {

    private final AwsS3Service awsS3Service;
    private final RestFileMetaService restFileMetaService;

    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public FileObjectDeleteDTO deleteFile(String fileId, String userId) {
        FileObjectDTO fileObjectDTO = restFileMetaService.getFileObject(fileId);
        List<String> chunkS3Keys = fileObjectDTO.getChunks().stream().map(FileChunkDTO::getS3Key).toList();
        restFileMetaService.deleteFileObject(fileId);
        awsS3Service.deleteObjects(chunkS3Keys, bucket);
        return FileObjectDeleteDTO.builder()
                .fileId(fileObjectDTO.getFileObjectId())
                .fileName(fileObjectDTO.getFileName())
                .filePath(fileObjectDTO.getFilePath())
                .userId(fileObjectDTO.getUserId())
                .fileSize(fileObjectDTO.getChunks().stream().map(FileChunkDTO::getChunkSize).reduce(Long::sum).orElse(null))
                .chunks(fileObjectDTO.getChunks().stream()
                        .map(fileChunkDTO -> ChunkObjectDeleteDTO.builder()
                                .chunkId(fileChunkDTO.getChunkId())
                                .chunkSize(fileChunkDTO.getChunkSize())
                                .build())
                        .toList())
                .build();
    }

    @Override
    public List<FileObjectDeleteDTO> deleteFiles(List<String> fileIds, String userId) {
        return fileIds.stream().map(fileId -> this.deleteFile(fileId, userId)).toList();
    }

}
