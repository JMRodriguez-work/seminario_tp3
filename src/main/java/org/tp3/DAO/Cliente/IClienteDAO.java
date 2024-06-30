package org.tp3.DAO.Cliente;

import java.sql.SQLException;
import org.tp3.modelo.Cliente;
import java.util.List;

public interface IClienteDAO {
    int agregarCliente(Cliente cliente) throws SQLException;
    Cliente obtenerCliente(int id) throws SQLException;
    List<Cliente> obtenerTodosClientes() throws SQLException;
    void actualizarCliente(Cliente cliente) throws SQLException;
    void eliminarCliente(int id) throws SQLException;
}
