package com.example.block16springcloud.service.impl;

import com.example.block16springcloud.controller.dto.ClienteDTO;
import com.example.block16springcloud.entity.ClienteEntity;
import com.example.block16springcloud.repository.ClienteRepository;
import com.example.block16springcloud.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        ClienteEntity cliente = convertirDtoAEntity(clienteDTO);
        ClienteEntity saved = clienteRepository.save(cliente);
        return convertirEntityADto(saved);
    }

    @Override
    public void borrarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        ClienteEntity cliente = convertirDtoAEntity(clienteDTO);
        cliente.setId(id);
        ClienteEntity updated = clienteRepository.save(cliente);
        return convertirEntityADto(updated);
    }

    @Override
    public ClienteDTO buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertirEntityADto)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }

    @Override
    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<ClienteEntity> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertirEntityADto)
                .collect(Collectors.toList());
    }

    private ClienteDTO convertirEntityADto(ClienteEntity entity) {
        return modelMapper.map(entity, ClienteDTO.class);
    }

    private ClienteEntity convertirDtoAEntity(ClienteDTO dto) {
        return modelMapper.map(dto, ClienteEntity.class);
    }

}

