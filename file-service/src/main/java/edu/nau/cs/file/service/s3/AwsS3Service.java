package edu.nau.cs.file.service.s3;

import edu.nau.cs.file.service.dto.payload.S3FileChunkPayload;
import edu.nau.cs.file.service.exception.CsFileServiceS3FileIOException;

import java.io.InputStream;
import java.util.List;

public interface AwsS3Service {

    List<S3Item> deleteObjects(List<String> keys, String bucket);

    InputStream getObjectAsStream(String keyName, String bucketName);

    byte[] getObject(String keyName, String bucketName) throws CsFileServiceS3FileIOException;

    S3Item deleteObject(String key, String bucket);

    S3Item uploadObject(S3FileChunkPayload fileUploadPayload, String bucket);

    List<S3Item> uploadObjects(List<S3FileChunkPayload> fileUploadPayloads, String bucket);

}
