package org.tp3.DAO.Deuda;

import org.tp3.modelo.Deuda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeudaDAO implements IDeudaDAO{
    private Connection db;

    public DeudaDAO(Connection db) {
        this.db = db;
    }


    @Override
    public void agregarDeuda(Deuda deuda) throws SQLException {
        String query = "INSERT INTO deuda (id_cliente, suma_asegurada, prima, fecha_inicio, fecha_vto, moneda, rama, id_archivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, deuda.getIdCliente());
            statement.setDouble(2, deuda.getSumaAsegurada());
            statement.setDouble(3, deuda.getPrima());
            statement.setString(4, deuda.getFechaInicio());
            statement.setString(5, deuda.getFechaVto());
            statement.setString(6, deuda.getMoneda());
            statement.setString(7, deuda.getRama());
            statement.setInt(8, deuda.getIdArchivo());
            statement.executeUpdate();
        }
    }

    @Override
    public Deuda obtenerDeuda(int id) throws SQLException {
        String query = "SELECT * FROM deuda WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Deuda(
                        resultSet.getInt("id_cliente"),
                        resultSet.getDouble("suma_asegurada"),
                        resultSet.getDouble("prima"),
                        resultSet.getString("fecha_inicio"),
                        resultSet.getString("fecha_vto"),
                        resultSet.getString("moneda"),
                        resultSet.getString("rama"),
                        resultSet.getInt("id_archivo")
                );
            }
        }
        return null;
    }

    @Override
    public List<Deuda> obtenerTodasDeudas() throws SQLException {
        List<Deuda> deudas = new ArrayList<>();
        String query = "SELECT * FROM deuda";
        try (Statement statement = db.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                deudas.add(new Deuda(
                        resultSet.getInt("id_cliente"),
                        resultSet.getDouble("suma_asegurada"),
                        resultSet.getDouble("prima"),
                        resultSet.getString("fecha_inicio"),
                        resultSet.getString("fecha_vto"),
                        resultSet.getString("moneda"),
                        resultSet.getString("rama"),
                        resultSet.getInt("id_archivo")
                ));
            }
        }
        return deudas;
    }

    @Override
    public void actualizarDeuda(Deuda deuda) throws SQLException {
        String query = "UPDATE deudas SET idCliente = ?, sumaAsegurada = ?, prima = ?, fechaInicio = ?, fechaVto = ?, moneda = ?, rama = ?, idArchivo = ? WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, deuda.getIdCliente());
            statement.setDouble(2, deuda.getSumaAsegurada());
            statement.setDouble(3, deuda.getPrima());
            statement.setString(4, deuda.getFechaInicio());
            statement.setString(5, deuda.getFechaVto());
            statement.setString(6, deuda.getMoneda());
            statement.setString(7, deuda.getRama());
            statement.setInt(8, deuda.getIdArchivo());
            statement.setInt(9, deuda.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarDeuda(int id) throws SQLException {
        String query = "DELETE FROM deudas WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
