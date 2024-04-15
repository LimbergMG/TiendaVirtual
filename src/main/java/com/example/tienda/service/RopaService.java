package com.example.tienda.service;

import com.example.tienda.entity.producto.Ropa;

import java.util.List;

public interface RopaService {
    List<Ropa> obtenerTodasLasRopas();
    Ropa guardarRopa(Ropa ropa);
    Ropa obtenerRopaPorId(Long id);
}
