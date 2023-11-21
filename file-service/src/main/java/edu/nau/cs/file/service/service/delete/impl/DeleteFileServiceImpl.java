package edu.nau.cs.file.service.service.delete.impl;

import edu.nau.cs.file.service.dto.FileObjectDeleteDTO;
import edu.nau.cs.file.service.service.delete.DeleteFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteFileServiceImpl implements DeleteFileService {

    @Override
    public FileObjectDeleteDTO deleteFile(String fileId) {
        return null;
    }

    @Override
    public List<FileObjectDeleteDTO> deleteFiles(List<String> fileIds) {
        return null;
    }

}
