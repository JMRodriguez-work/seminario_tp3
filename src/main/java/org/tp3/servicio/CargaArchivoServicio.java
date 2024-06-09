package org.tp3.servicio;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.tp3.excepcion.ArchivoInvalidoException;
import org.tp3.modelo.Cliente;
import org.tp3.modelo.Deuda;
import org.tp3.modelo.HistorialCargas;
import org.tp3.modelo.Poliza;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class CargaArchivoServicio {
    private ClienteServicio clienteServicio;
    private PolizaServicio polizaServicio;
    private DeudaServicio deudaServicio;

    public CargaArchivoServicio(ClienteServicio clienteServicio, PolizaServicio polizaServicio, DeudaServicio deudaServicio) {
        this.clienteServicio = clienteServicio;
        this.polizaServicio = polizaServicio;
        this.deudaServicio = deudaServicio;
    }

    public void cargarPolizas(File archivo, String compania, String ruta) throws IOException, ArchivoInvalidoException {
        FileInputStream fis = new FileInputStream(archivo);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        if(compania.equals("ACG")) {
            formatearACGPoliza(sheet, ruta);
        } else throw new IOException();


        //.. diferentes casos dependiendo de la compañia
        workbook.close();
        fis.close();
    }

    public void cargarDeudas(File archivo, String compania, String ruta) throws IOException, ArchivoInvalidoException {
        FileInputStream fis = new FileInputStream(archivo);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        if(compania.equals("Mercantil")) {
            formatearMercantilDeuda(sheet, ruta);
        } else throw new IOException();


        //.. diferentes casos dependiendo de la compañia
        workbook.close();
        fis.close();
    }

    private void formatearACGPoliza(Sheet sheet, String ruta) {
        int indiceEncabezados = 3;
        HistorialCargas histo = new HistorialCargas(ruta);
        for(int i = indiceEncabezados; i <= 4; i++) {
           Row row = sheet.getRow(i);

           // Cargamos el cliente
            Cliente cliente = new Cliente(row.getCell(12).getStringCellValue(), String.valueOf((int) row.getCell(14).getNumericCellValue()), "");
            clienteServicio.agregarCliente(cliente);
            // Cargamos los datos de la poliza
            Poliza poliza = new Poliza(cliente.getId(), String.valueOf((int) row.getCell(9).getNumericCellValue()), String.valueOf((int) row.getCell(17).getNumericCellValue()), String.valueOf((int) row.getCell(18).getNumericCellValue()), histo.getId(), row.getCell(43).getStringCellValue());
            polizaServicio.agregarPoliza(poliza);
        }
    }

    private void formatearMercantilDeuda(Sheet sheet, String ruta) {
        int indiceEncabezados = 5;
        HistorialCargas histo = new HistorialCargas(ruta);
        for(int i = indiceEncabezados; i <= 6; i++) {
            Row row = sheet.getRow(i);
            // Cargamos el cliente
            Cliente cliente = new Cliente(row.getCell(2).getStringCellValue(), row.getCell(10).getStringCellValue(), "");
            clienteServicio.agregarCliente(cliente);
            // Cargamos los datos de la deuda
            Deuda deuda = new Deuda(cliente.getId(), row.getCell(9).getNumericCellValue(), row.getCell(6).getNumericCellValue(), "", String.valueOf((int) row.getCell(3).getNumericCellValue()), "Peso", "Auto" ,histo.getId());
            deudaServicio.agregarDeuda(deuda);
        }
    }
}