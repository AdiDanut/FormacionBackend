package com.example.block16springcloud.service;

import com.example.block16springcloud.controller.dto.ClienteDTO;
import com.example.block16springcloud.entity.ClienteEntity;

import java.util.List;

public interface ClienteService {

    ClienteDTO crearCliente(ClienteDTO clienteDTO);

    void borrarCliente(Long id);

    ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO);

    ClienteDTO buscarClientePorId(Long id);

    List<ClienteDTO> obtenerTodosLosClientes();
}

