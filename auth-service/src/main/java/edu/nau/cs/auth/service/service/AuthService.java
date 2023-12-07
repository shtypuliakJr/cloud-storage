package edu.nau.cs.auth.service.service;

import edu.nau.cs.auth.service.model.AuthRequest;
import edu.nau.cs.auth.service.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    public AuthResponse register(AuthRequest authRequest) {
        String accessToken = jwtService.generate(authRequest.getEmail(), "ACCESS");
        String refreshToken = jwtService.generate(authRequest.getEmail(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

}