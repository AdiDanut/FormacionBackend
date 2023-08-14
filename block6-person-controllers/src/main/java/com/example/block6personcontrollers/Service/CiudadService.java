package com.example.block6personcontrollers.Service;

import com.example.block6personcontrollers.Model.Ciudad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {
    private List<Ciudad> ciudades = new ArrayList<>();

    public void agregarCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
    }

    public List<Ciudad> obtenerCiudades() {
        return ciudades;
    }
}

