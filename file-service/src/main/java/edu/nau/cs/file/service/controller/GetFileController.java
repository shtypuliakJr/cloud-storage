package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.FileChunkDTO;
import edu.nau.cs.file.service.dto.FileObjectDTO;
import edu.nau.cs.file.service.service.get.GetFileChunkService;
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

@RequiredArgsConstructor
@RestController
public class GetFileController implements GetFileControllerApi {

    private final GetFileService getFileService;
    private final GetFileChunkService getFileChunkService;
    private final String userId = "shtepa";

    @Override
    public ResponseEntity<Resource> downloadFile(String fileId) {
        FileObjectDTO fileObjectDTO = getFileService.getFile(userId, fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileObjectDTO.getOriginalFileName()))
                .contentLength(fileObjectDTO.getSize())
                .contentType(MediaTypeFactory.getMediaType(fileObjectDTO.getOriginalFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(new InputStreamResource(fileObjectDTO.getBody()));
    }

    @Override
    public ResponseEntity<Resource> downloadFileChunk(String fileId, String chunkId) {
        FileChunkDTO fileChunkDTO = getFileChunkService.getFileChunk(userId, fileId, chunkId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileChunkDTO.getOriginalFileName() + "-" + fileChunkDTO.getChunkId()))
                .contentLength(fileChunkDTO.getSize())
                .contentType(MediaTypeFactory.getMediaType(fileChunkDTO.getOriginalFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(new InputStreamResource(fileChunkDTO.getBody()));
    }

    @Override
    public ResponseEntity<Resource> downloadFilesZip(List<String> fileIds) {
        FileObjectDTO fileObjectDTO = getFileService.getArchivedFiles(userId, fileIds);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", "files.zip"))
                .contentType(MediaTypeFactory.getMediaType(fileObjectDTO.getOriginalFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(new InputStreamResource(fileObjectDTO.getBody()));
    }

    @Override
    public ResponseEntity<Resource> downloadFileChunksZip(String fileId, List<String> chunkIds) {
        FileObjectDTO fileObjectDTO = getFileService.getArchivedFileChunks(fileId, chunkIds);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", "files.zip"))
                .contentType(MediaTypeFactory.getMediaType(fileObjectDTO.getOriginalFileName()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(new InputStreamResource(fileObjectDTO.getBody()));
    }

}
