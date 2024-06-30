package org.tp3.servicio;

import org.tp3.DAO.Deuda.IDeudaDAO;
import org.tp3.modelo.Deuda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeudaServicio {
    private IDeudaDAO deudaDAO;

    public DeudaServicio(IDeudaDAO deudaDAO) {
        this.deudaDAO = deudaDAO;
    }

    public void agregarDeuda(Deuda deuda) {
        try {
            deudaDAO.agregarDeuda(deuda);
        } catch (SQLException e) {
            System.err.println("Error al agregar deuda: " + e.getMessage());
        }
    }

    public List<Deuda> getDeudas() {
        try {
            return deudaDAO.obtenerTodasDeudas();
        } catch (SQLException e) {
            System.err.println("Error al obtener deudas: " + e.getMessage());
            return null;
        }
    }

    public void eliminarDeuda(int id) {
        try {
            deudaDAO.eliminarDeuda(id);
        } catch (SQLException e) {
            System.err.println("Error al eliminar deuda: " + e.getMessage());
        }
    }
}