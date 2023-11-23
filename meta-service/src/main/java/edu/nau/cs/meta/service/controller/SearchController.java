package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchController {

    public ResponseEntity<Void> searchFileOrFolderByTemplate() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> searchFileOrFolderById() {
        return ResponseEntity.ok().build();
    }

}
