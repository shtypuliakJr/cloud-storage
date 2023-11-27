package edu.nau.cs.file.service.service.rest.impl;

import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.service.rest.RestMetaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static edu.nau.cs.file.service.constants.Endpoint.BASE_END_POINT;
import static edu.nau.cs.file.service.constants.Endpoint.FILES;

@Service
@RequiredArgsConstructor
public class RestMetaServiceImpl implements RestMetaService {

    private final MetaServiceRestTemplate restTemplate;

    @Override
    public FileObjectDTO saveFileObject(@NonNull FileObjectDTO fileObjectDTO) {
        return restTemplate.postForEntity(BASE_END_POINT + FILES, fileObjectDTO, FileObjectDTO.class);
    }

}
