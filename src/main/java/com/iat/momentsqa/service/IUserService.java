package com.iat.momentsqa.service;

import com.iat.momentsqa.auth.AuthenticationRequest;
import com.iat.momentsqa.auth.AuthenticationResponse;
import com.iat.momentsqa.auth.RegisterRequest;

public interface IUserService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
    void logout();
}
