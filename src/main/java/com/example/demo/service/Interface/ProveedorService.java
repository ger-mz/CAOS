package com.example.demo.service.Interface;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Proveedor;

import java.util.List;

public interface ProveedorService {
    public List<Proveedor> obtenerProveedor();
    public Proveedor nuevoProveedor(Proveedor proveedor);
}
