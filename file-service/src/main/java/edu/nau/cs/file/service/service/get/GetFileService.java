package edu.nau.cs.file.service.service.get;

import edu.nau.cs.file.service.dto.FileObjectDTO;

import java.util.List;

public interface GetFileService {

    FileObjectDTO getFile(String fileId);

    List<FileObjectDTO> getFiles(List<String> fileIds);

    FileObjectDTO getArchivedFiles(List<String> fileIds);

    FileObjectDTO getArchivedFileChunks(String fileId, List<String> chunkIds);

}
