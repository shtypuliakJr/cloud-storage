package edu.nau.cs.file.service.service.rest.impl;

import edu.nau.cs.file.service.dto.transfer.FileChunkDTO;
import edu.nau.cs.file.service.service.rest.RestChunkMetaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static edu.nau.cs.file.service.constants.Endpoint.BASE_ENDPOINT;
import static edu.nau.cs.file.service.constants.Endpoint.CHUNKS;
import static edu.nau.cs.file.service.constants.Endpoint.DASH;

@RequiredArgsConstructor
@Service
public class RestChunkMetaServiceImpl implements RestChunkMetaService {

    private final MetaServiceRestTemplate restTemplate;

    @Override
    public FileChunkDTO getChunkObject(@NonNull String chunkId) {
        return restTemplate.getForEntity(BASE_ENDPOINT + CHUNKS + DASH + chunkId, FileChunkDTO.class);
    }

    @Override
    public FileChunkDTO saveChunkObject(@NonNull FileChunkDTO fileObjectDTO) {
        return restTemplate.postForEntity(BASE_ENDPOINT + CHUNKS, fileObjectDTO, FileChunkDTO.class);
    }

    @Override
    public void deleteChunkObject(@NonNull String chunkId) {
        restTemplate.deleteForEntity(BASE_ENDPOINT + CHUNKS + DASH + chunkId);
    }

    @Override
    public void deleteChunkObjects(@NonNull List<String> chunkIds) {
        restTemplate.deleteForEntities(BASE_ENDPOINT + CHUNKS, Map.of("chunkIds", String.join(",", chunkIds)));
    }

}
