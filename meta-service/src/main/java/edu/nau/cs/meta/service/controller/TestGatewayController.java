package edu.nau.cs.meta.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@RestController
public class TestGatewayController {

    @GetMapping("/cs-api/hello-world")
    public ResponseEntity<String> getHelloWorld() {
        String userId = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest)
                .map(httpServletRequest -> httpServletRequest.getHeader("userId"))
                .orElse(null);
        return ResponseEntity.ok("hello world from meta-service + " + userId);
    }

}
