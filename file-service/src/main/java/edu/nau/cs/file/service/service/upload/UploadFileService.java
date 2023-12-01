package edu.nau.cs.file.service.service.upload;

import edu.nau.cs.file.service.dto.payload.FileUploadPayload;
import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;

import java.util.List;

public interface UploadFileService {

    FileObjectDTO uploadFile(FileUploadPayload fileUploadPayload, String userId, String path, String folderParentId);

    List<FileObjectDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads, String userId, String path, String folderParentId);

}
