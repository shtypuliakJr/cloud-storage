package edu.nau.cs.file.service.s3;

import edu.nau.cs.file.service.dto.FileUploadPayload;
import lombok.NonNull;

public interface AwsS3Service {

    void uploadFile(@NonNull FileUploadPayload fileUploadPayload, String bucketName);


}
