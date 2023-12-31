package edu.nau.cs.file.service.s3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class S3Item {

    private final String objectKey;

    private final String eTag;

    private final String version;

}