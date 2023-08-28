package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.controller.dto.AuthResponseDTO;
import com.example.block7crudvalidation.controller.dto.LoginRequestDTO;
import com.example.block7crudvalidation.controller.dto.PersonaDTO;

public interface AuthService {

    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);

    AuthResponseDTO register(PersonaDTO personaDTO);
}
