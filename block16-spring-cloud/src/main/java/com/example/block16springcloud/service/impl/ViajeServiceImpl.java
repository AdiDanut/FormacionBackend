package com.example.block16springcloud.service.impl;

import com.example.block16springcloud.controller.ClienteController;
import com.example.block16springcloud.controller.dto.ViajeDTO;
import com.example.block16springcloud.entity.ClienteEntity;
import com.example.block16springcloud.entity.ClienteViajeEntity;
import com.example.block16springcloud.entity.ViajeEntity;
import com.example.block16springcloud.repository.ClienteRepository;
import com.example.block16springcloud.repository.ViajeRepository;
import com.example.block16springcloud.service.ViajeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViajeServiceImpl implements ViajeService {

    private final ViajeRepository viajeRepository;

    private final ModelMapper modelMapper;

    private final ClienteRepository clienteRepository;

    @Autowired
    public ViajeServiceImpl(ViajeRepository viajeRepository, ModelMapper modelMapper, ClienteRepository clienteRepository) {
        this.viajeRepository = viajeRepository;
        this.modelMapper = modelMapper;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ViajeDTO crearViaje(ViajeDTO viajeDTO) {
        ViajeEntity viaje = convertirDtoAEntity(viajeDTO);
        ViajeEntity saved = viajeRepository.save(viaje);
        return convertirEntityADto(saved);
    }

    @Override
    public void borrarViaje(Long id) {
        viajeRepository.deleteById(id);
    }

    @Override
    public ViajeDTO actualizarViaje(Long id, ViajeDTO viajeDTO) {
        ViajeEntity viaje = convertirDtoAEntity(viajeDTO);
        viaje.setId(id);
        ViajeEntity updated = viajeRepository.save(viaje);
        return convertirEntityADto(updated);
    }

    @Override
    public ViajeDTO buscarViajePorId(Long id) {
        Optional<ViajeEntity> optional = viajeRepository.findById(id);
        if(optional.isPresent()) {
            return convertirEntityADto(optional.get());
        }
        throw new IllegalArgumentException("Viaje con id " + id + " no existe");
    }

    @Override
    public List<ViajeDTO> obtenerTodosLosViajes() {
        List<ViajeEntity> viajes = viajeRepository.findAll();
        return viajes.stream()
                .map(this::convertirEntityADto)
                .collect(Collectors.toList());
    }

    @Override
    public ViajeDTO anadirPasajeroAViaje(Long viajeId, Long pasajeroId) {
        ViajeEntity viaje = viajeRepository.findById(viajeId)
                .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));

        ClienteEntity cliente = clienteRepository.findById(pasajeroId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        if (viaje.getPasajeros() >= 40) {
            throw new IllegalArgumentException("No caben más pasajeros");
        }

        ClienteViajeEntity clienteViaje = new ClienteViajeEntity();
        clienteViaje.setCliente(cliente);
        clienteViaje.setViaje(viaje);

        cliente.getClienteViajes().add(clienteViaje);
        viaje.getClienteViajes().add(clienteViaje);

        viaje.setPasajeros(viaje.getPasajeros() + 1);
        ViajeEntity updated = viajeRepository.save(viaje);
        return convertirEntityADto(updated);
    }


    @Override
    public int contarPasajerosEnViaje(Long viajeId) {
        ViajeEntity viaje = viajeRepository.findById(viajeId).orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));
        return viaje.getPasajeros();
    }

    @Override
    public ViajeDTO cambiarEstadoDeViaje(Long viajeId, String nuevoEstado) {
        ViajeEntity viaje = viajeRepository.findById(viajeId).orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));
        viaje.setStatus(nuevoEstado);
        ViajeEntity updated = viajeRepository.save(viaje);
        return convertirEntityADto(updated);
    }

    @Override
    public String verificarDisponibilidadDeViaje(Long viajeId) {
        ViajeEntity viaje = viajeRepository.findById(viajeId).orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));
        return (viaje.getPasajeros() < 40) ? "Disponible" : "Lleno";
    }

    private ViajeDTO convertirEntityADto(ViajeEntity viaje) {
        return modelMapper.map(viaje, ViajeDTO.class);
    }

    private ViajeEntity convertirDtoAEntity(ViajeDTO dto) {
        return modelMapper.map(dto, ViajeEntity.class);
    }


}