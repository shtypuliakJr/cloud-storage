package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.FileMetaDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.service.upload.UploadFileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class UploadFileController implements UploadFileControllerApi {

    private final UploadFileService uploadFileService;

    @SneakyThrows
    @Override
    public ResponseEntity<FileMetaDTO> uploadFile(MultipartFile file) {
        FileUploadPayload fileUploadPayload = FileUploadPayload.builder()
                .withS3Key("/test-dir/" + file.getOriginalFilename())
                .withBody(file.getInputStream())
                .withSize(file.getSize())
                .build();
        return ResponseEntity.ok(uploadFileService.uploadFile(fileUploadPayload));
    }

}
