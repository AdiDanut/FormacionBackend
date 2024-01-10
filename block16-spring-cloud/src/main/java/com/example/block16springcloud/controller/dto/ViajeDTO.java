package com.example.block16springcloud.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViajeDTO {

    private Long id;

    private String origen;

    private String destino;

    private LocalDate fechaSalida;

    private LocalDate fechaLlegada;

    private int pasajeros;

    private String estado;

    private List<Long> idClientes;
}
