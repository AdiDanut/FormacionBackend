package com.example.block16springcloud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origen;

    private String destino;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private int pasajeros;

    private String status;

    @OneToMany(mappedBy = "viaje")
    private Set<ClienteViajeEntity> clienteViajes;

}

