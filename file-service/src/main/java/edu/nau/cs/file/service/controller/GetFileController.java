package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.get.ChunkGetResponseDTO;
import edu.nau.cs.file.service.dto.get.FileGetResponseDTO;
import edu.nau.cs.file.service.service.get.GetChunkService;
import edu.nau.cs.file.service.service.get.GetFileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static edu.nau.cs.file.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
public class GetFileController implements GetFileControllerApi {

    private final GetFileService getFileService;
    private final GetChunkService getChunkService;
    private final String userId = USER_ID;
    private final HttpServletResponse response;

    @Override
    public ResponseEntity<Resource> downloadFile(String fileId) {
        FileGetResponseDTO fileObjectDTO = getFileService.getFile(userId, fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileObjectDTO.getFileName()))
                .contentLength(fileObjectDTO.getSize())
                .contentType(MediaTypeFactory.getMediaType(fileObjectDTO.getFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(new InputStreamResource(fileObjectDTO.getBody()));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<StreamingResponseBody> downloadFilesZip(List<String> fileIds) {
        StreamingResponseBody streamingArchivedFilesResponse = getFileService.getArchivedFiles(userId, fileIds, response.getOutputStream());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=files.zip")
                .body(streamingArchivedFilesResponse);
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

    @SneakyThrows
    @Override
    public ResponseEntity<StreamingResponseBody> downloadFileChunksZip(String fileId, List<String> chunkIds) {
        StreamingResponseBody streamingArchivedChunksResponse = getChunkService.getArchivedFileChunks(userId, fileId, chunkIds, response.getOutputStream());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=chunks.zip")
                .body(streamingArchivedChunksResponse);
    }

}
