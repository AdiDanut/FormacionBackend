package com.example.block7crudvalidation.service.impl;

import com.example.block7crudvalidation.controller.dto.AuthResponseDTO;
import com.example.block7crudvalidation.controller.dto.LoginRequestDTO;
import com.example.block7crudvalidation.controller.dto.PersonaDTO;
import com.example.block7crudvalidation.entity.PersonaEntity;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.service.AuthService;
import com.example.block7crudvalidation.service.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                ));
        UserDetails userDetails = personaRepository.findByUsuario(loginRequestDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username no encontrado"));
        String token = jwtService.getToken(userDetails);
        return AuthResponseDTO.builder()
                .token(token)
                .build();
    }


    @Override
    public AuthResponseDTO register(PersonaDTO personaDTO) {
        personaDTO.setPassword(passwordEncoder.encode(personaDTO.getPassword()));
        PersonaDTO persona = personaService.addPersona(personaDTO);

        PersonaEntity personaEntity = modelMapper.map(persona, PersonaEntity.class);

        return AuthResponseDTO.builder()
                .token(jwtService.getToken(personaEntity))
                .build();
    }
}
