package com.iat.momentsqa.controller;

import com.iat.momentsqa.auth.AuthenticationRequest;
import com.iat.momentsqa.auth.AuthenticationResponse;
import com.iat.momentsqa.auth.RegisterRequest;
import com.iat.momentsqa.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    IUserService IUserService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(IUserService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(IUserService.authenticate(authenticationRequest));

    }
    @PostMapping("/login/oauth2/github")
    public ResponseEntity<AuthenticationResponse> loginUserGithub(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(IUserService.authenticate(authenticationRequest));

    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logoutUser(@RequestBody RegisterRequest request){
        IUserService.logout();
    }
}
