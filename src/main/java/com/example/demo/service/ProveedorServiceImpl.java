package com.example.demo.service;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Proveedor;
import com.example.demo.repository.MaquinaEstadosRepository;
import com.example.demo.repository.ProveedorRepository;
import com.example.demo.service.Interface.MaquinaEstadosService;
import com.example.demo.service.Interface.ProveedorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }


    @Override
    public List<Proveedor> obtenerProveedor() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor nuevoProveedor(Proveedor proveedor) {
        try {
            return proveedorRepository.save(proveedor);

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return null;
        }
    }
}
