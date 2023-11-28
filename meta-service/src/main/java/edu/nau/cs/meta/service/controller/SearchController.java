package edu.nau.cs.meta.service.controller;

import edu.nau.cs.meta.service.dto.SearchResultObjectDTO;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_ENDPOINT + SEARCH)
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<List<SearchResultObjectDTO>> searchFileOrFolderByTemplate(@RequestParam String objectTemplate) {
        return ResponseEntity.ok(searchService.searchFileOrFolderByTemplate(objectTemplate));
    }

    @GetMapping(CHUNKS + "/{chunkId}")
    public ResponseEntity<SearchResultObjectDTO> searchChunkById(@PathVariable String chunkId) {
        return ResponseEntity.ok(searchService.searchChunkById(chunkId));
    }

    @GetMapping(FILES + "/{fileId}")
    public ResponseEntity<SearchResultObjectDTO> searchFileById(@PathVariable String fileId) {
        return ResponseEntity.ok(searchService.searchFileById(fileId));
    }

    @GetMapping(FOLDERS + "/folderId")
    public ResponseEntity<SearchResultObjectDTO> searchFileOrFolderById(@PathVariable String folderId) {
        return ResponseEntity.ok(searchService.searchFolderById(folderId));
    }

}
