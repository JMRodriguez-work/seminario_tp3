package org.tp3.servicio;

import org.tp3.modelo.Deuda;

import java.util.ArrayList;
import java.util.List;

public class DeudaServicio {
    private List<Deuda> deudas = new ArrayList<>();

    public void agregarDeuda(Deuda deuda) {
        deudas.add(deuda);
    }

    public List<Deuda> getDeudas() {
        return deudas;
    }
}