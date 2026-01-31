package com.example.demo.reglasNegocio;

import com.example.demo.entity.EstadoSolicitud;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ValidarFecha {
    public boolean esfechaEntregaValida(EstadoSolicitud solicitud, Date ahora) {
        return !ahora.after(solicitud.getFechaEntregaEstimada());
    }
}
