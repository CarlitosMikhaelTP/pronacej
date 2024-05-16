package com.carlitos.Pronacej.OptionsActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.carlitos.Pronacej.TimeDateFragment.DatePickerFragment;
import com.carlitos.Pronacej.GraphicsActivitys.GraficoDosActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.TimeDateFragment.TimePickerFragment;

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
}
