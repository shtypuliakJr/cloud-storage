package edu.nau.cs.file.service.service.delete.impl;

import edu.nau.cs.file.service.dto.FileMetaDTO;
import edu.nau.cs.file.service.service.delete.DeleteFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteFileServiceImpl implements DeleteFileService {

    @Override
    public FileMetaDTO deleteFile(String fileId) {
        return null;
    }

    @Override
    public List<FileMetaDTO> deleteFiles(List<String> fileIds) {
        return null;
    }

}
