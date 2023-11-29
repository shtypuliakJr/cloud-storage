package edu.nau.cs.file.service.service.rest;

import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import lombok.NonNull;

import java.util.List;

public interface RestFileMetaService {

    FileObjectDTO getFileObject(@NonNull String fileId);

    FileObjectDTO saveFileObject(@NonNull FileObjectDTO payload);

    void deleteFileObject(@NonNull String fileId);

    void deleteFileObjects(@NonNull List<String> fileIds);

}
