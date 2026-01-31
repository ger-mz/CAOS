package com.example.demo.reglasNegocio;

import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.enums.StatusStateEnum;
import org.springframework.stereotype.Component;

@Component
public class ValidarEstado {
    public boolean cumpleCreate(EstadoSolicitud solicitud) {
        return solicitud.getState() == StatusStateEnum.CREADO;
    }

    public boolean cumpleEN_ESPERA(EstadoSolicitud solicitud) {
        return solicitud.getState() == StatusStateEnum.CREADO;
    }
}
