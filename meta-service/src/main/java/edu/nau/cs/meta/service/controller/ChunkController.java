package edu.nau.cs.meta.service.controller;

import edu.nau.cs.meta.service.dto.FileObjectDTO;
import edu.nau.cs.meta.service.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.nau.cs.meta.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
public class ChunkController {

    private final FileService fileService;
    private final String userId = USER_ID;

    @GetMapping("/{fileId}")
    public ResponseEntity<FileObjectDTO> getFileData(@PathVariable String fileId) {
        return ResponseEntity.ok(fileService.getFileData(userId, fileId));
    }

    @PostMapping
    public ResponseEntity<FileObjectDTO> saveFileData(@RequestBody FileObjectDTO fileObjectDTO) {
        return ResponseEntity.ok(fileService.saveFileData(fileObjectDTO.getUserId(), fileObjectDTO));
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<FileObjectDTO> editFileData(@PathVariable String fileId, @RequestBody FileObjectDTO fileObjectDTO) {
        return ResponseEntity.ok(fileService.editFileData(userId, fileId, fileObjectDTO));
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFileData(@PathVariable String fileId) {
        fileService.deleteFileData(userId, fileId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFilesData(@RequestParam List<String> fileIds) {
        fileService.deleteFilesData(userId, fileIds);
        return ResponseEntity.noContent().build();
    }

}
