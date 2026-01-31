package com.example.demo.controllers;

import com.example.demo.dto.ErrorMessage;
import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Producto;
import com.example.demo.reglasNegocio.ValidarDatosContacto;
import com.example.demo.reglasNegocio.ValidarEstado;
import com.example.demo.reglasNegocio.ValidarPrecio;
import com.example.demo.service.Interface.MaquinaEstadosService;
import com.example.demo.service.Interface.ProductoService;
import com.example.demo.service.ProductoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductoController {

    private final ProductoService productoService;
    private final ValidarPrecio validarPrecio;

    public ProductoController(ProductoService productoService, ValidarPrecio validarPrecio){
        this.productoService = productoService;
        this.validarPrecio = validarPrecio;
    }

    @GetMapping("/v1/productos")
    public ResponseEntity<List<Producto>> obtenerSolicitud(){
        List<Producto> response = productoService.obtenerProductos();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/producto")
    public ResponseEntity<?> nuevaSolicitud(@RequestBody Producto producto){
        if(!validarPrecio.esPrecioValido(producto.getPrecio())){
            return ResponseEntity.badRequest().body(new ErrorMessage("Precio de producto invalido"));
        }

        Producto response = productoService.nuevoProducto(producto);

        if(response == null){
            ResponseEntity.internalServerError().body(new ErrorMessage("Servicio no disponible por el momento"));
        }

        return ResponseEntity.ok(response);
    }
}
