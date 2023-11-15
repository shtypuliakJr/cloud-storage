package edu.nau.cs.file.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.time.Duration;

@Configuration
public class AwsS3Config {

    private final String awsRegion;
    private final String awsS3FsEndpoint;

    @Autowired
    public AwsS3Config(@Value(value = "${aws.region}") String awsRegion,
                       @Value(value = "${aws.s3.fs.endpoint}") String awsS3FsEndpoint) {
        this.awsRegion = awsRegion;
        this.awsS3FsEndpoint = awsS3FsEndpoint;
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider awsCredentialsProvider,
                             ClientOverrideConfiguration clientOverrideConfiguration) {
        return S3Client.builder()
                .region(Region.of(awsRegion))
                .endpointOverride(URI.create(awsS3FsEndpoint))
                .credentialsProvider(awsCredentialsProvider)
                .overrideConfiguration(clientOverrideConfiguration)
                .httpClient(ApacheHttpClient.create())
                .forcePathStyle(true)
                .build();
    }

    @Bean
    public S3AsyncClient s3AsyncClient(AwsCredentialsProvider awsCredentialsProvider,
                                       ClientOverrideConfiguration clientOverrideConfiguration) {
        return S3AsyncClient.builder()
                .region(Region.of(awsRegion))
                .endpointOverride(URI.create(awsS3FsEndpoint))
                .credentialsProvider(awsCredentialsProvider)
                .overrideConfiguration(clientOverrideConfiguration)
                .forcePathStyle(true)
                .build();
    }

    @Bean
    @Primary
    protected ClientOverrideConfiguration clientOverrideConfiguration() {
        return ClientOverrideConfiguration.builder()
                .apiCallAttemptTimeout(Duration.ofSeconds(1))
                .retryPolicy(RetryPolicy.builder().numRetries(10).build())
                .build();
    }

    @Bean
    @Primary
    protected AwsCredentialsProvider awsCredentialsProvider() {
        return DefaultCredentialsProvider.create();
    }

}
