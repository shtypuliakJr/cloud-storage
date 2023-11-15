package edu.nau.cs.file.service.controller;

import edu.nau.cs.file.service.dto.FileUploadDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class UploadFileController implements UploadFileControllerApi {

    @Value(value = "${s3.data.bucket}")
    private String bucketName;

    private final AwsS3Service awsS3Service;

    @SneakyThrows
    @Override
    public ResponseEntity<FileUploadDTO> uploadFile(MultipartFile file) {
        FileUploadPayload fileUploadPayload = FileUploadPayload.builder()
                .withS3Key("/test-dir/" + file.getOriginalFilename())
                .withBody(file.getInputStream())
                .withSize(file.getSize())
                .build();

        awsS3Service.uploadFile(fileUploadPayload, bucketName);

        FileUploadDTO fileUploadDTO = FileUploadDTO.builder().withOriginalFilename(file.getOriginalFilename()).build();
        return ResponseEntity.ok(fileUploadDTO);
    }

}
