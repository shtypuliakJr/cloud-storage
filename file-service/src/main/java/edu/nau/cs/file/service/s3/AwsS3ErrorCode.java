package edu.nau.cs.file.service.s3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AwsS3ErrorCode {

    NO_SUCH_KEY(404, "NoSuchKey"),
    SLOW_DOWN(503, "SlowDown"),
    INTERNAL_ERROR(500, "InternalError");

    private final int status;

    private final String code;

}
