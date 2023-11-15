package edu.nau.cs.file.service.service.upload;

import edu.nau.cs.file.service.dto.FileMetaDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;

import java.util.List;

public interface UploadFileService {

    FileMetaDTO uploadFile(FileUploadPayload fileUploadPayload);

    List<FileMetaDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads);

}
