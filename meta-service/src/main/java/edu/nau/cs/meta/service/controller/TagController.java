package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {

    public ResponseEntity<Void> getObjectTags() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> addObjectTags() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> editFolderTags() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteFolderTags() {
        return ResponseEntity.ok().build();
    }

}
