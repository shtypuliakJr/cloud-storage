package edu.nau.cs.file.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChunkObjectDeleteDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1992311926689053048L;

    private String chunkId;

    private Long chunkSize;

}
