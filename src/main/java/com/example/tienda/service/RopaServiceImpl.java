package com.example.tienda.service;

import com.example.tienda.entity.producto.Ropa;
import com.example.tienda.repository.RopaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RopaServiceImpl implements RopaService{

    @Autowired
    private RopaRepository ropaRepository;

    @Override
    public List<Ropa> obtenerTodasLasRopas() {
        return ropaRepository.findAll();
    }

    @Override
    public Ropa guardarRopa(Ropa ropa) {
        return ropaRepository.save(ropa);
    }

    @Override
    public Ropa obtenerRopaPorId(Long id) {
        return ropaRepository.findById(id).orElse(null);
    }
}
