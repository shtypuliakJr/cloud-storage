package edu.nau.cs.file.service.service.delete;

import edu.nau.cs.file.service.dto.delete.FileObjectDeleteDTO;

import java.util.List;

public interface DeleteFileService {

    FileObjectDeleteDTO deleteFile(String fileId, String userId);

    List<FileObjectDeleteDTO> deleteFiles(List<String> fileIds, String userId);

}
