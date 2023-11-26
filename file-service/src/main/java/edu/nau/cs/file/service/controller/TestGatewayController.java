package edu.nau.cs.file.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestGatewayController implements TestGatewayControllerApi{

    @Override
    public ResponseEntity<String> getHelloWorld() {
        return ResponseEntity.ok("hello world from file-service");
    }

}
