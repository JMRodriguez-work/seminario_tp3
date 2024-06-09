package org.tp3.modelo;

import java.util.Date;

public class Deuda {
    private static int contador = 0;
    private int id;
    private int idCliente;
    private double sumaAsegurada;
    private double prima;
    private String fechaInicio;
    private String fechaVto;
    private String moneda;
    private String rama;
    private int idArchivo;

    public Deuda(int idCliente, double sumaAsegurada, double prima, String fechaInicio, String fechaVto, String moneda,String rama ,int idArchivo) {
        this.id = ++contador;
        this.idCliente = idCliente;
        this.sumaAsegurada = sumaAsegurada;
        this.prima = prima;
        this.fechaInicio = fechaInicio;
        this.fechaVto = fechaVto;
        this.moneda = moneda;
        this.rama = rama;
        this.idArchivo = idArchivo;
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

    public double getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(double sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public double getPrima() {
        return prima;
    }

    public void setPrima(double prima) {
        this.prima = prima;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(String fechaVto) {
        this.fechaVto = fechaVto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setRama(String rama) {
        this.rama = rama;
    }

    public String getRama() {
        return rama;
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    @Override
    public String toString() {
        return "Deuda{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", sumaAsegurada=" + sumaAsegurada +
                ", prima=" + prima +
                ", fechaInicio=" + fechaInicio +
                ", fechaVto=" + fechaVto +
                ", moneda='" + moneda + '\'' +
                ", rama='" + rama + '\'' +
                ", idArchivo=" + idArchivo +
                '}';
    }
}