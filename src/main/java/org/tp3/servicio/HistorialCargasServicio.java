package org.tp3.servicio;

import org.tp3.modelo.Cliente;
import org.tp3.modelo.HistorialCargas;

import java.util.ArrayList;
import java.util.List;

public class HistorialCargasServicio {
    private List<HistorialCargas> histo = new ArrayList<>();

    public void agregarHistoria(HistorialCargas historia) {
        histo.add(historia);
    }

    public List<HistorialCargas> getHistorial() {
        return histo;
    }
}
