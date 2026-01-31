package com.example.demo.service.Interface;

import com.example.demo.entity.EstadoSolicitud;

import java.util.List;

public interface MaquinaEstadosService {
    public List<EstadoSolicitud> obtenerSolicitud();
    public EstadoSolicitud nuevaSolicitud(EstadoSolicitud solicitud);
    public EstadoSolicitud obtenerPorId(Long id);
    public EstadoSolicitud actualizarSolicitud(EstadoSolicitud solicitud);

}
