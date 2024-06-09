package org.tp3.modelo;

import java.time.LocalDate;
import java.util.Date;

public class HistorialCargas {
    private static int contador = 0;
    private int id;
    private LocalDate fecha;
    private String rutaArchivo;

    public HistorialCargas(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.id = ++contador;
        this.fecha = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
}
