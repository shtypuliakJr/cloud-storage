package edu.nau.cs.auth.service.services;

import edu.nau.cs.auth.service.dto.CredentialsDto;
import edu.nau.cs.auth.service.dto.UserDto;
import edu.nau.cs.auth.service.exceptions.AppException;
import edu.nau.cs.auth.service.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public UserDto signIn(CredentialsDto credentialsDto) {
        var user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user, createToken(user));
        }

        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<BookstoreUser> userOptional = userRepository.findByLogin(login);

        if (userOptional.isEmpty()) {
            throw new AppException("User not found", HttpStatus.NOT_FOUND);
        }

        BookstoreUser user = userOptional.get();
        return userMapper.toUserDto(user, createToken(user));
    }

    private String createToken(BookstoreUser user) {
        Claims claims = Jwts.claims().setSubject(user.getLogin());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public UserDto signUp(UserCreationDto userCreationDto) {
        var userOptional = userRepository.findByLogin(userCreationDto.getLogin());
        if (userOptional.isPresent()) {
            throw new AppException("User already in database", HttpStatus.BAD_REQUEST);
        }

        var user = userMapper.toBookstoreUser(
                userCreationDto, passwordEncoder.encode(CharBuffer.wrap(userCreationDto.getPassword())));
        userRepository.save(user);

        return UserDto.builder()
                .login(user.getLogin())
                .build();
    }
}
