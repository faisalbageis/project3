package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.Model.User;
import org.example.project3.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/admin")
    public ResponseEntity<?> AdminRegister(@Valid @RequestBody User user) {
        authService.AdminRegister(user);
        return ResponseEntity.status(200).body("admin registered successfully");
    }
}
