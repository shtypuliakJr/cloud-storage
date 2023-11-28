package edu.nau.cs.meta.service.service.impl;

import edu.nau.cs.meta.service.dto.SearchResultObjectDTO;
import edu.nau.cs.meta.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public List<SearchResultObjectDTO> searchFileOrFolderByTemplate(String objectTemplate) {
        return null;
    }

    @Override
    public SearchResultObjectDTO searchChunkById(String chunkId) {
        return null;
    }

    @Override
    public SearchResultObjectDTO searchFileById(String fileId) {
        return null;
    }

    @Override
    public SearchResultObjectDTO searchFolderById(String folderId) {
        return null;
    }

}
