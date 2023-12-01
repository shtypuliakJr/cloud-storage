package edu.nau.cs.file.service.s3.impl;

import edu.nau.cs.file.service.dto.S3FileChunkPayload;
import edu.nau.cs.file.service.exception.CsFileServiceS3FileIOException;
import edu.nau.cs.file.service.s3.AwsS3ErrorCode;
import edu.nau.cs.file.service.s3.AwsS3Service;
import edu.nau.cs.file.service.s3.S3Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AwsS3ServiceImpl implements AwsS3Service {

    private final S3Client s3Client;

    @Override
    public byte[] getObject(String keyName, String bucketName) throws CsFileServiceS3FileIOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .key(keyName)
                .bucket(bucketName)
                .build();
        try (ResponseInputStream<GetObjectResponse> responseInputStream = this.s3Client.getObject(getObjectRequest, ResponseTransformer.toInputStream());
             ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            IOUtils.copy(responseInputStream, outStream);
            return outStream.toByteArray();
        } catch (Exception e) {
            if (e instanceof NoSuchKeyException && AwsS3ErrorCode.NO_SUCH_KEY.getStatus() == ((NoSuchKeyException) e).statusCode()) {
                return null;
            }
            throw new CsFileServiceS3FileIOException(e.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public S3Item uploadObject(S3FileChunkPayload fileUploadPayload, String bucket) {
        PutObjectResponse response = s3Client.putObject(request -> request
                        .key(fileUploadPayload.getS3Key())
                        .contentLength(fileUploadPayload.getChunkSize())
                        .bucket(bucket),
                RequestBody.fromBytes(fileUploadPayload.getBody().readAllBytes()));
        return new S3Item(fileUploadPayload.getS3Key(), response.eTag(), response.versionId());
    }

    @Override
    public List<S3Item> uploadObjects(List<S3FileChunkPayload> fileUploadPayloads, String bucket) {
        return fileUploadPayloads.stream()
                .map(fileUploadPayload -> this.uploadObject(fileUploadPayload, bucket))
                .toList();
    }

    @Override
    public S3Item deleteObject(String key, String bucket) {
        DeleteObjectResponse response = s3Client.deleteObject(request -> request.key(key).bucket(bucket));
        return new S3Item(key, null, response.versionId());
    }

    @Override
    public List<S3Item> deleteObjects(List<String> keys, String bucket) {
        return keys.stream()
                .map(key -> this.deleteObject(key, bucket))
                .toList();
    }

    @Override
    public InputStream getObjectAsStream(String keyName, String bucketName) {
        ResponseBytes<GetObjectResponse> response = s3Client.getObject(request ->
                request.key(keyName).bucket(bucketName), ResponseTransformer.toBytes());
        return Objects.requireNonNull(response).asInputStream();
    }

}
