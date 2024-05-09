package com.carlitos.Pronacej;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class OpcionDosActivity extends AppCompatActivity implements View.OnClickListener{

    // Declarando variables para obtener referencias a los componentes EditText
    private EditText editableUno, editableDos, editableTres, editableCuatro,
            editableCinco, editableSeis, editableSiete, editableOcho, editableNueve,
            editableDiez, editableOnce, editableDoce, editableTrece;

    // Definir el código de solicitud para seleccionar un archivo
    private static final int SELECCIONAR_ARCHIVO_REQUEST_CODE = 1;

    // Definir variables referidas al tiempo y hora como clickeables para desplegar opciones
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcion_dos);

        // Estableciendo OnclickListener para estos id de estos componentes para llamar a métodos específicos

        editableUno = findViewById(R.id.editableUno);
        editableUno.setOnClickListener(this);

        editableDos = findViewById(R.id.editableDos);
        editableDos.setOnClickListener(this);

        // Referenciadno un componente de la interfaz de usuario, findViewById Busca busca un componente de la UI en este caso "id"

        editableTres = findViewById(R.id.editableTres);
        editableCuatro = findViewById(R.id.editableCuatro);
        editableCinco = findViewById(R.id.editableCinco);
        editableSeis = findViewById(R.id.editableSeis);
        editableSiete = findViewById(R.id.editableSiete);
        editableOcho = findViewById(R.id.editableOcho);
        editableNueve = findViewById(R.id.editableNueve);

        TextView btncomenzar = findViewById(R.id.btn_signup);

        // Se establecen las funciones del botón
        btncomenzar.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(OpcionDosActivity.this, GraficoDosActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });
    }


    // Llamando al método Generar Archivo XLS
    public void SignUp(View view) {
        ArchivoXLSGeneratorDos.generarArchivosXLS(this, this, editableUno, editableDos, editableTres, editableCuatro, editableCinco, editableSeis,
                editableSiete, editableOcho, editableNueve, editableDiez, editableOnce, editableDoce,
                editableTrece);
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
        if (view.getId() == R.id.editableUno) {
            // Mostrar el DatePicker
            showDatePickerDialog((EditText) view);
        } else if (view.getId() == R.id.editableDos) {
            // Mostrar el TimePicker
            showTimePickerDialog((EditText) view);
        }
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
                    agregarNuevosRegistros(uriDelArchivo);
                } else {
                    // Manejar el caso de que no se pueda obtener la URI del archivo
                    Toast.makeText(this, "No se pudo obtener la URI del archivo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void agregarNuevosRegistros(Uri uriDelArchivo) {
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

                    // Obtén la última fila ocupada en el archivo de Excel
                    int ultimaFila = sheet.getLastRowNum();

                    // Crea una nueva fila y agrega nuevos registros
                    Row nuevaFila = sheet.createRow(ultimaFila + 1); // Crear una nueva fila después de la última fila ocupada

                    nuevaFila.createCell(0).setCellValue(editableUno.getText().toString());
                    nuevaFila.createCell(1).setCellValue(editableDos.getText().toString());
                    nuevaFila.createCell(2).setCellValue(editableTres.getText().toString());
                    nuevaFila.createCell(3).setCellValue(editableCuatro.getText().toString());
                    nuevaFila.createCell(4).setCellValue(editableCinco.getText().toString());
                    nuevaFila.createCell(5).setCellValue(editableSeis.getText().toString());
                    nuevaFila.createCell(6).setCellValue(editableSiete.getText().toString());
                    nuevaFila.createCell(7).setCellValue(editableOcho.getText().toString());
                    nuevaFila.createCell(8).setCellValue(editableNueve.getText().toString());
                    nuevaFila.createCell(9).setCellValue(editableDiez.getText().toString());
                    nuevaFila.createCell(10).setCellValue(editableOnce.getText().toString());
                    nuevaFila.createCell(11).setCellValue(editableDoce.getText().toString());
                    nuevaFila.createCell(12).setCellValue(editableTrece.getText().toString());

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
