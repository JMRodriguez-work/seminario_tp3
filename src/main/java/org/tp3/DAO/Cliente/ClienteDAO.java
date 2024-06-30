package org.tp3.DAO.Cliente;

import org.tp3.modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    private Connection db;

    public ClienteDAO(Connection db) {
        this.db = db;
    }
    @Override
    public int agregarCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO clientes (nombre, dni_cuit_cuil, email) VALUES (?, ?, ?)";
        try (PreparedStatement sql = db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            sql.setString(1, cliente.getNombre());
            sql.setString(2, cliente.getDniCuitCuil());
            sql.setString(3, cliente.getEmail());
            sql.executeUpdate();

            try (ResultSet generatedKeys = sql.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    cliente.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
        }
        return -1;
    }


    @Override
    public Cliente obtenerCliente(int id) throws SQLException {
        String query = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Cliente(
                        resultSet.getString("nombre"),
                        resultSet.getString("dni_cuit_cuil"),
                        resultSet.getString("email")
                );
            }
        }
        return null;
    }

    @Override
    public List<Cliente> obtenerTodosClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM clientes";
        try (Statement statement = db.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                clientes.add(new Cliente(
                        resultSet.getString("nombre"),
                        resultSet.getString("dni_cuit_cuil"),
                        resultSet.getString("email")
                ));
            }
        }
        return clientes;
    }

    @Override
    public void actualizarCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE clientes SET nombre = ?, dni_cuit_cuil = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDniCuitCuil());
            statement.setString(3, cliente.getEmail());
            statement.setInt(4, cliente.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarCliente(int id) throws SQLException {
        String query = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
