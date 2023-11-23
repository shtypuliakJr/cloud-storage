package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MetadataController {

    public ResponseEntity<Void> getFileMetadata() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> getFileChunkMetadata() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> getFolderMetadata() {
        return ResponseEntity.ok().build();
    }

}
