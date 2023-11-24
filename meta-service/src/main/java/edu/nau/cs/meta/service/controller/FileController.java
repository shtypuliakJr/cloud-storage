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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;
    private final String userId = "40231bd7-f047-4577-b8e8-028b263008c1";

    @GetMapping("/cs-api/files/{fileId}")
    public ResponseEntity<FileObjectDTO> getFileData(@PathVariable("fileId") String fileId) {
        return ResponseEntity.ok(fileService.getFileData(userId, fileId));
    }

    @PostMapping("/cs-api/files")
    public ResponseEntity<FileObjectDTO> saveFileData(@RequestBody FileObjectDTO fileObjectDTO) {
        return ResponseEntity.ok(fileService.saveFileData(fileObjectDTO.getUserId(), fileObjectDTO));
    }

    @PutMapping("/cs-api/files/{fileId}")
    public ResponseEntity<FileObjectDTO> editFileData(@PathVariable("fileId") String fileId, @RequestBody FileObjectDTO fileObjectDTO) {
        return ResponseEntity.ok(fileService.editFileData(userId, fileId, fileObjectDTO));
    }

    @DeleteMapping("/cs-api/files/{fileId}")
    public ResponseEntity<Void> deleteFileData(@PathVariable("fileId") String fileId) {
        fileService.deleteFileData(userId, fileId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteFilesData(@RequestBody List<String> fileIds) {
        fileService.deleteFilesData(userId, fileIds);
        return ResponseEntity.noContent().build();
    }

}
