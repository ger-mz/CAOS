package com.example.demo.entity;

import com.example.demo.entity.enums.StatusStateEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class EstadoSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusStateEnum state;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonIgnoreProperties({"catalogo"})
    private Producto productoSolicitado;

    @Column(name = "fecha_solicitud")
    Date fechaSolicitud;

    @Column(name = "fecha_entrega")
    Date fechaEntregaEstimada;

    @Column(name = "numero_unidades")
    Long numeroUnidades;

    @Column(name = "paquete_recibido")
    Boolean paqueteRecibido;

    @Column(name = "paquete_en_buen_estado")
    Boolean paqueteEnBuenEstado;

    @Column(name = "pagado")
    Boolean pagado;

    public Long getId() {
        return id;
    }

    public StatusStateEnum getState() {
        return state;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Producto getProductoSolicitado() {
        return productoSolicitado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public Date getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public Long getNumeroUnidades() {
        return numeroUnidades;
    }

    public Boolean getPaqueteRecibido() {
        return paqueteRecibido;
    }

    public Boolean getPaqueteEnBuenEstado() {
        return paqueteEnBuenEstado;
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setState(StatusStateEnum state) {
        this.state = state;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setProductoSolicitado(Producto productoSolicitado) {
        this.productoSolicitado = productoSolicitado;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public void setFechaEntregaEstimada(Date fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public void setNumeroUnidades(Long numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public void setPaqueteRecibido(Boolean paqueteRecibido) {
        this.paqueteRecibido = paqueteRecibido;
    }

    public void setPaqueteEnBuenEstado(Boolean paqueteEnBuenEstado) {
        this.paqueteEnBuenEstado = paqueteEnBuenEstado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }
}
