package com.example.block16springcloud.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private int edad;

    private String email;

    private String telefono;

    private List<Long> idViajes;
}

