package org.tp3.DAO.Deuda;

import org.tp3.modelo.Deuda;

import java.sql.SQLException;
import java.util.List;

public interface IDeudaDAO {
    void agregarDeuda(Deuda deuda) throws SQLException;
    Deuda obtenerDeuda(int id) throws SQLException;
    List<Deuda> obtenerTodasDeudas() throws SQLException;
    void actualizarDeuda(Deuda deuda) throws SQLException;
    void eliminarDeuda(int id) throws SQLException;
}
