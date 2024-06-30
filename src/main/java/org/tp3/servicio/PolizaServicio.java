package org.tp3.servicio;

import org.tp3.DAO.Poliza.IPolizaDAO;
import org.tp3.modelo.Poliza;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PolizaServicio {
    private IPolizaDAO polizaDAO;

    public PolizaServicio(IPolizaDAO polizaDAO) {
        this.polizaDAO = polizaDAO;
    }

    public void agregarPoliza(Poliza poliza) {
        try {
            polizaDAO.agregarPoliza(poliza);
        } catch (SQLException e) {
            System.err.println("Error al agregar póliza: " + e.getMessage());
        }
    }

    public List<Poliza> getPolizas() {
        try {
            return polizaDAO.obtenerTodasPolizas();
        } catch (SQLException e) {
            System.err.println("Error al obtener pólizas: " + e.getMessage());
            return null;
        }
    }

    public Poliza obtenerPoliza(int id) {
        try {
            return polizaDAO.obtenerPoliza(id);
        } catch (SQLException e) {
            System.err.println("Error al obtener póliza: " + e.getMessage());
            return null;
        }
    }

    public void actualizarPoliza(Poliza poliza) {
        try {
            polizaDAO.actualizarPoliza(poliza);
        } catch (SQLException e) {
            System.err.println("Error al actualizar póliza: " + e.getMessage());
        }
    }

    public void eliminarPoliza(int id) {
        try {
            polizaDAO.eliminarPoliza(id);
        } catch (SQLException e) {
            System.err.println("Error al eliminar póliza: " + e.getMessage());
        }
    }
}