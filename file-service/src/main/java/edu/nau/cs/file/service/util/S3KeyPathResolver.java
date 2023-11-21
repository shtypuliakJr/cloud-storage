package edu.nau.cs.file.service.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class S3KeyPathResolver {

    private static final String KEY_SEPARATOR = "/";
    private static final String S3_PATH_TEMPLATE = "{userLeftPartition}/{userRightPartition}/{userId}/{fileId}";

    public static String toFilePath(String filePath, String chunkId) {
        return filePath + KEY_SEPARATOR + chunkId;
    }

    public static String toChunkPath(String userId, String fileId, String chunkId) {
        final Integer userLeftPartition = Math.abs(userId.hashCode() % 1000 / 10);
        final Integer userRightPartition = Math.abs(userId.hashCode() % 10);
        return toChunkPath(userLeftPartition, userRightPartition, userId, fileId, chunkId);
    }

    public static String toFilePath(Integer userLeftPartition, Integer userRightPartition, String userId, String fileId) {
        return S3_PATH_TEMPLATE
                .replace("{userLeftPartition}", userLeftPartition.toString())
                .replace("{userRightPartition}", userRightPartition.toString())
                .replace("{userId}", userId)
                .replace("{fileId}", fileId);
    }

    public static String toChunkPath(Integer userLeftPartition, Integer userRightPartition, String userId, String fileId, String chunkId) {
        return toFilePath(toFilePath(userLeftPartition, userRightPartition, userId, fileId), chunkId);
    }

}
