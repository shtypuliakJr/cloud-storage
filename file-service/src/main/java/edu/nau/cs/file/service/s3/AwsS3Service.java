package edu.nau.cs.file.service.s3;

import edu.nau.cs.file.service.dto.FileChunkPayload;
import edu.nau.cs.file.service.exception.CsFileServiceS3FileIOException;

import java.io.InputStream;

public interface AwsS3Service {

    InputStream getObjectAsStream(String keyName, String bucketName);

    byte[] getObject(String keyName, String bucketName) throws CsFileServiceS3FileIOException;

    S3Item deleteObject(String key, String bucket);

    S3Item uploadObject(FileChunkPayload fileUploadPayload, String bucket);

}
