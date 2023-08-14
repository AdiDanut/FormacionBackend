package com.example.block6personcontrollers.Controller;

import com.example.block6personcontrollers.Model.Ciudad;
import com.example.block6personcontrollers.Model.Persona;
import com.example.block6personcontrollers.Service.CiudadService;
import com.example.block6personcontrollers.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private final PersonaService personaService;
    private final CiudadService ciudadService;

    @Autowired
    @Qualifier("bean1")
    private Persona bean1;

    @Autowired
    @Qualifier("bean2")
    private Persona bean2;

    @Autowired
    @Qualifier("bean3")
    private Persona bean3;

    @Autowired
    public Controlador1(PersonaService personaService, CiudadService ciudadService) {
        this.personaService = personaService;
        this.ciudadService = ciudadService;
    }

    @GetMapping("/addPersona")
    public Persona agregarPersona(@RequestParam String nombre, @RequestParam String poblacion, @RequestParam int edad) {
        return personaService.crearPersona(nombre, poblacion, edad);
    }

    @PostMapping("/addCiudad")
    public void agregarCiudad(@RequestBody Ciudad ciudad) {
        ciudadService.agregarCiudad(ciudad);
    }

    @GetMapping("/bean/{bean}")
    public Persona obtenerBean(@PathVariable String bean) {
        switch (bean) {
            case "bean1":
                return bean1;
            case "bean2":
                return bean2;
            case "bean3":
                return bean3;
            default:
                throw new IllegalArgumentException("Bean no válido");
        }
    }
}