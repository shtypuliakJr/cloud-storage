package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.nau.cs.meta.service.constants.Endpoint.BASE_ENDPOINT;
import static edu.nau.cs.meta.service.constants.Endpoint.FILES;
import static edu.nau.cs.meta.service.constants.Endpoint.FOLDERS;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_ENDPOINT + FOLDERS)
public class FolderController {

    public ResponseEntity<Void> listFolder() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> getFolder() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> createFolder() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> editFolder() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteFolder() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> moveFolder() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> copyFolders() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> moveFolders() {
        return ResponseEntity.ok().build();
    }

}
