package com.example.block16springcloud.service;

import com.example.block16springcloud.controller.dto.ViajeDTO;
import com.example.block16springcloud.entity.ViajeEntity;

import java.util.List;


public interface ViajeService {

    ViajeDTO crearViaje(ViajeDTO viajeDTO);

    void borrarViaje(Long id);

    ViajeDTO actualizarViaje(Long id, ViajeDTO viajeDTO);

    ViajeDTO buscarViajePorId(Long id);

    List<ViajeDTO> obtenerTodosLosViajes();

    ViajeDTO anadirPasajeroAViaje(Long viajeId, Long pasajeroId);

    int contarPasajerosEnViaje(Long viajeId);

    ViajeDTO cambiarEstadoDeViaje(Long viajeId, String nuevoEstado);

    String verificarDisponibilidadDeViaje(Long viajeId);

}
