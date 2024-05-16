package com.carlitos.Pronacej.OptionsActivitys;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.carlitos.Pronacej.TimeDateFragment.DatePickerFragment;
import com.carlitos.Pronacej.GraphicsActivitys.GraficoUnoActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.TimeDateFragment.TimePickerFragment;

public class OpcionUnoActivity extends AppCompatActivity {
    // Definir variables referidas al tiempo y hora como clickeables para desplegar opciones
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opcion_uno);
    }
}


