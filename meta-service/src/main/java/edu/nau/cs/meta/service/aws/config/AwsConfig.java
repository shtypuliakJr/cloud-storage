package edu.nau.cs.meta.service.aws.config;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.awspring.cloud.sqs.operations.TemplateAcknowledgementMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class AwsConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${notification.updates.queue.name}")
    private String queueName;

    @Value("${aws.sqs.url}")
    private String sqsUrl;

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient) {
        return SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .configure(options -> options
                        .acknowledgementMode(TemplateAcknowledgementMode.MANUAL)
                        .defaultQueue(queueName))
                .build();
    }

    @Bean
    @Primary
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(awsRegion))
                .endpointOverride(URI.create(sqsUrl))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

}
