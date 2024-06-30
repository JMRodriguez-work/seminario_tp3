package org.tp3.servicio;

import org.tp3.DAO.Cliente.IClienteDAO;
import org.tp3.modelo.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteServicio {
    private IClienteDAO clienteDAO;

    public ClienteServicio(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void agregarCliente(Cliente cliente) {
        try {
            clienteDAO.agregarCliente(cliente);
        } catch (SQLException e) {
            System.err.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> getClientes() {
        try {
            return clienteDAO.obtenerTodosClientes();
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
            return null;
        }
    }

    public Cliente obtenerCliente(int id) {
        try {
            return clienteDAO.obtenerCliente(id);
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente: " + e.getMessage());
            return null;
        }
    }

    public void eliminarCliente(int id) {
        try {
            clienteDAO.eliminarCliente(id);
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
