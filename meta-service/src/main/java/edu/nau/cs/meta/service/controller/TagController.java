package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {

    public ResponseEntity<Void> getObjectTag() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> addObjectTag() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> editObjectTag() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteObjectTags() {
        return ResponseEntity.ok().build();
    }

}
