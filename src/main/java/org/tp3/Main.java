package org.tp3;

import org.tp3.modelo.Cliente;
import org.tp3.modelo.Deuda;
import org.tp3.modelo.Poliza;
import org.tp3.servicio.CargaArchivoServicio;
import org.tp3.servicio.ClienteServicio;
import org.tp3.servicio.DeudaServicio;
import org.tp3.servicio.PolizaServicio;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static PolizaServicio polizaServicio = new PolizaServicio();
    private static DeudaServicio deudaServicio = new DeudaServicio();
    private static ClienteServicio clienteServicio = new ClienteServicio();
    private static CargaArchivoServicio cargaArchivoServicio = new CargaArchivoServicio(clienteServicio, polizaServicio, deudaServicio);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Agregar Pólizas");
            System.out.println("2. Agregar Deudas");
            System.out.println("3. Ver Pólizas");
            System.out.println("4. Ver Deudas");
            System.out.println("5. Ver Clientes");
            System.out.println("6. Salir");
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
                    for (Poliza poliza : polizas) {
                        System.out.println(poliza);
                        System.out.println();
                    }
                    break;
                case 4:
                    List<Deuda> deudas = deudaServicio.getDeudas();
                    for (Deuda deuda : deudas) {
                        System.out.println(deuda);
                        System.out.println();
                    }
                    break;
                case 5:
                    List<Cliente> clientes = clienteServicio.getClientes();
                    for (Cliente cliente : clientes) {
                        System.out.println(cliente);
                        System.out.println();
                    }
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}