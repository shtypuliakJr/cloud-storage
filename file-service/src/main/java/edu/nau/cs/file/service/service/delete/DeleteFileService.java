package edu.nau.cs.file.service.service.delete;

import edu.nau.cs.file.service.dto.FileMetaDTO;

import java.util.List;

public interface DeleteFileService {

    FileMetaDTO deleteFile(String fileId);

    List<FileMetaDTO> deleteFiles(List<String> fileIds);

}
