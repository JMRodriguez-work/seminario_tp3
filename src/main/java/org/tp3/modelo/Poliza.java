package org.tp3.modelo;

import java.util.Date;

public class Poliza {
    private static int contador = 0;
    private int id;
    private int idCliente;
    private String poliza;
    private String fechaInicio;
    private String fechaFin;
    private int idArchivo;
    private String rama;

    public Poliza(int idCliente, String poliza, String fechaInicio, String fechaFin, int idArchivo, String rama) {
        this.id = ++contador;
        this.idCliente = idCliente;
        this.poliza = poliza;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idArchivo = idArchivo;
        this.rama = rama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getRama() {
        return rama;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    @Override
    public String toString() {
        return "Poliza{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", poliza='" + poliza + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", idArchivo=" + idArchivo +
                ", rama='" + rama + '\'' +
                '}';
    }
}