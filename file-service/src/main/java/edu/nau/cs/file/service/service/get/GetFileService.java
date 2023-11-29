package edu.nau.cs.file.service.service.get;

import edu.nau.cs.file.service.dto.FileGetResponseDTO;

import java.util.List;

public interface GetFileService {

    FileGetResponseDTO getFile(String userId, String fileId);

    FileGetResponseDTO getArchivedFiles(String userId, List<String> fileIds);

}
