package edu.nau.cs.file.service.service.rest;

import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;

public interface RestMetaService {

    FileObjectDTO saveFileObject(FileObjectDTO payload);

}
