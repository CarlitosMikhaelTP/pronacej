package com.carlitos.Pronacej.OptionsActivitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.carlitos.Pronacej.TimeDateFragment.DatePickerFragment;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.TimeDateFragment.TimePickerFragment;

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






}