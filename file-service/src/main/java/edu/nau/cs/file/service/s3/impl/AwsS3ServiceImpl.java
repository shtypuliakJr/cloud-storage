package edu.nau.cs.file.service.s3.impl;

import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.s3.AwsS3Service;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RequiredArgsConstructor
@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    private final S3Client s3Client;

    @Override
    public void uploadFile(@NonNull FileUploadPayload fileUploadPayload, String bucketName) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileUploadPayload.getS3Key())
                .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(fileUploadPayload.getBody(), fileUploadPayload.getSize()));
    }

}
