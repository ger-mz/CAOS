package com.example.demo.entity.enums;

public enum StatusStateEnum {
    CREADO,          // nuevo inicio de solicitud
    EN_ESPERA,      // En proceso de espera de recibir los paquetes solicitados
    REVISION,       // Revisar pquetes recibidos para validar que sea lo solicitado
    PAGO,           // En proceso de realizar el pago
    FINALIZADO,     // Se finaliza el proceso y se activa la generacion de factura
    DEVOLUCION,     // Los paquetes recibidos se devolveran por incumplimiento
    CANCELACION     // Se cancela la solicitud de un paquete
}
