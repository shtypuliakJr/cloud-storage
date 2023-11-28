package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.nau.cs.meta.service.constants.Endpoint.BASE_ENDPOINT;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_ENDPOINT)
public class MetadataController {

    public ResponseEntity<Void> getChunkMetadata() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> getFileMetadata() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> getFolderMetadata() {
        return ResponseEntity.ok().build();
    }

}
