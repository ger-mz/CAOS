package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social", nullable = false, length = 150)
    String razonSocial; // Nombre de la empresa

    @Column(nullable = false, unique = true, length = 13)
    private String rfc;

    @Column(name = "codigo_postal", length = 5)
    private String codigoPostal;

    @Column(name = "regimen_fiscal")
    private String regimenFiscal;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "proveedor_id")
    private List<Producto> catalogo;

    @Column(name = "email")
    private String email;

    @Column(name = "celular")
    private String celular;

    public Long getId() {
        return id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public List<Producto> getCatalogo() {
        return catalogo;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {
        return celular;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }

    public void setCatalogo(List<Producto> catalogo) {
        this.catalogo = catalogo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
