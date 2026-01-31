package com.example.demo.repository;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // save, findById, findAll, deleteById, count
}
