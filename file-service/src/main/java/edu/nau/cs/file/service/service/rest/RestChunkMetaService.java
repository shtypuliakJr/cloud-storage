package edu.nau.cs.file.service.service.rest;

import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import lombok.NonNull;

import java.util.List;

public interface RestChunkMetaService {

    FileChunkDTO getChunkObject(@NonNull String chunkId, @NonNull String fileId);

    FileChunkDTO saveChunkObject(@NonNull FileChunkDTO fileObjectDTO);

    void deleteChunkObject(@NonNull String fileId);

    void deleteChunkObjects(@NonNull List<String> fileIds);

}
