package edu.nau.cs.file.service.service.get;

import edu.nau.cs.file.service.dto.FileDTO;

import java.util.List;

public interface GetFileService {

    FileDTO getFile(String fileId);

    List<FileDTO> getFiles(List<String> fileIds);

}
