package edu.nau.cs.meta.service.controller;

import edu.nau.cs.meta.service.dto.UserDTO;
import edu.nau.cs.meta.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/cs-api/users/registration")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/cs-api/users/{userId}")
    public ResponseEntity<UserDTO> getUserData(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

}
