package org.tp3.servicio;

import org.tp3.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {
    private List<Cliente> clientes = new ArrayList<>();

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}