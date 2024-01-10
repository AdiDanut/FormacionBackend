package com.example.block16springcloud.entity;


import com.example.block16springcloud.controller.dto.ClienteViajeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteViajeEntity {

    @EmbeddedId
    private ClienteViajeId id;

    @ManyToOne
    @MapsId("clienteId")
    private ClienteEntity cliente;

    @ManyToOne
    @MapsId("viajeId")
    private ViajeEntity viaje;
}

