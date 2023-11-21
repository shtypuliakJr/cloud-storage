package edu.nau.cs.file.service.service.get.impl;

import edu.nau.cs.file.service.dto.FileObjectDTO;
import edu.nau.cs.file.service.service.get.GetFileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFileServiceImpl implements GetFileService {

    @Override
    public FileObjectDTO getFile(String fileId) {
        return null;
    }

    @Override
    public List<FileObjectDTO> getFiles(List<String> fileIds) {
        return null;
    }

    @Override
    public FileObjectDTO getArchivedFiles(List<String> fileIds) {
        return null;
    }

    @Override
    public FileObjectDTO getArchivedFileChunks(String fileId, List<String> chunkIds) {
        return null;
    }

}

