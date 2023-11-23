package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ActionController {

    public ResponseEntity<Void> copyFile() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> copyFiles() {
        return ResponseEntity.ok().build();
    }

}
