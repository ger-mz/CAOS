package com.example.demo.service.Interface;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Producto;

import java.util.List;

public interface ProductoService {
    public List<Producto> obtenerProductos();
    public Producto nuevoProducto(Producto producto);
}
