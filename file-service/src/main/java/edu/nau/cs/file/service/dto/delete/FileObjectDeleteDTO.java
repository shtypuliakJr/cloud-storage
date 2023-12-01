package edu.nau.cs.file.service.dto.delete;

import edu.nau.cs.file.service.dto.delete.ChunkObjectDeleteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileObjectDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4422176605346870369L;

    private String fileId;

    private String fileName;

    private String filePath;

    private Long fileSize;

    private String userId;

    private List<ChunkObjectDeleteDTO> chunks;

}
