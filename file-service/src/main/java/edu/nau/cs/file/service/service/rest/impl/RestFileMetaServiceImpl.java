package edu.nau.cs.file.service.service.rest.impl;

import edu.nau.cs.file.service.dto.transfer.FileObjectDTO;
import edu.nau.cs.file.service.service.rest.RestFileMetaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static edu.nau.cs.file.service.constants.Endpoint.BASE_ENDPOINT;
import static edu.nau.cs.file.service.constants.Endpoint.DASH;
import static edu.nau.cs.file.service.constants.Endpoint.FILES;

@RequiredArgsConstructor
@Service
public class RestFileMetaServiceImpl implements RestFileMetaService {

    private final MetaServiceRestTemplate restTemplate;

    @Override
    public FileObjectDTO getFileObject(@NonNull String fileId) {
        return restTemplate.getForEntity(BASE_ENDPOINT + FILES + DASH + fileId, FileObjectDTO.class);
    }

    @Override
    public FileObjectDTO saveFileObject(@NonNull FileObjectDTO fileObjectDTO) {
        return restTemplate.postForEntity(BASE_ENDPOINT + FILES, fileObjectDTO, FileObjectDTO.class);
    }

    @Override
    public void deleteFileObject(@NonNull String fileId) {
        restTemplate.deleteForEntity(BASE_ENDPOINT + FILES + DASH + fileId);
    }

    @Override
    public void deleteFileObjects(@NonNull List<String> fileIds) {
        restTemplate.deleteForEntities(BASE_ENDPOINT + FILES, Map.of("fileIds", fileIds));
    }

}
