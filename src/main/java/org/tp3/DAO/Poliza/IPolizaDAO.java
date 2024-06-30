package org.tp3.DAO.Poliza;
import org.tp3.modelo.Poliza;

import java.sql.SQLException;
import java.util.List;

public interface IPolizaDAO {
    void agregarPoliza(Poliza poliza) throws SQLException;
    Poliza obtenerPoliza(int id) throws SQLException;
    List<Poliza> obtenerTodasPolizas() throws SQLException;
    void actualizarPoliza(Poliza poliza) throws SQLException;
    void eliminarPoliza(int id) throws SQLException;
}
