package org.tp3.DAO.HistorialCargas;

import org.tp3.modelo.HistorialCargas;

import java.sql.SQLException;

public interface IHistorialCargasDAO {
    int agregarCarga(HistorialCargas carga) throws SQLException;
    void eliminarCarga(int id) throws SQLException;
}
