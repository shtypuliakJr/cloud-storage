package edu.nau.cs.file.service.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChunkGetResponseDTO {

    private String fileName;

    private Long size;

    private InputStream body;

}
