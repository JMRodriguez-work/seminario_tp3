package org.tp3.DAO.Usuario;

import org.tp3.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO implements IUsuarioDAO{
    private Connection db;

    public UsuarioDAO(Connection db) {
        this.db = db;
    }

    @Override
    public void agregarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuario (nombre, contraseña, email, rol) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContraseña());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getRol());
            statement.executeUpdate();
        }
    }

    @Override
    public Usuario obtenerUsuario(int id) throws SQLException {
        String query = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearUsuario(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void eliminarUsuario(int id) throws SQLException {
        String query = "DELETE FROM usuario WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Usuario mapearUsuario(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String contraseña = resultSet.getString("contraseña");
        String email = resultSet.getString("email");
        String rol = resultSet.getString("rol");

        return new Usuario(id, nombre, contraseña, email, rol);
    }
}
