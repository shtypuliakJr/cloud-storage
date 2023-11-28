package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.SearchResultObjectDTO;

import java.util.List;

public interface SearchService {

    List<SearchResultObjectDTO> searchFileOrFolderByTemplate(String objectTemplate);

    SearchResultObjectDTO searchChunkById(String chunkId);

    SearchResultObjectDTO searchFileById(String fileId);

    SearchResultObjectDTO searchFolderById(String folderId);

}
