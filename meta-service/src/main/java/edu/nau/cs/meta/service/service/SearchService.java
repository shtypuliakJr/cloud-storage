package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.search.SearchResultObjectDTO;

import java.util.List;

public interface SearchService {

    List<SearchResultObjectDTO> searchFileOrFolderByTemplate(String objectTemplate, String userId);

    SearchResultObjectDTO searchChunkById(String chunkId, String userId);

    SearchResultObjectDTO searchFileById(String fileId, String userId);

    SearchResultObjectDTO searchFolderById(String folderId, String userId);

}
