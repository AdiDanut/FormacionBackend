package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.controller.dto.PersonaDTO;
import com.example.block7crudvalidation.controller.dto.ProfesorDTO;
import com.example.block7crudvalidation.controller.feign.ProfesorFeignClient;
import com.example.block7crudvalidation.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ProfesorFeignClient profesorFeignClient;

    @PostMapping
    public ResponseEntity<PersonaDTO> addPersona(@RequestBody PersonaDTO personaDTO) {
        PersonaDTO addedPersona = personaService.addPersona(personaDTO);
        return ResponseEntity.ok(addedPersona);
    }

    @GetMapping
    public ResponseEntity<Page<PersonaDTO>> getAllPersonas(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")  Date fechaCreacion,
            @RequestParam int pageNumber,
            @RequestParam(defaultValue = "10") int size
            ) {
        Page<PersonaDTO> personas = personaService.getAllPersonasWithPagination(username, name, surname, fechaCreacion, pageNumber, size);
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable Long id) throws ClassNotFoundException {
        PersonaDTO personaDTO = personaService.getPersonaById(id);
        return ResponseEntity.ok(personaDTO);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaDTO> getPersonaByUsuario(@PathVariable String usuario) throws ClassNotFoundException {
        PersonaDTO personaDTO = personaService.getPersonaByUsuario(usuario);
        return ResponseEntity.ok(personaDTO);
    }

    @GetMapping("/profesores/{id}")
    public ProfesorDTO getProfesor(@PathVariable String id) {
        return profesorFeignClient.getProfesor(id);
    }

}

