package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.R;

public class CategoriaMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_menu);

        ConstraintLayout btncomenzar = findViewById(R.id.opcion_uno);
        ConstraintLayout btncomenzar2 = findViewById(R.id.opcion_dos);

        // Se establecen las funciones del botón
        btncomenzar.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(CategoriaMenu.this, OpcionesCentrosJuvenilesActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });

        btncomenzar2.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(CategoriaMenu.this, OpcionesCentrosJuvenilesActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });
    }
}
