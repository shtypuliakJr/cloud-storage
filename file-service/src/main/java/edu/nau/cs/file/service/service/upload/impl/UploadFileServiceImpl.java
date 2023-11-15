package edu.nau.cs.file.service.service.upload.impl;

import edu.nau.cs.file.service.dto.FileMetaDTO;
import edu.nau.cs.file.service.dto.FileUploadPayload;
import edu.nau.cs.file.service.service.upload.UploadFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Override
    public FileMetaDTO uploadFile(FileUploadPayload fileUploadPayload) {
        return null;
    }

    @Override
    public List<FileMetaDTO> uploadFiles(List<FileUploadPayload> fileUploadPayloads) {
        return null;
    }

}
