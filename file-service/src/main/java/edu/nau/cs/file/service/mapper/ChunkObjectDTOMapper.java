package edu.nau.cs.file.service.mapper;

import edu.nau.cs.file.service.dto.payload.S3FileChunkPayload;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChunkObjectDTOMapper {

    public FileChunkDTO mapFileChunkObjectToDTO(S3FileChunkPayload s3FileChunkPayload) {
        return FileChunkDTO.builder()
                .withChunkId(s3FileChunkPayload.getChunkId())
                .withChunkOrder(s3FileChunkPayload.getChunkOrder())
                .withChunkSize(s3FileChunkPayload.getChunkSize())
                .withChunkChecksum(s3FileChunkPayload.getChecksum())
                .withS3Key(s3FileChunkPayload.getS3Key())
                .withCreatedAt(s3FileChunkPayload.getCreatedAt())
                .withUpdatedAt(s3FileChunkPayload.getUpdatedAt())
                .withFileObjectId(s3FileChunkPayload.getFileObjectId())
                .build();
    }

}
