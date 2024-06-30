package org.tp3.servicio;

import org.tp3.DAO.HistorialCargas.IHistorialCargasDAO;
import org.tp3.modelo.Cliente;
import org.tp3.modelo.HistorialCargas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistorialCargasServicio {
    private IHistorialCargasDAO historialCargasDAO;

    public HistorialCargasServicio(IHistorialCargasDAO historialCargasDAO) {
        this.historialCargasDAO = historialCargasDAO;
    }

    public void agregarHistoria(HistorialCargas historia) {
        try {
            historialCargasDAO.agregarCarga(historia);
        } catch (SQLException e) {
            System.err.println("Error al agregar historial: " + e.getMessage());
        }
    }



    public void eliminarHistorial(int id) {
        try {
            historialCargasDAO.eliminarCarga(id);
        } catch (SQLException e) {
            System.err.println("Error al eliminar historial: " + e.getMessage());
        }
    }
}
