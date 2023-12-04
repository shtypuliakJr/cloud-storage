package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dao.SearchObjectResultDao;
import edu.nau.cs.meta.service.dto.search.SearchResultObjectDTO;
import edu.nau.cs.meta.service.exception.CsChunkDoesNotExistsException;
import edu.nau.cs.meta.service.repository.ChunkRepository;
import edu.nau.cs.meta.service.repository.FileObjectRepository;
import edu.nau.cs.meta.service.repository.FolderObjectRepository;
import edu.nau.cs.meta.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final ChunkRepository chunkRepository;
    private final FileObjectRepository fileObjectRepository;
    private final FolderObjectRepository folderObjectRepository;
    private final SearchObjectResultDao searchObjectResultDao;

    @Override
    public List<SearchResultObjectDTO> searchFileOrFolderByTemplate(String objectTemplate, String userId) {
        return searchObjectResultDao.searchObjectsByTemplate(objectTemplate, userId);
    }

    @Override
    public SearchResultObjectDTO searchChunkById(String chunkId, String userId) {
        return chunkRepository.findById(chunkId)
                .map(chunk -> new SearchResultObjectDTO())
                .orElseThrow(() -> new CsChunkDoesNotExistsException(chunkId));
    }

    @Override
    public SearchResultObjectDTO searchFileById(String fileId, String userId) {
        return null;
    }

    @Override
    public SearchResultObjectDTO searchFolderById(String folderId, String userId) {
        return null;
    }

}
