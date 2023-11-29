package edu.nau.cs.file.service.service.get.impl;

import edu.nau.cs.file.service.dto.FileGetResponseDTO;
import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.service.get.GetFileService;
import edu.nau.cs.file.service.service.rest.RestFileMetaService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RequiredArgsConstructor
@Service
public class GetFileServiceImpl implements GetFileService {

    private final RestFileMetaService restFileMetaService;
    private final AwsS3Service awsS3Service;

    @Value(value = "${s3.data.bucket}")
    private String bucket;

    @Override
    public FileGetResponseDTO getFile(String userId, String fileId) {
        FileObjectDTO fileObject = restFileMetaService.getFileObject(fileId);

        ByteBuffer buffer = ByteBuffer.wrap(new byte[fileObject.getSize().intValue()]);
        fileObject.getChunks().stream()
                .sorted(Comparator.comparing(FileChunkDTO::getChunkOrder, Comparator.naturalOrder()))
                .map(fileChunkDTO -> awsS3Service.getObject(fileChunkDTO.getS3Key(), bucket))
                .forEachOrdered(buffer::put);

        return FileGetResponseDTO.builder()
                .fileName(fileObject.getFileName())
                .size(fileObject.getSize())
                .body(new ByteArrayInputStream(buffer.array()))
                .build();
    }

    @SneakyThrows
    @Override
    public FileGetResponseDTO getArchivedFiles(String userId, List<String> fileIds) {
        FileGetResponseDTO archivedFilesGetResponseDTO = new FileGetResponseDTO();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("files.zip"))) {
            for (String fileId : fileIds) {
                FileGetResponseDTO fileGetResponseDTO = this.getFile(userId, fileId);
                InputStream inputStreamResource = fileGetResponseDTO.getBody();

                ZipEntry zipEntry = new ZipEntry(fileGetResponseDTO.getFileName());
                zipEntry.setSize(fileGetResponseDTO.getSize());
                zipEntry.setTime(System.currentTimeMillis());
                zipOutputStream.putNextEntry(zipEntry);

                StreamUtils.copy(inputStreamResource, zipOutputStream);
                zipOutputStream.closeEntry();

                archivedFilesGetResponseDTO.setSize(archivedFilesGetResponseDTO.getSize() + fileGetResponseDTO.getSize());
            }
            zipOutputStream.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return archivedFilesGetResponseDTO;
    }

}

