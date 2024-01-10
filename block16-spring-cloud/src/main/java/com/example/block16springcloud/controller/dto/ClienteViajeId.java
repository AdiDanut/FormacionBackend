package com.example.block16springcloud.controller.dto;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteViajeId implements Serializable {

    private Long clienteId;

    private Long viajeId;
}

