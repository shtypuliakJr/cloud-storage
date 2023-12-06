package edu.nau.cs.meta.service.controller;

import edu.nau.cs.meta.service.dto.search.ChunkSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FileSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.FolderSearchResultDTO;
import edu.nau.cs.meta.service.dto.search.SearchResultObjectDTO;
import edu.nau.cs.meta.service.dto.search.WorkspaceResultDTO;
import edu.nau.cs.meta.service.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.nau.cs.meta.service.constants.Endpoint.BASE_ENDPOINT;
import static edu.nau.cs.meta.service.constants.Endpoint.CHUNKS;
import static edu.nau.cs.meta.service.constants.Endpoint.FILES;
import static edu.nau.cs.meta.service.constants.Endpoint.FOLDERS;
import static edu.nau.cs.meta.service.constants.Endpoint.SEARCH;
import static edu.nau.cs.meta.service.constants.Endpoint.WORKSPACE;
import static edu.nau.cs.meta.service.constants.TemporaryConstants.USER_ID;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_ENDPOINT + SEARCH)
public class SearchController {

    private final SearchService searchService;
    private final String userId = USER_ID;

    @GetMapping
    public ResponseEntity<List<SearchResultObjectDTO>> searchFileOrFolderByTemplate(@RequestParam String objectTemplate) {
        return ResponseEntity.ok(searchService.searchFileOrFolderByTemplate(objectTemplate, userId));
    }

    @GetMapping(WORKSPACE)
    public ResponseEntity<WorkspaceResultDTO> searchAll() {
        return ResponseEntity.ok(searchService.searchAll(userId));
    }

    @GetMapping(FILES)
    public ResponseEntity<List<FileSearchResultDTO>> searchFilesByExtension(@RequestParam String fileExtension) {
        return ResponseEntity.ok(searchService.searchByFileExtension(fileExtension, userId));
    }

    @GetMapping(CHUNKS + "/{chunkId}")
    public ResponseEntity<ChunkSearchResultDTO> searchChunkById(@PathVariable String chunkId) {
        return ResponseEntity.ok(searchService.searchChunkById(chunkId, userId));
    }

    @GetMapping(FILES + "/{fileId}")
    public ResponseEntity<FileSearchResultDTO> searchFileById(@PathVariable String fileId) {
        return ResponseEntity.ok(searchService.searchFileById(fileId, userId));
    }

    @GetMapping(FOLDERS + "/{folderId}")
    public ResponseEntity<FolderSearchResultDTO> searchFileOrFolderById(@PathVariable String folderId) {
        return ResponseEntity.ok(searchService.searchFolderById(folderId, userId));
    }

}
