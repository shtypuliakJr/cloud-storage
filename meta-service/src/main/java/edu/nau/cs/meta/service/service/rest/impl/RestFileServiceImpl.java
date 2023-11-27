package edu.nau.cs.meta.service.service.rest.impl;

import edu.nau.cs.meta.service.dto.FileObjectDTO;
import edu.nau.cs.meta.service.service.rest.RestFileService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static edu.nau.cs.meta.service.constants.Endpoint.BASE_END_POINT;
import static edu.nau.cs.meta.service.constants.Endpoint.FILES;

@Service
@RequiredArgsConstructor
public class RestFileServiceImpl implements RestFileService {

    private final FileServiceRestTemplate restTemplate;

    @Override
    public FileObjectDTO saveFileObject(@NonNull FileObjectDTO fileObjectDTO) {
        return restTemplate.postForEntity(BASE_END_POINT + FILES, fileObjectDTO, FileObjectDTO.class);
    }

}
