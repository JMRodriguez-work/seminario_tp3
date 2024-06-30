package org.tp3.servicio;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.tp3.DAO.Deuda.DeudaDAO;
import org.tp3.DAO.HistorialCargas.HistorialCargasDAO;
import org.tp3.DAO.Poliza.PolizaDAO;
import org.tp3.excepcion.ArchivoInvalidoException;
import org.tp3.modelo.Cliente;
import org.tp3.modelo.Deuda;
import org.tp3.modelo.HistorialCargas;
import org.tp3.modelo.Poliza;

import org.tp3.DAO.Cliente.ClienteDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class CargaArchivoServicio {
    private ClienteDAO clienteDAO;
    private PolizaDAO polizaDAO;
    private DeudaDAO deudaDAO;
    private HistorialCargasDAO historialDAO;
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); // Formato de fecha de tu Excel


    public CargaArchivoServicio(Connection db) {
        this.clienteDAO = new ClienteDAO(db);
        this.polizaDAO = new PolizaDAO(db);
        this.deudaDAO = new DeudaDAO(db);
        this.historialDAO = new HistorialCargasDAO(db);
    }

    public void cargarPolizas(File archivo, String compania, String ruta) throws IOException, ArchivoInvalidoException {
        try (FileInputStream fis = new FileInputStream(archivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            if ("ACG".equals(compania)) {
                formatearACGPoliza(sheet, ruta);
            } else {
                throw new IOException("Compañía no soportada");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cargarDeudas(File archivo, String compania, String ruta) throws IOException, ArchivoInvalidoException, SQLException {
        try (FileInputStream fis = new FileInputStream(archivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            if ("Mercantil".equals(compania)) {
                formatearMercantilDeuda(sheet, ruta);
            } else {
                throw new IOException("Compañía no soportada");
            }
        }
    }

    private void formatearACGPoliza(Sheet sheet, String ruta) throws SQLException {
        int indiceEncabezados = 3;
        HistorialCargas histo = new HistorialCargas(ruta);
        int IdArchivo = historialDAO.agregarCarga(histo);
        try {
            for (int i = indiceEncabezados; i <= 4; i++) {
                Row row = sheet.getRow(i);

                Date fechaInicio = DateUtil.getJavaDate(row.getCell(17).getNumericCellValue());
                Date fechaFin = dateFormat.parse(String.valueOf((int) row.getCell(18).getNumericCellValue()));
                Cliente cliente = new Cliente(
                        row.getCell(12).getStringCellValue(),
                        String.valueOf((int) row.getCell(14).getNumericCellValue()),
                    "");
                int idCliente = clienteDAO.agregarCliente(cliente);
                Poliza poliza = new Poliza(
                        idCliente,
                        String.valueOf((int) row.getCell(9).getNumericCellValue()),
                        String.valueOf(fechaInicio),
                        String.valueOf(fechaFin),
                        IdArchivo,
                        row.getCell(43).getStringCellValue()
                );
                polizaDAO.agregarPoliza(poliza);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar pólizas ACG: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void formatearMercantilDeuda(Sheet sheet, String ruta) throws SQLException {
        int indiceEncabezados = 5;
        HistorialCargas histo = new HistorialCargas(ruta);
        int IdArchivo = historialDAO.agregarCarga(histo);
        try {
            for (int i = indiceEncabezados; i <= 6; i++) {
                Row row = sheet.getRow(i);

                Date fechaVto = DateUtil.getJavaDate(row.getCell(3).getNumericCellValue());

                Cliente cliente = new Cliente(
                        row.getCell(2).getStringCellValue(),
                        row.getCell(10).getStringCellValue(),
                        ""
                );
                int idCliente = clienteDAO.agregarCliente(cliente);

                Deuda deuda = new Deuda(
                        idCliente,
                        row.getCell(9).getNumericCellValue(),
                        row.getCell(6).getNumericCellValue(),
                        "",
                        String.valueOf(fechaVto),
                        "Peso",
                        "Auto",
                        IdArchivo
                );
                deudaDAO.agregarDeuda(deuda);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar deudas Mercantil: " + e.getMessage());
        }
    }
}