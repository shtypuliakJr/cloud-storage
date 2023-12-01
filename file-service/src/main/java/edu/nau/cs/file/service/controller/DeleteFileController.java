package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.delete.ChunkObjectDeleteDTO;
import edu.nau.cs.file.service.dto.delete.FileObjectDeleteDTO;
import edu.nau.cs.file.service.service.delete.DeleteFileChunkService;
import edu.nau.cs.file.service.service.delete.DeleteFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.nau.cs.file.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
public class DeleteFileController implements DeleteFileControllerApi {

    private final DeleteFileService deleteFileService;
    private final DeleteFileChunkService deleteFileChunkService;
    private final String userId = USER_ID;

    @Override
    public ResponseEntity<FileObjectDeleteDTO> deleteFile(String fileId) {
        return ResponseEntity.ok(deleteFileService.deleteFile(fileId, userId));
    }

    @Override
    public ResponseEntity<List<FileObjectDeleteDTO>> deleteBatchFiles(List<String> fileIds) {
        return ResponseEntity.ok(deleteFileService.deleteFiles(fileIds, userId));
    }

    @Override
    public ResponseEntity<ChunkObjectDeleteDTO> deleteFileChunk(String fileId, String chunkId) {
        return ResponseEntity.ok(deleteFileChunkService.deleteFileChunk(fileId, chunkId, userId));
    }

    @Override
    public ResponseEntity<List<ChunkObjectDeleteDTO>> deleteBatchFileChunks(String fileId, List<String> chunkIds) {
        return ResponseEntity.ok(deleteFileChunkService.deleteFileChunks(fileId, chunkIds, userId));
    }

}
