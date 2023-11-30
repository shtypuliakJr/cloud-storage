package edu.nau.cs.file.service.service.get.impl;

import edu.nau.cs.file.service.dto.ChunkGetResponseDTO;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.service.get.GetChunkService;
import edu.nau.cs.file.service.service.rest.RestChunkMetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RequiredArgsConstructor
@Service
public class GetChunkServiceImpl implements GetChunkService {

    private final RestChunkMetaService restChunkMetaService;
    private final AwsS3Service awsS3Service;

    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public ChunkGetResponseDTO getChunk(String userId, String fileId, String fileChunkId) {
        FileChunkDTO chunkObject = restChunkMetaService.getChunkObject(fileId);
        byte[] chunkBody = awsS3Service.getObject(chunkObject.getS3Key(), bucket);

        return ChunkGetResponseDTO.builder()
                .fileName(chunkObject.getFileObjectId() + "_" + chunkObject.getChunkOrder())
                .size(chunkObject.getChunkSize())
                .body(new ByteArrayInputStream(chunkBody))
                .build();
    }

    @Override
    public StreamingResponseBody getArchivedFileChunks(String userId, String fileId, List<String> chunkIds, OutputStream outputStream) {
        return responseOutputStream -> {
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
                for (String chunkId : chunkIds) {
                    ChunkGetResponseDTO chunkGetResponseDTO = this.getChunk(userId, fileId, chunkId);
                    InputStream inputStreamResource = chunkGetResponseDTO.getBody();

                    ZipEntry zipEntry = new ZipEntry(chunkGetResponseDTO.getFileName());
                    zipEntry.setSize(chunkGetResponseDTO.getSize());
                    zipEntry.setTime(System.currentTimeMillis());
                    zipOutputStream.putNextEntry(zipEntry);

                    StreamUtils.copy(inputStreamResource, zipOutputStream);
                    zipOutputStream.closeEntry();
                }
                zipOutputStream.finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
