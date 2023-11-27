package edu.nau.cs.meta.service.service.rest;

import edu.nau.cs.meta.service.dto.FileObjectDTO;

public interface RestFileService {

    FileObjectDTO saveFileObject(FileObjectDTO payload);

}
