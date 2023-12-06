package edu.nau.cs.meta.service.service;

import edu.nau.cs.meta.service.dto.search.ChunkSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FileSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FolderSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.SearchResultObjectDTO;
import edu.nau.cs.meta.service.dto.search.WorkspaceResultDTO;

import java.util.List;

public interface SearchService {

    List<SearchResultObjectDTO> searchFileOrFolderByTemplate(String objectTemplate, String userId);

    List<FileSearchResultDTO> searchByFileExtension(String extension, String userId);

    ChunkSearchResultDTO searchChunkById(String chunkId, String userId);

    FileSearchResultDTO searchFileById(String fileId, String userId);

    FolderSearchResultDTO searchFolderById(String folderId, String userId);

    WorkspaceResultDTO searchAll(String userId);

}
