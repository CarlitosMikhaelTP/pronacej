package com.carlitos.Pronacej;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeneratePrimerArchivoXLSDos {

    // Función dedicada a crear el primer archivo xls cuando no se detecta ningún otro archivo xls en la carpeta correspondiente
    public void crearPrimerArchivo(Context context, Activity activity, String nombreBase, File dir, EditText editableUno, EditText editableDos, EditText editableTres, EditText editableCuatro,
                                   EditText editableCinco, EditText editableSeis, EditText editableSiete, EditText editableOcho,
                                   EditText editableNueve, EditText editableDiez, EditText editableOnce, EditText editableDoce,
                                   EditText editableTrece) {

        // Crear el primer archivo con el contador inicializado en 1
        String primerNombreArchivo = nombreBase + "1.xls";
        File primerArchivo = new File(dir, primerNombreArchivo);

        // Crear el workbook y la hoja de cálculo
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("COR.R6.1.3.0.FR.091");

        // Creando Columnas y celdas para cada conjunto de datos
        Row row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Fecha de Recepción:");
        row1.createCell(1).setCellValue("Hora de Recepción:");
        row1.createCell(2).setCellValue("Consecutivo de Muestras:");
        row1.createCell(3).setCellValue("Nombre del producto:");
        row1.createCell(4).setCellValue("Código del producto:");
        row1.createCell(5).setCellValue("Orden del producto:");
        row1.createCell(6).setCellValue("N° de Lote logístico:");
        row1.createCell(7).setCellValue("Peso de muestras:");
        row1.createCell(8).setCellValue("Fecha de Ingreso:");
        row1.createCell(9).setCellValue("Hora de Ingreso:");
        row1.createCell(10).setCellValue("Fecha de Salida:");
        row1.createCell(11).setCellValue("Hora de Salida:");
        row1.createCell(12).setCellValue("Bacterias Aerobias:");
        row1.createCell(13).setCellValue("Hongos y Levaduras:");
        row1.createCell(14).setCellValue("Fecha de Salida:");
        row1.createCell(15).setCellValue("Hora de Salida:");
        row1.createCell(16).setCellValue("Fecha de Ingreso:");
        row1.createCell(17).setCellValue("Hora de Ingreso:");
        row1.createCell(18).setCellValue("Fecha de Salida:");
        row1.createCell(19).setCellValue("Hora de Salida:");
        row1.createCell(20).setCellValue("P.Aeruginosa:");
        row1.createCell(21).setCellValue("E. Coli:");
        row1.createCell(22).setCellValue("S. Aureus:");
        row1.createCell(23).setCellValue("Valoración:");
        row1.createCell(24).setCellValue("Responsable de Análisis:");
        row1.createCell(25).setCellValue("Responsable de Lecturas:");
        row1.createCell(26).setCellValue("Observaciones:");
        row1.createCell(27).setCellValue("Observaciones2:");

        Row row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue(editableUno.getText().toString());
        row2.createCell(1).setCellValue(editableDos.getText().toString());
        row2.createCell(2).setCellValue(editableTres.getText().toString());
        row2.createCell(3).setCellValue(editableCuatro.getText().toString());
        row2.createCell(4).setCellValue(editableCinco.getText().toString());
        row2.createCell(5).setCellValue(editableSeis.getText().toString());
        row2.createCell(6).setCellValue(editableSiete.getText().toString());
        row2.createCell(7).setCellValue(editableOcho.getText().toString());
        row2.createCell(8).setCellValue(editableNueve.getText().toString());
        row2.createCell(9).setCellValue(editableDiez.getText().toString());
        row2.createCell(10).setCellValue(editableOnce.getText().toString());
        row2.createCell(11).setCellValue(editableDoce.getText().toString());
        row2.createCell(12).setCellValue(editableTrece.getText().toString());

        try {
            // Limpiar los campos de texto
            ArchivoXLSGeneratorDos.limpiarCamposDeTexto(editableUno, editableDos, editableTres, editableCuatro, editableCinco, editableSeis,
                    editableSiete, editableOcho, editableNueve, editableDiez, editableOnce, editableDoce, editableTrece);

            // Desplazar la vista hacia arriba
            ScrollView scrollView = activity.findViewById(R.id.scrollViewDos);
            scrollView.scrollTo(0, 0);

            FileOutputStream fos = new FileOutputStream(primerArchivo);
            workbook.write(fos);
            fos.close();
            Toast.makeText(context, "Primer archivo XLS generado correctamente", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace(); // Esta línea imprimirá el stack trace en la consola de LogCat
            Toast.makeText(context, "Error al generar el primer archivo XLS", Toast.LENGTH_SHORT).show();
        }
    }
}
