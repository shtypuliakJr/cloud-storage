package edu.nau.cs.file.service.service.get;

import edu.nau.cs.file.service.dto.ChunkGetResponseDTO;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;
import java.util.List;

public interface GetChunkService {

    ChunkGetResponseDTO getChunk(String userId, String fileId, String fileChunkId);

    StreamingResponseBody getArchivedFileChunks(String userId, String fileId, List<String> chunkIds, OutputStream outputStream);

}
