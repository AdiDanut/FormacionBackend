package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.controller.dto.AuthResponseDTO;
import com.example.block7crudvalidation.controller.dto.LoginRequestDTO;
import com.example.block7crudvalidation.controller.dto.PersonaDTO;
import com.example.block7crudvalidation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody PersonaDTO personaDTO){
        return ResponseEntity.ok(authService.register(personaDTO));
    }
}
