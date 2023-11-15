package edu.nau.cs.file.service.service.get.impl;

import edu.nau.cs.file.service.dto.FileDTO;
import edu.nau.cs.file.service.service.get.GetFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFileServiceImpl implements GetFileService {

    @Override
    public FileDTO getFile(String fileId) {
        return null;
    }

    @Override
    public List<FileDTO> getFiles(List<String> fileIds) {
        return null;
    }

}
