package com.iat.momentsqa.service.impl;

import com.iat.momentsqa.auth.AuthenticationRequest;
import com.iat.momentsqa.auth.AuthenticationResponse;
import com.iat.momentsqa.auth.RegisterRequest;
//import com.iat.momentsqa.config.JwtService;
import com.iat.momentsqa.config.JwtService;
import com.iat.momentsqa.model.User;
import com.iat.momentsqa.repository.UserRepository;
import com.iat.momentsqa.service.IUserService;
import com.iat.momentsqa.utilities.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IUserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        Optional<String> jwtToken = Optional.ofNullable(jwtService.generateToken(user));
        return AuthenticationResponse.builder().build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user.orElseThrow());
        return AuthenticationResponse.builder().token(jwtToken).build();
//        return AuthenticationResponse.builder().build();
    }

    @Override
    public void logout() {

    }
}
