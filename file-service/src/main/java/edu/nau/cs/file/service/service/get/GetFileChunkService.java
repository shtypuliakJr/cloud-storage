package edu.nau.cs.file.service.service.get;

import edu.nau.cs.file.service.dto.FileChunkDTO;

import java.util.List;

public interface GetFileChunkService {

    FileChunkDTO getFileChunk(String fileId, String fileChunkId);

    List<FileChunkDTO> getFileChunks(List<String> fileChunkIds);

}
