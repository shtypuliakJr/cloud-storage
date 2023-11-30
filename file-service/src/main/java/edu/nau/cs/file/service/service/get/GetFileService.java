package edu.nau.cs.file.service.service.get;

import edu.nau.cs.file.service.dto.FileGetResponseDTO;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.OutputStream;
import java.util.List;

public interface GetFileService {

    FileGetResponseDTO getFile(String userId, String fileId);

    StreamingResponseBody getArchivedFiles(String userId, List<String> fileIds, OutputStream outputStream);

}
