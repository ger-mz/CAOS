package com.example.demo.service;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Producto;
import com.example.demo.repository.MaquinaEstadosRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.Interface.MaquinaEstadosService;
import com.example.demo.service.Interface.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }


    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto nuevoProducto(Producto producto) {
        try {
            return productoRepository.save(producto);

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return null;
        }
    }
}
