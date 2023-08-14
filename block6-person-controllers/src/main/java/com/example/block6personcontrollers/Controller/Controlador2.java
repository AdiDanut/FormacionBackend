package com.example.block6personcontrollers.Controller;

import com.example.block6personcontrollers.Model.Ciudad;
import com.example.block6personcontrollers.Model.Persona;
import com.example.block6personcontrollers.Service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    private final CiudadService ciudadService;

    @Autowired
    public Controlador2(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping("/getCiudades")
    public List<Ciudad> obtenerCiudades() {
        return ciudadService.obtenerCiudades();
    }
    @GetMapping("/getPersona")
    public Persona obtenerPersonaMultiplicada(@RequestBody Persona persona) {
        persona.setEdad(persona.getEdad() * 2);
        return persona;
    }
}
