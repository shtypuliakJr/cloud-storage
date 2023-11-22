package edu.nau.cs.file.service.util;

import lombok.experimental.UtilityClass;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

@UtilityClass
public final class ChecksumUtil {

    public static long getCRC32Checksum(byte[] bytes) {
        Checksum crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }

}
