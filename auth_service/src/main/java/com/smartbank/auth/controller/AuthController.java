package com.smartbank.auth.controller;
import com.smartbank.auth.model.User;
import com.smartbank.auth.repository.UserRepository;
import com.smartbank.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if(userRepository.existsByEmail(user.getEmail())) return "Email already exists";
        userRepository.save(user);
        return "Registered Successfully";
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser.isPresent() && dbUser.get().getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            return Map.of("token", token, "email", dbUser.get().getEmail());
        }
        return "Invalid Credentials";
    }
}