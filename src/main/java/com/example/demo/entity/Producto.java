package com.example.demo.entity;

import com.example.demo.entity.enums.MonedaProdutoEnum;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    String nombre;

    @Column(columnDefinition = "TEXT")
    String descripcion;

    Long precio;

    @Enumerated(EnumType.STRING)
    MonedaProdutoEnum moneda;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getPrecio() {
        return precio;
    }

    public MonedaProdutoEnum getMoneda() {
        return moneda;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public void setMoneda(MonedaProdutoEnum moneda) {
        this.moneda = moneda;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
