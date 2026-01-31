package com.example.demo.controllers;

import com.example.demo.dto.ErrorMessage;
import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.Proveedor;
import com.example.demo.reglasNegocio.ValidarDatosContacto;
import com.example.demo.service.Interface.MaquinaEstadosService;
import com.example.demo.service.Interface.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final ValidarDatosContacto validarDatosContacto;

    public ProveedorController(ProveedorService proveedorService, ValidarDatosContacto validarDatosContacto){
        this.proveedorService = proveedorService;
        this.validarDatosContacto = validarDatosContacto;
    }

    @GetMapping("/v1/proveedores")
    public ResponseEntity<List<Proveedor>> obtenerProveedor(){
        List<Proveedor> response = proveedorService.obtenerProveedor();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/proveedor")
    public ResponseEntity<?> nuevoProveedor(@RequestBody Proveedor proveedor){
        if(!validarDatosContacto.esContactoValido(proveedor)){
            return ResponseEntity.badRequest().body(new ErrorMessage("Email o Correo no son validos"));
        }

        Proveedor response = proveedorService.nuevoProveedor(proveedor);

        if(response == null){
            ResponseEntity.internalServerError().body(new ErrorMessage("Servicio no disponible por el momento"));
        }

        return ResponseEntity.ok(response);
    }
}
