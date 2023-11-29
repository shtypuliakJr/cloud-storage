package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.ChunkGetResponseDTO;
import edu.nau.cs.file.service.dto.FileGetResponseDTO;
import edu.nau.cs.file.service.service.get.GetChunkService;
import edu.nau.cs.file.service.service.get.GetFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.nau.cs.file.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
public class GetFileController implements GetFileControllerApi {

    private final GetFileService getFileService;
    private final GetChunkService getChunkService;
    private final String userId = USER_ID;

    @Override
    public ResponseEntity<Resource> downloadFile(String fileId) {
        FileGetResponseDTO fileObjectDTO = getFileService.getFile(userId, fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileObjectDTO.getFileName()))
                .contentLength(fileObjectDTO.getSize())
                .contentType(MediaTypeFactory.getMediaType(fileObjectDTO.getFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(new InputStreamResource(fileObjectDTO.getBody()));
    }

    @Override
    public ResponseEntity<Resource> downloadFilesZip(List<String> fileIds) {
        FileGetResponseDTO archivedFiles = getFileService.getArchivedFiles(userId, fileIds);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", archivedFiles.getFileName()))
                .contentLength(archivedFiles.getSize())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(archivedFiles.getBody()));
    }

    @Override
    public ResponseEntity<Resource> downloadFileChunk(String fileId, String chunkId) {
        ChunkGetResponseDTO fileChunkDTO = getChunkService.getChunk(userId, fileId, chunkId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileChunkDTO.getFileName()))
                .contentLength(fileChunkDTO.getSize())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileChunkDTO.getBody()));
    }

    @Override
    public ResponseEntity<Resource> downloadFileChunksZip(String fileId, List<String> chunkIds) {
        ChunkGetResponseDTO archivedChunks = getChunkService.getArchivedFileChunks(userId, fileId, chunkIds);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", archivedChunks.getFileName()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(archivedChunks.getBody()));
    }

}
