package by.bsu.authservice.endpoint;


import by.bsu.authservice.exception.UsernameAlreadyExistsException;
import by.bsu.authservice.model.Profile;
import by.bsu.authservice.model.Role;
import by.bsu.authservice.model.User;
import by.bsu.authservice.payload.ApiResponse;
import by.bsu.authservice.payload.FacebookLoginRequest;
import by.bsu.authservice.payload.JwtAuthenticationResponse;
import by.bsu.authservice.payload.LoginRequest;
import by.bsu.authservice.payload.SignUpRequest;
import by.bsu.authservice.service.AuthService;
import by.bsu.authservice.service.FacebookService;
import by.bsu.authservice.exception.BadRequestException;
import by.bsu.authservice.exception.EmailAlreadyExistsException;
import com.clone.instagram.authservice.payload.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
public class AuthEndpoint {

    @Autowired private AuthService authService;
    @Autowired private FacebookService facebookService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping("/facebook/signin")
    public  ResponseEntity<?> facebookAuth(@Valid @RequestBody FacebookLoginRequest facebookLoginRequest) {
        log.info("facebook login {}", facebookLoginRequest);
        String token = facebookService.loginUser(facebookLoginRequest.getAccessToken());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload) {
        log.info("creating user {}", payload.getUsername());

        User user = User
                .builder()
                .username(payload.getUsername())
                .email(payload.getEmail())
                .password(payload.getPassword())
                .userProfile(Profile
                        .builder()
                        .displayName(payload.getName())
                        .profilePictureUrl(payload.getProfilePicUrl())
                        .build())
                .build();

        try {
            authService.registerUser(user, Role.USER);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            throw new BadRequestException(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse(true,"User registered successfully"));
    }
}
