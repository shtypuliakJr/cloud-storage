package edu.nau.cs.meta.service.controller;

import edu.nau.cs.meta.service.entity.User;
import edu.nau.cs.meta.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/cs-api/users/registration")
    public ResponseEntity<String> saveUser(@RequestParam String userId) {
        userRepository.save(User.builder()
                .id(userId)
                .name("user_name")
                .email("user_email")
                .password("password")
                .build());
        return ResponseEntity.ok(userId);
    }

}
