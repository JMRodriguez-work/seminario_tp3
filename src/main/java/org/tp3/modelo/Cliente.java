package org.tp3.modelo;

public class Cliente {
    private static int contador = 0;
    private int id;
    private String nombre;
    private String dniCuitCuil;
    private String email;

    public Cliente(String nombre, String dniCuitCuil, String email) {
        this.id = ++contador;
        this.nombre = nombre;
        this.dniCuitCuil = dniCuitCuil;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDniCuitCuil() {
        return dniCuitCuil;
    }

    public void setDniCuitCuil(String dniCuitCuil) {
        this.dniCuitCuil = dniCuitCuil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dniCuitCuil='" + dniCuitCuil + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

