package com.example.block16springcloud.controller;


import com.example.block16springcloud.controller.dto.ViajeDTO;
import com.example.block16springcloud.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    @Autowired
    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @PostMapping
    public ResponseEntity<ViajeDTO> crearViaje(@RequestBody ViajeDTO viajeDTO) {
        ViajeDTO creado = viajeService.crearViaje(viajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarViaje(@PathVariable Long id) {
        viajeService.borrarViaje(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViajeDTO> actualizarViaje(@PathVariable Long id, @RequestBody ViajeDTO viajeDTO) {
        ViajeDTO actualizado = viajeService.actualizarViaje(id, viajeDTO);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViajeDTO> buscarViajePorId(@PathVariable Long id) {
        ViajeDTO viaje = viajeService.buscarViajePorId(id);
        return ResponseEntity.ok(viaje);
    }

    @GetMapping
    public ResponseEntity<List<ViajeDTO>> obtenerTodosLosViajes() {
        List<ViajeDTO> viajes = viajeService.obtenerTodosLosViajes();
        return ResponseEntity.ok(viajes);
    }

    @PostMapping("/{viajeId}/pasajeros/{pasajeroId}")
    public ResponseEntity<ViajeDTO> anadirPasajeroAViaje(@PathVariable Long viajeId, @PathVariable Long pasajeroId) {
        ViajeDTO viaje = viajeService.anadirPasajeroAViaje(viajeId, pasajeroId);
        return ResponseEntity.ok(viaje);
    }

    @GetMapping("/{viajeId}/pasajeros")
    public ResponseEntity<Integer> contarPasajerosEnViaje(@PathVariable Long viajeId) {
        int cantidadPasajeros = viajeService.contarPasajerosEnViaje(viajeId);
        return ResponseEntity.ok(cantidadPasajeros);
    }

    @PostMapping("/{viajeId}/estado")
    public ResponseEntity<ViajeDTO> cambiarEstadoDeViaje(@PathVariable Long viajeId, @RequestBody String nuevoEstado) {
        ViajeDTO viaje = viajeService.cambiarEstadoDeViaje(viajeId, nuevoEstado);
        return ResponseEntity.ok(viaje);
    }

    @GetMapping("/{viajeId}/disponibilidad")
    public ResponseEntity<String> verificarDisponibilidadDeViaje(@PathVariable Long viajeId) {
        String disponibilidad = viajeService.verificarDisponibilidadDeViaje(viajeId);
        return ResponseEntity.ok(disponibilidad);
    }
}

