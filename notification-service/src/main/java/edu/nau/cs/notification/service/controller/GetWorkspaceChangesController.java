package edu.nau.cs.notification.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetWorkspaceChangesController implements GetWorkspaceChangesControllerApi {

    @Override
    public ResponseEntity<String> getWorkspaceChanges() {
        return ResponseEntity.ok("hello");
    }

}
