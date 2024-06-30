package org.tp3.DAO.HistorialCargas;

import org.tp3.modelo.HistorialCargas;

import java.sql.*;

public class HistorialCargasDAO implements IHistorialCargasDAO{

    private Connection db;

    public HistorialCargasDAO(Connection db) {
        this.db = db;
    }
    @Override
    public int agregarCarga(HistorialCargas carga) {

        String query = "INSERT INTO historial_cargas (fecha, id_usuario, ruta) VALUES (?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, java.sql.Date.valueOf(carga.getFecha()));
            statement.setInt(2, 1);
            statement.setString(3, carga.getRutaArchivo());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en agregarCarga: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void eliminarCarga(int id) throws SQLException {
        String query = "DELETE FROM historial_cargas WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
