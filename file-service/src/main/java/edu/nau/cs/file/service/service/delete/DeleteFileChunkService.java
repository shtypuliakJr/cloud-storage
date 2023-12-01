package edu.nau.cs.file.service.service.delete;

import edu.nau.cs.file.service.dto.FileChunkDeleteDTO;

import java.util.List;

public interface DeleteFileChunkService {

    FileChunkDeleteDTO deleteFileChunk(String fileId, String chunkId, String userId);

    List<FileChunkDeleteDTO> deleteFileChunks(String fileId, List<String> chunkIds, String userId);

}
