package com.carlitos.Pronacej;
import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.DialogFragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OpcionUnoActivity extends AppCompatActivity implements View.OnClickListener {

    // Declarando variables para obtener referencias a los componentes EditText
    private EditText editableUno, editableDos, editableTres, editableCuatro,
            editableCinco, editableSeis, editableSiete, editableOcho, editableNueve,
            editableDiez, editableOnce, editableDoce, editableTrece, editableCatorce,
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
        setContentView(R.layout.opcion_uno);

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
            Intent intent = new Intent(OpcionUnoActivity.this, GraficoUnoActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });
    }


    // Llamando al método Generar Archivo XLS
    public void SignUp(View view) {
        ArchivoXLSGenerator.generarArchivosXLS(this, this, editableUno, editableDos, editableTres, editableCuatro, editableCinco, editableSeis,
                editableSiete, editableOcho, editableNueve, editableDiez, editableOnce, editableDoce,
                editableTrece, editableCatorce, editableQuince, editableDiecisies, editableDiecisiete, editableDieciocho,
                editableDiecinueve, editableVeinte, editableVeintiUno, editableVeintiDos, editableVeintiTres,
                editableVeintiCuatro, editableVeintiCinco, editableVeintiSeis, editableVeintiSiete, editableVeintiOcho);
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
                        nuevaFila.createCell(13).setCellValue(editableCatorce.getText().toString());
                        nuevaFila.createCell(14).setCellValue(editableQuince.getText().toString());
                        nuevaFila.createCell(15).setCellValue(editableDiecisies.getText().toString());
                        nuevaFila.createCell(16).setCellValue(editableDiecisiete.getText().toString());
                        nuevaFila.createCell(17).setCellValue(editableDieciocho.getText().toString());
                        nuevaFila.createCell(18).setCellValue(editableDiecinueve.getText().toString());
                        nuevaFila.createCell(19).setCellValue(editableVeinte.getText().toString());
                        nuevaFila.createCell(20).setCellValue(editableVeintiUno.getText().toString());
                        nuevaFila.createCell(21).setCellValue(editableVeintiDos.getText().toString());
                        nuevaFila.createCell(22).setCellValue(editableVeintiTres.getText().toString());
                        nuevaFila.createCell(23).setCellValue(editableVeintiCuatro.getText().toString());
                        nuevaFila.createCell(24).setCellValue(editableVeintiCinco.getText().toString());
                        nuevaFila.createCell(25).setCellValue(editableVeintiSeis.getText().toString());
                        nuevaFila.createCell(26).setCellValue(editableVeintiSiete.getText().toString());
                        nuevaFila.createCell(27).setCellValue(editableVeintiOcho.getText().toString());

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

// CODIGO DE ANTES PRINCIPAL //
    // Función para agregar nuevos registros al ya haber seleccionado el archivo a sobreescribir //////////////////////////////////
   /* private void agregarNuevosRegistros(Uri uriDelArchivo) {
        // Obtener la ruta real del archivo a partir de su URI
        String rutaArchivo = getRealPathFromURI(uriDelArchivo);

        if (rutaArchivo != null) {
            try {
                // Abrir el archivo Excel existente
                FileInputStream fis = new FileInputStream(new File(rutaArchivo));
                Workbook workbook = new HSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0); // Suponiendo que la hoja que deseas modificar es la primera

                // Obtener la última fila ocupada en el archivo de Excel
                int ultimaFila = sheet.getLastRowNum();

                // Crear una nueva fila y agregar nuevos registros después de la última fila ocupada
                Row nuevaFila = sheet.createRow(ultimaFila + 1);

                // Asignar valores a las celdas de la nueva fila
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
                nuevaFila.createCell(13).setCellValue(editableCatorce.getText().toString());
                nuevaFila.createCell(14).setCellValue(editableQuince.getText().toString());
                nuevaFila.createCell(15).setCellValue(editableDiecisies.getText().toString());
                nuevaFila.createCell(16).setCellValue(editableDiecisiete.getText().toString());
                nuevaFila.createCell(17).setCellValue(editableDieciocho.getText().toString());
                nuevaFila.createCell(18).setCellValue(editableDiecinueve.getText().toString());
                nuevaFila.createCell(19).setCellValue(editableVeinte.getText().toString());
                nuevaFila.createCell(20).setCellValue(editableVeintiUno.getText().toString());
                nuevaFila.createCell(21).setCellValue(editableVeintiDos.getText().toString());
                nuevaFila.createCell(22).setCellValue(editableVeintiTres.getText().toString());
                nuevaFila.createCell(23).setCellValue(editableVeintiCuatro.getText().toString());
                nuevaFila.createCell(24).setCellValue(editableVeintiCinco.getText().toString());
                nuevaFila.createCell(25).setCellValue(editableVeintiSeis.getText().toString());
                nuevaFila.createCell(26).setCellValue(editableVeintiSiete.getText().toString());
                nuevaFila.createCell(27).setCellValue(editableVeintiOcho.getText().toString());

                // Guardar los cambios en el archivo
                FileOutputStream fos = new FileOutputStream(new File(rutaArchivo));
                workbook.write(fos);

                // Cerrar recursos
                fos.close();
                fis.close();
                workbook.close();

                // Mostrar mensaje de éxito
                Toast.makeText(this, "Nuevos registros agregados al archivo Excel", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                // Mostrar mensaje de error
                Toast.makeText(this, "Error al agregar nuevos registros al archivo Excel", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mostrar mensaje si no se pudo obtener la ruta del archivo
            Toast.makeText(this, "No se pudo obtener la ruta del archivo", Toast.LENGTH_SHORT).show();
        }
    } */

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Método para obtener la ruta real del archivo a partir de su URI //////////////////////////////////////////////////
    /*private String getRealPathFromURI(Uri uri) {
        String realPath = null;
        try {
            if (uri != null) {
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(uri, null, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToFirst()) { // Mover el cursor al primer resultado
                        int columnIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
                        if (columnIndex >= 0) { // Verificar si el índice de columna es válido
                            realPath = cursor.getString(columnIndex);
                        }
                    }
                    cursor.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realPath;
    }*/



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}




    /*
    // Método para obtener la ruta real del archivo a partir de su URI
    private String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Files.FileColumns.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return null;
    }
    // Método para agregar filas a un archivo de Excel
    private void agregarFilasAExcel(String rutaArchivo) {
        if (rutaArchivo == null) {
            // Manejar el caso de rutaArchivo nula, por ejemplo, mostrar un mensaje de error al usuario.
            Toast.makeText(this, "Ruta del archivo es nula", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Cargar el archivo de Excel existente
            FileInputStream fis = new FileInputStream(new File(rutaArchivo));
            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Suponiendo que la hoja que deseas modificar es la primera

            // Obtener la última fila ocupada en el archivo de Excel
            int ultimaFila = sheet.getLastRowNum();

            // Crear una nueva fila y agregar datos desde el formulario
            Row nuevaFila = sheet.createRow(ultimaFila + 1); // Crear una nueva fila después de la última fila ocupada

            // Aquí puedes agregar las celdas y establecer su contenido según los EditTexts de tu formulario
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
            nuevaFila.createCell(13).setCellValue(editableCatorce.getText().toString());
            nuevaFila.createCell(14).setCellValue(editableQuince.getText().toString());
            nuevaFila.createCell(15).setCellValue(editableDiecisies.getText().toString());
            nuevaFila.createCell(16).setCellValue(editableDiecisiete.getText().toString());
            nuevaFila.createCell(17).setCellValue(editableDieciocho.getText().toString());
            nuevaFila.createCell(18).setCellValue(editableDiecinueve.getText().toString());
            nuevaFila.createCell(19).setCellValue(editableVeinte.getText().toString());
            nuevaFila.createCell(20).setCellValue(editableVeintiUno.getText().toString());
            nuevaFila.createCell(21).setCellValue(editableVeintiDos.getText().toString());
            nuevaFila.createCell(22).setCellValue(editableVeintiTres.getText().toString());
            nuevaFila.createCell(23).setCellValue(editableVeintiCuatro.getText().toString());
            nuevaFila.createCell(24).setCellValue(editableVeintiCinco.getText().toString());
            nuevaFila.createCell(25).setCellValue(editableVeintiSeis.getText().toString());
            nuevaFila.createCell(26).setCellValue(editableVeintiSiete.getText().toString());
            nuevaFila.createCell(27).setCellValue(editableVeintiOcho.getText().toString());

            // Guardar los cambios en el archivo
            FileOutputStream fos = new FileOutputStream(new File(rutaArchivo));
            workbook.write(fos);
            fos.close();
            workbook.close();

            Toast.makeText(this, "Nuevas filas agregadas al archivo Excel", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al agregar filas al archivo Excel", Toast.LENGTH_SHORT).show();
        }
    }*/

   /* private static final int PICK_FILE_REQUEST_CODE = 1;
*/
/*    public void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);*/
/*        intent.setType(); // Establecer el tipo de archivo que deseas seleccionar, en este caso, cualquier tipo de archivo
      /*  startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    /*}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uriDelArchivo = data.getData();
                if (uriDelArchivo != null) {
                    // Aquí tienes la URI del archivo seleccionado, puedes hacer lo que necesites con ella
                    // Por ejemplo, puedes llamar al método agregarFilasAExcel() y pasarle la URI del archivo
                    String rutaArchivo = getRealPathFromURI(uriDelArchivo);
                    if (rutaArchivo != null) {
                        agregarFilasAExcel(rutaArchivo);
                    } else {
                        // Manejar el caso de que no se pueda obtener la ruta del archivo
                        Toast.makeText(this, "No se pudo obtener la ruta del archivo", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Manejar el caso de que no se pueda obtener la URI del archivo
                    Toast.makeText(this, "No se pudo obtener la URI del archivo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    /*

    public void SignUp3(Uri uriDelArchivo){
        // Verificar si la URI del archivo es válida
        if (uriDelArchivo != null) {
            // Obtener la ruta real del archivo a partir de su URI
            String rutaArchivo = getRealPathFromURI(uriDelArchivo);

            // Verificar si la ruta del archivo es válida
            if (rutaArchivo != null) {
                // Llamar al método para agregar filas al archivo de Excel
                agregarFilasAExcel(rutaArchivo);
            } else {
                // Manejar el caso de que no se pueda obtener la ruta del archivo
                Toast.makeText(this, "No se pudo obtener la ruta del archivo", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Manejar el caso de que no se pueda obtener la URI del archivo
            Toast.makeText(this, "No se pudo obtener la URI del archivo", Toast.LENGTH_SHORT).show();
        }
    }

}



    /*public void SignUp2(View view){
        actualizarArchivoXLS();
    }

    public void actualizarArchivoXLS() {
        // Verificar si el archivo ya existe
        String nombreArchivo = "COR.R6.1.3.0.FR.091_" + contadorArchivos + ".xls";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), nombreArchivo);

        try {
            Workbook workbook;
            Sheet sheet;
            Row nuevaFila;

            // Si el archivo existe, abrimos el workbook existente y obtenemos la hoja de cálculo correspondiente
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new HSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);

                // Obtener el número de la última fila ocupada
                int numeroUltimaFila = sheet.getLastRowNum();

                // Crear la nueva fila en la siguiente posición
                nuevaFila = sheet.createRow(numeroUltimaFila + 1);
            } else {
                // Si el archivo no existe, creamos un nuevo workbook y hoja de cálculo
                workbook = new HSSFWorkbook();
                sheet = workbook.createSheet("COR.R6.1.3.0.FR.091_");

                // Creamos la fila inicial en la posición 0
                nuevaFila = sheet.createRow(0);
            }

            // Agregar las celdas con la nueva información a la nueva fila
            nuevaFila.createCell(0).setCellValue(editableUno.getText().toString());
            nuevaFila.createCell(1).setCellValue(editableDos.getText().toString());
            // Continúa con el resto de las celdas, omitidas por brevedad

            // Guardar el archivo
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();

            Toast.makeText(this, "Archivo XLS actualizado correctamente", Toast.LENGTH_SHORT).show();

            // Incrementar el contador de archivos
            contadorArchivos++;

            // Limpiar los campos de texto
            limpiarCamposDeTexto();

            // Desplazar la vista hacia arriba
            ScrollView scrollView = findViewById(R.id.scrollView);
            scrollView.scrollTo(0, 0);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al actualizar el archivo XLS", Toast.LENGTH_SHORT).show();
        }
    }*/


   /* public void actualizarArchivoXLS() {
        // Verificar si el archivo ya existe
        String nombreArchivo = "COR.R6.1.3.0.FR.091_" + contadorArchivos + ".xls";
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), nombreArchivo);

        try {
            Workbook workbook;
            Sheet sheet;
            Row nuevaFila;

            // Si el archivo existe, abrimos el workbook existente y obtenemos la hoja de cálculo correspondiente
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                workbook = new HSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);

                // Obtener el número de la última fila ocupada
                int numeroUltimaFila = sheet.getLastRowNum();

                // Crear la nueva fila en la siguiente posición
                nuevaFila = sheet.createRow(numeroUltimaFila + 1);
            } else {
                // Si el archivo no existe, creamos un nuevo workbook y hoja de cálculo
                workbook = new HSSFWorkbook();
                sheet = workbook.createSheet("COR.R6.1.3.0.FR.091");

                // Creamos la fila inicial en la posición 0
                nuevaFila = sheet.createRow(0);
            }

            // Agregar las celdas con la nueva información a la nueva fila
            nuevaFila.createCell(0).setCellValue(editableUno.getText().toString());
            nuevaFila.createCell(1).setCellValue(editableDos.getText().toString());
            nuevaFila.createCell(3).setCellValue(editableTres.getText().toString());
            nuevaFila.createCell(4).setCellValue(editableCuatro.getText().toString());
            nuevaFila.createCell(5).setCellValue(editableCinco.getText().toString());
            nuevaFila.createCell(6).setCellValue(editableSeis.getText().toString());
            nuevaFila.createCell(7).setCellValue(editableSiete.getText().toString());
            nuevaFila.createCell(8).setCellValue(editableOcho.getText().toString());
            nuevaFila.createCell(9).setCellValue(editableNueve.getText().toString());
            nuevaFila.createCell(10).setCellValue(editableDiez.getText().toString());
            nuevaFila.createCell(11).setCellValue(editableOnce.getText().toString());
            nuevaFila.createCell(12).setCellValue(editableDoce.getText().toString());
            nuevaFila.createCell(13).setCellValue(editableTrece.getText().toString());
            nuevaFila.createCell(14).setCellValue(editableCatorce.getText().toString());
            nuevaFila.createCell(15).setCellValue(editableQuince.getText().toString());
            nuevaFila.createCell(16).setCellValue(editableDiecisies.getText().toString());
            nuevaFila.createCell(17).setCellValue(editableDiecisiete.getText().toString());
            nuevaFila.createCell(18).setCellValue(editableDieciocho.getText().toString());
            nuevaFila.createCell(19).setCellValue(editableDiecinueve.getText().toString());
            nuevaFila.createCell(20).setCellValue(editableVeinte.getText().toString());
            nuevaFila.createCell(21).setCellValue(editableVeintiUno.getText().toString());
            nuevaFila.createCell(22).setCellValue(editableVeintiDos.getText().toString());
            nuevaFila.createCell(23).setCellValue(editableVeintiTres.getText().toString());
            nuevaFila.createCell(24).setCellValue(editableVeintiCuatro.getText().toString());
            nuevaFila.createCell(25).setCellValue(editableVeintiCinco.getText().toString());
            nuevaFila.createCell(26).setCellValue(editableVeintiSeis.getText().toString());
            nuevaFila.createCell(27).setCellValue(editableVeintiSiete.getText().toString());
            nuevaFila.createCell(28).setCellValue(editableVeintiOcho.getText().toString());

            // Guardar el archivo
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();

            Toast.makeText(this, "Archivo XLS actualizado correctamente", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al actualizar el archivo XLS", Toast.LENGTH_SHORT).show();
        }
    }*/


