package org.tp3.DAO.Usuario;

import org.tp3.modelo.Usuario;

import java.sql.SQLException;

public interface IUsuarioDAO {
    void agregarUsuario(Usuario usuario) throws SQLException;
    Usuario obtenerUsuario(int id) throws SQLException;
    void eliminarUsuario(int id) throws SQLException;
}
