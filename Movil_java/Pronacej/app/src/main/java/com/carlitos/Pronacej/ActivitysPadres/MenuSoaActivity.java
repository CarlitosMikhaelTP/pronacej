package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.OptionsCjdr.InfraccionesCometidasActivity;
import com.carlitos.Pronacej.OptionsCjdr.InsercionEducativaCjdrActivity;
import com.carlitos.Pronacej.OptionsCjdr.InsercionLaboralActivity;
import com.carlitos.Pronacej.OptionsCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.OptionsCjdr.TratamientoDiferenciadoCjdrActivity;
import com.carlitos.Pronacej.R;

;

public class MenuSoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cjdr);

        ConstraintLayout opcionUno = findViewById(R.id.Opcion1);
        ConstraintLayout opcionDos = findViewById(R.id.Opcion2);
        ConstraintLayout opcionTres = findViewById(R.id.Opcion3);
        ConstraintLayout opcionCuatro = findViewById(R.id.Opcion4);
        ConstraintLayout opcionCinco = findViewById(R.id.Opcion5);



        // Eventos que abrirá las otras actividades
        opcionUno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, PoblacionCjdrActivity.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, TratamientoDiferenciadoCjdrActivity.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, InsercionEducativaCjdrActivity.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, InsercionLaboralActivity.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuSoaActivity.this, InfraccionesCometidasActivity.class);
                startActivity(intent);
            }
        });

    }

}
