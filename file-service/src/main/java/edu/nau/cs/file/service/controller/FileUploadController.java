package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.FileUploadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileUploadController implements FileUploadControllerApi {

    @Override
    public ResponseEntity<FileUploadDTO> uploadFile(MultipartFile file) {
        return ResponseEntity.ok(FileUploadDTO.builder().withOriginalFilename(file.getOriginalFilename()).build());
    }

}
