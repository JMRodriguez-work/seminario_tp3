package org.tp3.DAO.Poliza;

import org.tp3.modelo.Poliza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolizaDAO implements IPolizaDAO{
    private Connection db;

    public PolizaDAO(Connection db) {
        this.db = db;
    }

    @Override
    public void agregarPoliza(Poliza poliza) throws SQLException {
        String query = "INSERT INTO polizas (id_cliente, poliza, fecha_inicio, fecha_fin, id_archivo, rama) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, poliza.getIdCliente());
            statement.setString(2, poliza.getPoliza());
            statement.setString(3, poliza.getFechaInicio());
            statement.setString(4, poliza.getFechaFin());
            statement.setInt(5, poliza.getIdArchivo());
            statement.setString(6, poliza.getRama());
            statement.executeUpdate();
        }
    }

    @Override
    public Poliza obtenerPoliza(int id) throws SQLException {
        String query = "SELECT * FROM polizas WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearPoliza(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<Poliza> obtenerTodasPolizas() throws SQLException {
        List<Poliza> polizas = new ArrayList<>();
        String query = "SELECT * FROM polizas";
        try (Statement statement = db.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                polizas.add(mapearPoliza(resultSet));
            }
        }
        return polizas;
    }

    @Override
    public void actualizarPoliza(Poliza poliza) throws SQLException {
        String query = "UPDATE polizas SET id_cliente = ?, poliza = ?, fecha_inicio = ?, fecha_fin = ?, id_archivo = ?, rama = ? WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, poliza.getIdCliente());
            statement.setString(2, poliza.getPoliza());
            statement.setString(3, poliza.getFechaInicio());
            statement.setString(4, poliza.getFechaFin());
            statement.setInt(5, poliza.getIdArchivo());
            statement.setString(6, poliza.getRama());
            statement.setInt(7, poliza.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarPoliza(int id) throws SQLException {
        String query = "DELETE FROM polizas WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Poliza mapearPoliza(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int idCliente = resultSet.getInt("id_cliente");
        String poliza = resultSet.getString("poliza");
        String fechaInicio = resultSet.getString("fecha_inicio");
        String fechaFin = resultSet.getString("fecha_fin");
        int idArchivo = resultSet.getInt("id_archivo");
        String rama = resultSet.getString("rama");

        return new Poliza(idCliente, poliza, fechaInicio, fechaFin, idArchivo, rama);
    }
}
