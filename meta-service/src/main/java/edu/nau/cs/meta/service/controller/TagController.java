package edu.nau.cs.meta.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.nau.cs.meta.service.constants.Endpoint.BASE_ENDPOINT;
import static edu.nau.cs.meta.service.constants.Endpoint.TAGS;

@RequiredArgsConstructor
@RestController
@RequestMapping(BASE_ENDPOINT + TAGS)
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
