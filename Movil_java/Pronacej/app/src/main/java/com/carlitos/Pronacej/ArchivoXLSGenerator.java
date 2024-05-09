package com.carlitos.Pronacej;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
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
import java.util.ArrayList;
import java.util.List;

public class ArchivoXLSGenerator {

    public static void generarArchivosXLS(Context context, Activity activity, EditText editableUno, EditText editableDos, EditText editableTres, EditText editableCuatro,
                                          EditText editableCinco, EditText editableSeis, EditText editableSiete, EditText editableOcho,
                                          EditText editableNueve, EditText editableDiez, EditText editableOnce, EditText editableDoce,
                                          EditText editableTrece, EditText editableCatorce, EditText editableQuince, EditText editableDiecisies,
                                          EditText editableDiecisiete, EditText editableDieciocho, EditText editableDiecinueve, EditText editableVeinte,
                                          EditText editableVeintiUno, EditText editableVeintiDos, EditText editableVeintiTres, EditText editableVeintiCuatro,
                                          EditText editableVeintiCinco, EditText editableVeintiSeis, EditText editableVeintiSiete, EditText editableVeintiOcho) {

        // Nombre base del archivo
        // Produce errores al momento de la creación por priemra vez
        String nombreBase = "COR.R6.1.3.0.FR.091_";

        //  Usando la clase Environment para obtener el directorio de almacenamiento externo público del dispositivo Android
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath());
        File[] files = dir.listFiles();

        // Listando archivos en el directorio de documentos y filtrando aquellos cuyos nombres comienzan con el nombre base
        // Estos archivos se alamcenan en una lista llamada archivosExistentes
        List<File> archivosExistente = new ArrayList<>();
        for (File file : files) {
            if (file.getName().startsWith(nombreBase)) {
                archivosExistente.add(file);
            }
        }

        // Si no se encontraron archivos con el formato buscado, crear el primer archivo haciendo uso
        // de la clase GeneratePrimerArchivoXLS
        if (archivosExistente.isEmpty()) {
            GeneratePrimerArchivoXLS generador = new GeneratePrimerArchivoXLS();
            generador.crearPrimerArchivo(context, activity, nombreBase, dir, editableUno, editableDos, editableTres, editableCuatro, editableCinco, editableSeis,
                    editableSiete, editableOcho, editableNueve, editableDiez, editableOnce, editableDoce,
                    editableTrece, editableCatorce, editableQuince, editableDiecisies, editableDiecisiete, editableDieciocho,
                    editableDiecinueve, editableVeinte, editableVeintiUno, editableVeintiDos, editableVeintiTres,
                    editableVeintiCuatro, editableVeintiCinco, editableVeintiSeis, editableVeintiSiete, editableVeintiOcho);
            return;
        }

        // Encontrar el máximo contador entre los archivos existentes
        int maxContador = 0;
        for (File archivo : archivosExistente) {
            String nombreArchivo = archivo.getName();
            int contador = obtenerContador(nombreArchivo, nombreBase);
            maxContador = Math.max(maxContador, contador);
        }

        // Incrementar el contador para obtener el nuevo nombre del archivo
        int nuevoContador = maxContador + 1;
        String nuevoNombreArchivo = nombreBase + nuevoContador + ".xls";

        // Crear el archivo con el nuevo nombre
        File nuevoArchivo = new File(dir, nuevoNombreArchivo);

        // Verificar si el archivo ya existe
        if (nuevoArchivo.exists()) {
            Toast.makeText(context, "El archivo ya existe", Toast.LENGTH_SHORT).show();
            return; // Salir del método si el archivo ya existe
        }

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
        row2.createCell(13).setCellValue(editableCatorce.getText().toString());
        row2.createCell(14).setCellValue(editableQuince.getText().toString());
        row2.createCell(15).setCellValue(editableDiecisies.getText().toString());
        row2.createCell(16).setCellValue(editableDiecisiete.getText().toString());
        row2.createCell(17).setCellValue(editableDieciocho.getText().toString());
        row2.createCell(18).setCellValue(editableDiecinueve.getText().toString());
        row2.createCell(19).setCellValue(editableVeinte.getText().toString());
        row2.createCell(20).setCellValue(editableVeintiUno.getText().toString());
        row2.createCell(21).setCellValue(editableVeintiDos.getText().toString());
        row2.createCell(22).setCellValue(editableVeintiTres.getText().toString());
        row2.createCell(23).setCellValue(editableVeintiCuatro.getText().toString());
        row2.createCell(24).setCellValue(editableVeintiCinco.getText().toString());
        row2.createCell(25).setCellValue(editableVeintiSeis.getText().toString());
        row2.createCell(26).setCellValue(editableVeintiSiete.getText().toString());
        row2.createCell(27).setCellValue(editableVeintiOcho.getText().toString());

        try {
            // Limpiar los campos de texto
            limpiarCamposDeTexto(editableUno, editableDos, editableTres, editableCuatro, editableCinco, editableSeis,
                    editableSiete, editableOcho, editableNueve, editableDiez, editableOnce, editableDoce,
                    editableTrece, editableCatorce, editableQuince, editableDiecisies, editableDiecisiete, editableDieciocho,
                    editableDiecinueve, editableVeinte, editableVeintiUno, editableVeintiDos, editableVeintiTres,
                    editableVeintiCuatro, editableVeintiCinco, editableVeintiSeis, editableVeintiSiete, editableVeintiOcho);

            // Desplazar la vista hacia arriba
            ScrollView scrollView = activity.findViewById(R.id.scrollView);
            scrollView.scrollTo(0, 0);

            FileOutputStream fos = new FileOutputStream(nuevoArchivo);
            workbook.write(fos);
            fos.close();
            Toast.makeText(context, "Archivo XLS generado correctamente", Toast.LENGTH_SHORT).show();

            // Incrementar el contador de archivos
            contadorArchivos++;

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al generar el archivo XLS", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para obtener el contador de un nombre de archivo dado el nombre base
    public static int obtenerContador(String nombreArchivo, String nombreBase) {
        String contadorStr = nombreArchivo.substring(nombreBase.length(), nombreArchivo.indexOf(".xls"));
        return Integer.parseInt(contadorStr);
    }

    // Contador de Archivos
    private static int contadorArchivos = 1;

    // Función para limpiar los espacios del formulario después de registrar /////////////////////////////
    public static void limpiarCamposDeTexto(EditText editableUno, EditText editableDos, EditText editableTres, EditText editableCuatro,
                                             EditText editableCinco, EditText editableSeis, EditText editableSiete, EditText editableOcho,
                                             EditText editableNueve, EditText editableDiez, EditText editableOnce, EditText editableDoce,
                                             EditText editableTrece, EditText editableCatorce, EditText editableQuince, EditText editableDiecisies,
                                             EditText editableDiecisiete, EditText editableDieciocho, EditText editableDiecinueve, EditText editableVeinte,
                                             EditText editableVeintiUno, EditText editableVeintiDos, EditText editableVeintiTres, EditText editableVeintiCuatro,
                                             EditText editableVeintiCinco, EditText editableVeintiSeis, EditText editableVeintiSiete, EditText editableVeintiOcho) {
        editableUno.setText("");
        editableDos.setText("");
        editableTres.setText("");
        editableCuatro.setText("");
        editableCinco.setText("");
        editableSeis.setText("");
        editableSiete.setText("");
        editableOcho.setText("");
        editableNueve.setText("");
        editableDiez.setText("");
        editableOnce.setText("");
        editableDoce.setText("");
        editableTrece.setText("");
        editableCatorce.setText("");
        editableQuince.setText("");
        editableDiecisies.setText("");
        editableDiecisiete.setText("");
        editableDieciocho.setText("");
        editableDiecinueve.setText("");
        editableVeinte.setText("");
        editableVeintiUno.setText("");
        editableVeintiDos.setText("");
        editableVeintiTres.setText("");
        editableVeintiCuatro.setText("");
        editableVeintiCinco.setText("");
        editableVeintiSeis.setText("");
        editableVeintiSiete.setText("");
        editableVeintiOcho.setText("");
    }
}
