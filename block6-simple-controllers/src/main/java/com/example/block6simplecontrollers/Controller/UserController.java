package com.example.block6simplecontrollers.Controller;

import com.example.block6simplecontrollers.Model.Persona;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user/{nombre}")
    public String saludar(@PathVariable String nombre) {
        return "Hola " + nombre;
    }

    @PostMapping("/useradd")
    public Persona agregarPersona(@RequestBody Persona persona) {
        int nuevaEdad = persona.getEdad() + 1;
        persona.setEdad(nuevaEdad);
        return persona;
    }
}

