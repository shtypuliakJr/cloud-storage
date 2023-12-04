package edu.nau.cs.meta.service.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileSearchResultDTO {

    private String id;

    private String fileName;

    private String filePath;

    private Long fileSize;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long chunksCount;

    private List<ChunkSearchResultDTO> chunks;

    private String parentFolderId;

}
