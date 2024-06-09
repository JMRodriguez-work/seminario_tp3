package org.tp3.servicio;

import org.tp3.modelo.Poliza;

import java.util.ArrayList;
import java.util.List;

public class PolizaServicio {
    private List<Poliza> polizas = new ArrayList<>();

    public void agregarPoliza(Poliza poliza) {
        polizas.add(poliza);
    }

    public List<Poliza> getPolizas() {
        return polizas;
    }
}