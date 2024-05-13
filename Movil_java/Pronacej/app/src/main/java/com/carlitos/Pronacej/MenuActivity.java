package com.carlitos.Pronacej;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        LinearLayout opcionUno = findViewById(R.id.Opcion1);
        LinearLayout opcionDos = findViewById(R.id.Opcion2);
        LinearLayout opcionTres = findViewById(R.id.Opcion3);
        LinearLayout opcionCuatro = findViewById(R.id.Opcion4);
        LinearLayout opcionCinco = findViewById(R.id.Opcion5);
        LinearLayout opcionSeis = findViewById(R.id.Opcion6);


        // Eventos que abrirá las otras actividades
        opcionUno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OpcionUnoActivity.class);
                startActivity(intent);
            }
        });

        opcionDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OpcionDosActivity.class);
                startActivity(intent);
            }
        });

        opcionTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OpcionTresActivity.class);
                startActivity(intent);
            }
        });

        opcionCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OpcionCuatroActivity.class);
                startActivity(intent);
            }
        });

        opcionCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OpcionCincoActivity.class);
                startActivity(intent);
            }
        });

        opcionSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OpcionSeisActivity.class);
                startActivity(intent);
            }
        });

    }

}