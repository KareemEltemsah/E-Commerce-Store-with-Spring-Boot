package com.spring.ecommerce.api.controller.auth;

import com.spring.ecommerce.api.model.LoginBody;
import com.spring.ecommerce.api.model.LoginResponse;
import com.spring.ecommerce.api.model.RegistrationBody;
import com.spring.ecommerce.exception.UserAlreadyExistException;
import com.spring.ecommerce.model.LocalUser;
import com.spring.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody body) {
        try {
            userService.registerUser(body);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody body) {
        String jwt = userService.loginUser(body);
        if (jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        LoginResponse response = new LoginResponse();
        response.setJwt(jwt);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public LocalUser getUserData (@AuthenticationPrincipal LocalUser user) {
        return user;
    }
}
