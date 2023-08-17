package com.example.block7crudvalidation.Controller;

import com.example.block7crudvalidation.Controller.DTO.PersonaDTO;
import com.example.block7crudvalidation.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<PersonaDTO> addPersona(@RequestBody PersonaDTO personaDTO) throws Exception {
        PersonaDTO addedPersona = personaService.addPersona(personaDTO);
        return ResponseEntity.ok(addedPersona);
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAllPersonas() {
        List<PersonaDTO> personas = personaService.getAllPersonas();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable Long id) throws Exception {
        PersonaDTO personaDTO = personaService.getPersonaById(id);
        return ResponseEntity.ok(personaDTO);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<PersonaDTO> getPersonaByUsuario(@PathVariable String usuario) throws Exception {
        PersonaDTO personaDTO = personaService.getPersonaByUsuario(usuario);
        return ResponseEntity.ok(personaDTO);
    }

}

