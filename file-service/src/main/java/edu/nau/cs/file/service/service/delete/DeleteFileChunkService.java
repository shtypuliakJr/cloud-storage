package edu.nau.cs.file.service.service.delete;

import edu.nau.cs.file.service.dto.FileChunkMetaDTO;

import java.util.List;

public interface DeleteFileChunkService {

    FileChunkMetaDTO deleteFileChunk(String fileChunkId);

    List<FileChunkMetaDTO> deleteFileChunks(List<String> fileChunkIds);

}
