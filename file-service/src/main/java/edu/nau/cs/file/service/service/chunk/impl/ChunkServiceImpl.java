package edu.nau.cs.file.service.service.chunk.impl;

import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.dto.S3FileChunkPayload;
import edu.nau.cs.file.service.service.chunk.ChunkService;
import edu.nau.cs.file.service.util.ChecksumUtil;
import edu.nau.cs.file.service.util.S3KeyPathResolver;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ChunkServiceImpl implements ChunkService {

    private static final Long CHUNK_SIZE = DataSize.ofMegabytes(4).toBytes();

    @SneakyThrows
    @Override
    public List<S3FileChunkPayload> processFile(FileUploadPayload fileUploadPayload, String userId, String fileId) {
        final long fileSize = fileUploadPayload.getSize();
        if (fileSize <= CHUNK_SIZE) {
            String chunkId = UUID.randomUUID().toString();
            return Collections.singletonList(S3FileChunkPayload.builder()
                    .chunkId(chunkId)
                    .s3Key(S3KeyPathResolver.toChunkPath(userId, fileId, chunkId))
                    .size(fileSize)
                    .body(fileUploadPayload.getBody())
                    .build());
        }

        byte[] body = fileUploadPayload.getBody().readAllBytes();

        List<S3FileChunkPayload> s3FileChunkPayloads = new ArrayList<>();

        int chunkPosition = 0;
        for (int i = 0; i < fileSize; i++) {
            byte[] currentChunk = new byte[(int) Math.min(CHUNK_SIZE, fileSize - i)];
            for (int j = 0; j < currentChunk.length; j++, i++) {
                currentChunk[j] = body[i];
            }
            String chunkId = UUID.randomUUID().toString();
            s3FileChunkPayloads.add(S3FileChunkPayload.builder()
                    .chunkId(chunkId)
                    .chunkOrder(chunkPosition++)
                    .s3Key(S3KeyPathResolver.toChunkPath(userId, fileId, chunkId))
                    .size(currentChunk.length)
                    .body(new ByteArrayInputStream(currentChunk))
                    .checkSum(ChecksumUtil.getCRC32Checksum(currentChunk))
                    .build());
        }
        return s3FileChunkPayloads;
    }

}
