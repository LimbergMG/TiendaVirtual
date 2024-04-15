package com.example.tienda.repository;

import com.example.tienda.entity.producto.Ropa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RopaRepository extends JpaRepository<Ropa, Long> {
}
