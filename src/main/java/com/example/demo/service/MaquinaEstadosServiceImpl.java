package com.example.demo.service;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.repository.MaquinaEstadosRepository;
import com.example.demo.service.Interface.MaquinaEstadosService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinaEstadosServiceImpl implements MaquinaEstadosService {

    private final MaquinaEstadosRepository maquinaEstadosRepository;

    public MaquinaEstadosServiceImpl(MaquinaEstadosRepository maquinaEstadosRepository){
        this.maquinaEstadosRepository = maquinaEstadosRepository;
    }

    @Override
    public List<EstadoSolicitud> obtenerSolicitud() {
        return maquinaEstadosRepository.findAll();
    }

    @Override
    public EstadoSolicitud nuevaSolicitud(EstadoSolicitud solicitud){
        try {
            return maquinaEstadosRepository.save(solicitud);

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return null;
        }
    }

    @Override
    public EstadoSolicitud obtenerPorId(Long id) {
        return maquinaEstadosRepository.getById(id);
    }

    @Override
    public EstadoSolicitud actualizarSolicitud(EstadoSolicitud solicitud){
        try {
            return maquinaEstadosRepository.save(solicitud);

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return null;
        }
    }
}
