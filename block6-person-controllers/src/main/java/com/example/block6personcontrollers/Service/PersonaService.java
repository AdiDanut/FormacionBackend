package com.example.block6personcontrollers.Service;

import com.example.block6personcontrollers.Model.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    public Persona crearPersona(String nombre, String poblacion, int edad) {
        return new Persona(nombre, poblacion, edad);
    }
}
