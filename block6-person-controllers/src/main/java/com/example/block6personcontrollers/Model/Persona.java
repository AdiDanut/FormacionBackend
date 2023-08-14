package com.example.block6personcontrollers.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private String nombre;
    private String poblacion;
    private int edad;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
}

