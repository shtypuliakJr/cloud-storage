package edu.nau.cs.file.service.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileChunkDTO {

    private String chunkId;

    private String fileId;

    private Long chunkOrder;

    private String s3Key;

    private Long chunkSize;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
