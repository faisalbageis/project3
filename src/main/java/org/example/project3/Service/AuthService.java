package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.Model.User;
import org.example.project3.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void AdminRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(hash);
        user.setRole("ADMIN");

        authRepository.save(user);
    }
}
