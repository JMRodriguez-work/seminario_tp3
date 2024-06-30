package org.tp3;

import org.tp3.DAO.Cliente.ClienteDAO;
import org.tp3.DAO.Deuda.DeudaDAO;
import org.tp3.DAO.Poliza.PolizaDAO;
import org.tp3.modelo.Cliente;
import org.tp3.modelo.Deuda;
import org.tp3.modelo.Poliza;
import org.tp3.servicio.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Connection conn;
    private static CargaArchivoServicio cargaArchivoServicio;
    private static PolizaServicio polizaServicio;
    private static DeudaServicio deudaServicio;
    private static ClienteServicio clienteServicio;

    public static void main(String[] args) {
        try {

            // Esto se mejoraría utilizando variables de entorno para proteger credenciales
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminario", "root", "");
            cargaArchivoServicio = new CargaArchivoServicio(conn);
            polizaServicio = new PolizaServicio(new PolizaDAO(conn));
            deudaServicio = new DeudaServicio(new DeudaDAO(conn));
            clienteServicio = new ClienteServicio(new ClienteDAO(conn));
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Agregar Pólizas");
            System.out.println("2. Agregar Deudas");
            System.out.println("3. Ver Pólizas");
            System.out.println("4. Ver Deudas");
            System.out.println("5. Ver Clientes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese compañía: ");
                    String compania = scanner.nextLine();

                    String rutaPoliza = "src/main/resources/archivos/poliza_demo.xlsx";
                    try {
                        cargaArchivoServicio.cargarPolizas(new File(rutaPoliza), compania, rutaPoliza);
                        System.out.println("\nPólizas agregadas\n");
                    } catch (Exception e) {
                        System.out.println("Error al cargar el archivo de pólizas: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Ingrese compañía: ");
                    String deudaCompania = scanner.nextLine();

                    String rutaDeuda = "src/main/resources/archivos/deudores_demo.xlsx";
                    try {
                        cargaArchivoServicio.cargarDeudas(new File(rutaDeuda), deudaCompania, rutaDeuda);
                        System.out.println("\nDeudas agregadas\n");
                    } catch (Exception e) {
                        System.out.println("Error al cargar el archivo de deudas: " + e.getMessage());
                    }
                    break;
                case 3:
                    List<Poliza> polizas = polizaServicio.getPolizas();
                    if (polizas.isEmpty()) {
                        System.out.println("No hay pólizas disponibles.");
                    } else {
                        for (Poliza poliza : polizas) {
                            System.out.println(poliza);
                        }
                    }
                    break;
                case 4:
                    List<Deuda> deudas = deudaServicio.getDeudas();
                    if (deudas.isEmpty()) {
                        System.out.println("No hay deudas disponibles.");
                    } else {
                        for (Deuda deuda : deudas) {
                            System.out.println(deuda);
                        }
                    }
                    break;
                case 5:
                    List<Cliente> clientes = clienteServicio.getClientes();
                    if(clientes.isEmpty()) {
                        System.out.println("No hay clientes disponibles.");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente);
                        }
                    }
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}