package com.carlitos.Pronacej;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.DialogFragment;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OpcionTresActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editableCatorce,
            editableQuince, editableDiecisies, editableDiecisiete, editableDieciocho,
            editableDiecinueve, editableVeinte, editableVeintiUno, editableVeintiDos,
            editableVeintiTres, editableVeintiCuatro, editableVeintiCinco, editableVeintiSeis,
            editableVeintiSiete, editableVeintiOcho;

    // Definir el código de solicitud para seleccionar un archivo
    private static final int SELECCIONAR_ARCHIVO_REQUEST_CODE = 1;

    // Definir variables referidas al tiempo y hora como clickeables para desplegar opciones
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcion_tres);

        // Estableciendo OnclickListener para estos id de estos componentes para llamar a métodos específicos


    }

    // Función para habilitar el método DataPickerFragment para desplegar opción de poner la fecha /////////////////
    public void showDatePickerDialog(EditText v) {
        DialogFragment newFragment = DatePickerFragment.newInstance(v);
        // Mostrar el datePicker
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    // Función para habilitar el método TimePickerFragment para desplegar opción de poner la hora ///////////////////
    public void showTimePickerDialog(EditText v) {
        DialogFragment newFragment = TimePickerFragment.newInstance(v);
        // Mostrar el datePicker
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    // Manejo de evento Onclick para mostrar el DatePicker y TimePicker /////////////////////////////////////////////
    @Override
    public void onClick(View view) {
        // Manejar el evento de clic para cualquier EditText

    }
    // Función para que al hacer click en SignUp3 se habilite la función para seleccionar archivos ///
    public void SignUp3(View view) {
        selectFile();
    }

    // Función para seleccionar un archivo desde nuestro  formulario /////////////////////////////////
    public void selectFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*"); // Seleccionar cualquier tipo de archivo
        startActivityForResult(intent, SELECCIONAR_ARCHIVO_REQUEST_CODE);
    }


    // Función para obtener la URI del archivo seleccionado y usarla para sobreeescribir en la siguiente línea
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECCIONAR_ARCHIVO_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                // Obtener la URI del archivo seleccionado utilizando SAF
                Uri uriDelArchivo = data.getData();
                if (uriDelArchivo != null) {
                    // Continuar con el procesamiento del archivo
                    agregarNuevosRegistros(uriDelArchivo, 5);
                } else {
                    // Manejar el caso de que no se pueda obtener la URI del archivo
                    Toast.makeText(this, "No se pudo obtener la URI del archivo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void agregarNuevosRegistros(Uri uriDelArchivo, int numeroFila ) {
        // Obtener el DocumentFile del archivo seleccionado
        DocumentFile documentFile = DocumentFile.fromSingleUri(this, uriDelArchivo);

        if (documentFile != null) {
            try {
                // Obtener el ContentResolver
                ContentResolver contentResolver = getContentResolver();

                // Abrir un OutputStream para escribir en el DocumentFile
                OutputStream outputStream = contentResolver.openOutputStream(uriDelArchivo);

                if (outputStream != null) {
                    // Ahora puedes escribir en outputStream como lo harías con FileOutputStream

                    // Obtener la entrada de flujo de datos del DocumentFile
                    InputStream inputStream = contentResolver.openInputStream(uriDelArchivo);

                    // Abrir el archivo Excel existente
                    Workbook workbook = new HSSFWorkbook(inputStream);
                    Sheet sheet = workbook.getSheetAt(0); // Suponiendo que la hoja que deseas modificar es la primera

                    // Obtén la fila deseada o crea una nueva si no existe
                    Row fila = sheet.getRow(numeroFila);
                    if (fila == null) {
                        fila = sheet.createRow(numeroFila);
                    }

                    // Crear celdas para cada registro comenzando desde la columna 13
                    int columnaInicial = 13; // Columna M, ya que las columnas se indexan desde 0

                    // Crea una nueva fila y agrega nuevos registros
                    fila.createCell(columnaInicial).setCellValue(editableCatorce.getText().toString());
                    fila.createCell(columnaInicial + 1).setCellValue(editableQuince.getText().toString());
                    fila.createCell(columnaInicial + 2).setCellValue(editableDiecisies.getText().toString());
                    fila.createCell(columnaInicial + 3).setCellValue(editableDiecisiete.getText().toString());
                    fila.createCell(columnaInicial + 4).setCellValue(editableDieciocho.getText().toString());
                    fila.createCell(columnaInicial + 5).setCellValue(editableDiecinueve.getText().toString());
                    fila.createCell(columnaInicial + 6).setCellValue(editableVeinte.getText().toString());
                    fila.createCell(columnaInicial + 7).setCellValue(editableVeintiUno.getText().toString());
                    fila.createCell(columnaInicial + 8).setCellValue(editableVeintiDos.getText().toString());
                    fila.createCell(columnaInicial + 9).setCellValue(editableVeintiTres.getText().toString());
                    fila.createCell(columnaInicial + 10).setCellValue(editableVeintiCuatro.getText().toString());
                    fila.createCell(columnaInicial + 11).setCellValue(editableVeintiCinco.getText().toString());
                    fila.createCell(columnaInicial + 12).setCellValue(editableVeintiSeis.getText().toString());
                    fila.createCell(columnaInicial + 13).setCellValue(editableVeintiSiete.getText().toString());
                    fila.createCell(columnaInicial + 14).setCellValue(editableVeintiOcho.getText().toString());

                    // Guardar los cambios en el archivo
                    workbook.write(outputStream);
                    outputStream.close();
                    workbook.close();

                    Toast.makeText(this, "Nuevos registros agregados al archivo Excel", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error al abrir el OutputStream", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                // Manejo de IOException
                e.printStackTrace();
                Toast.makeText(this, "Error de E/S al agregar nuevos registros al archivo Excel", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No se pudo obtener el DocumentFile del archivo", Toast.LENGTH_SHORT).show();
        }
    }
}