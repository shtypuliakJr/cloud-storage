package edu.nau.cs.meta.service.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChunkSearchResultDTO {

    private String id;

    private Long chunkOrder;

    private Long chunkSize;

    private Long chunkChecksum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String fileId;

}
