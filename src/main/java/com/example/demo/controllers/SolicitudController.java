package com.example.demo.controllers;

import com.example.demo.dto.ErrorMessage;
import com.example.demo.dto.MessageResponse;
import com.example.demo.entity.EstadoSolicitud;
import com.example.demo.entity.enums.StatusStateEnum;
import com.example.demo.reglasNegocio.ValidarEstado;
import com.example.demo.reglasNegocio.ValidarFecha;
import com.example.demo.service.Interface.MaquinaEstadosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RestController
public class SolicitudController {

    private final MaquinaEstadosService maquinaEstadosService;
    private final ValidarEstado validarEstado;
    private final ValidarFecha validarFecha;

    public SolicitudController(
            MaquinaEstadosService maquinaEstadosService,
            ValidarEstado validarEstado,
            ValidarFecha validarFecha) {
        this.maquinaEstadosService = maquinaEstadosService;
        this.validarEstado = validarEstado;
        this.validarFecha = validarFecha;
    }

    @GetMapping("/v1/solicitudes")
    public ResponseEntity<List<EstadoSolicitud>> obtenerSolicitud() {
        List<EstadoSolicitud> response = maquinaEstadosService.obtenerSolicitud();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/v1/solicitud")
    public ResponseEntity<EstadoSolicitud> nuevaSolicitud(@RequestBody EstadoSolicitud estadoSolicitud) {
        estadoSolicitud.setFechaSolicitud(new Date());
        EstadoSolicitud response = maquinaEstadosService.nuevaSolicitud(estadoSolicitud);

        if (response == null) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/v1/solicitud")
    public ResponseEntity<?> actualizarSolicitud(@RequestBody EstadoSolicitud estadoSolicitud) {
        if (estadoSolicitud.getId() == null) {
            return ResponseEntity.badRequest()
                    .body(new ErrorMessage("Se requiere id de solicitud para actualizar la solicitud"));
        }

        EstadoSolicitud response = maquinaEstadosService.actualizarSolicitud(estadoSolicitud);

        if (response == null) {
            return ResponseEntity.internalServerError().body(new ErrorMessage("Servicio no disponible"));
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/v1/solicitudes/avanzar-solicitud")
    public ResponseEntity<?> validarEstadoActual(@RequestParam Long id) {
        EstadoSolicitud solicitud = maquinaEstadosService.obtenerPorId(id);

        if (solicitud.getState() == StatusStateEnum.CREADO) {
            Date date = solicitud.getFechaSolicitud();
            solicitud.setFechaEntregaEstimada(Date.from(date.toInstant().plus(3, ChronoUnit.DAYS)));

            solicitud.setState(StatusStateEnum.EN_ESPERA);
        } else if (solicitud.getState() == StatusStateEnum.EN_ESPERA) {
            if (solicitud.getPaqueteRecibido() == false && !validarFecha.esfechaEntregaValida(solicitud, new Date())) {
                solicitud.setState(StatusStateEnum.CANCELACION);
            }

            if (solicitud.getPaqueteRecibido() == true) {
                solicitud.setState(StatusStateEnum.REVISION);
            }

        } else if (solicitud.getState() == StatusStateEnum.REVISION) {
            if (solicitud.getPaqueteEnBuenEstado() == null) {
                return ResponseEntity.ok(new MessageResponse("En espera de revision de estado fisico del paquete"));
            }
            if (solicitud.getPaqueteEnBuenEstado() == true) {
                solicitud.setState(StatusStateEnum.PAGO);
            }

            if (solicitud.getPaqueteEnBuenEstado() == false) {
                solicitud.setState(StatusStateEnum.DEVOLUCION);
            }

        } else if (solicitud.getState() == StatusStateEnum.PAGO) {
            if (solicitud.getPagado() == null) {
                return ResponseEntity.ok(new MessageResponse("En espera de confirmacion de pago"));
            }

            if (solicitud.getPagado() == true) {
                solicitud.setState(StatusStateEnum.FINALIZADO);
            }

            if (solicitud.getPagado() == false) {
                solicitud.setState(StatusStateEnum.CANCELACION);
            }
        } else if (solicitud.getState() == StatusStateEnum.FINALIZADO) {
            return ResponseEntity.ok(new MessageResponse("Se a finalizado el proceso"));

        } else if (solicitud.getState() == StatusStateEnum.CANCELACION) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Se cancelo la solicitud"));

        } else if (solicitud.getState() == StatusStateEnum.DEVOLUCION) {
            return ResponseEntity.badRequest()
                    .body(new ErrorMessage("Paquete recibido en mal estado, se realiza devolucion"));
        }

        EstadoSolicitud response = maquinaEstadosService.actualizarSolicitud(solicitud);

        if (response == null) {
            return ResponseEntity.internalServerError().body(new ErrorMessage("Servicio no disponible"));
        }

        return ResponseEntity.ok(response);
    }
}
