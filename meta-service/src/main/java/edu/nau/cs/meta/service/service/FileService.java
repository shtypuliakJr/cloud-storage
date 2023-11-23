package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.FileObjectDTO;

import java.util.List;

public interface FileService {

    FileObjectDTO getFileData(String userId, String fileId);

    List<FileObjectDTO> getFilesData(String userId, List<String> fileIds);

    FileObjectDTO saveFileData(String userId, FileObjectDTO fileObjectDTO);

    FileObjectDTO editFileData(String userId, String fileId, FileObjectDTO fileObjectDTO);

    void deleteFileData(String userId, String fileId);

    void deleteFilesData(String userId, List<String> fileIds);

}
