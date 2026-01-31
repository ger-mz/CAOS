package com.example.demo.reglasNegocio;

import org.springframework.stereotype.Component;

@Component
public class ValidarPrecio {
    public boolean esPrecioValido(Long precio) {
        return precio >= 0;
    }
}
