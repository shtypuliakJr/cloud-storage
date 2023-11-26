package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.FileChunkDeleteDTO;
import edu.nau.cs.file.service.dto.FileObjectDeleteDTO;
import edu.nau.cs.file.service.service.delete.DeleteFileChunkService;
import edu.nau.cs.file.service.service.delete.DeleteFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DeleteFileController implements DeleteFileControllerApi {

    private final DeleteFileService deleteFileService;
    private final DeleteFileChunkService deleteFileChunkService;
    private final String userId = "2bb8e3d3-77fc-46c2-9ddc-8e83d593142d";

    @Override
    public ResponseEntity<FileObjectDeleteDTO> deleteFile(String fileId) {
        return ResponseEntity.ok(deleteFileService.deleteFile(fileId));
    }

    @Override
    public ResponseEntity<List<FileObjectDeleteDTO>> deleteBatchFiles(List<String> fileIds) {
        return ResponseEntity.ok(deleteFileService.deleteFiles(fileIds));
    }

    @Override
    public ResponseEntity<FileChunkDeleteDTO> deleteFileChunk(String fileId, String chunkId) {
        return ResponseEntity.ok(deleteFileChunkService.deleteFileChunk(fileId, chunkId));
    }

    @Override
    public ResponseEntity<List<FileChunkDeleteDTO>> deleteBatchFileChunks(String fileId, List<String> chunkIds) {
        return ResponseEntity.ok(deleteFileChunkService.deleteFileChunks(chunkIds));
    }

}
