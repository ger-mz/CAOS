package com.example.demo.reglasNegocio;

import com.example.demo.entity.Proveedor;
import org.springframework.stereotype.Component;

@Component
public class ValidarDatosContacto {
    // Expresión regular básica para email
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public boolean esContactoValido(Proveedor proveedor) {
        return tieneEmailValido(proveedor) && tieneCelularValido(proveedor);
    }

    public boolean tieneEmailValido(Proveedor proveedor) {
        String email = proveedor.getEmail();
        return email != null && !email.isBlank() && email.matches(EMAIL_REGEX);
    }

    public boolean tieneCelularValido(Proveedor proveedor) {
        String celular = proveedor.getCelular();
        return celular != null && !celular.isBlank() && celular.length() == 10;
    }
}
