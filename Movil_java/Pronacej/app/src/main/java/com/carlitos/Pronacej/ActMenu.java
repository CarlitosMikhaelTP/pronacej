package com.carlitos.Pronacej;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.InicioActivitys.OptionTypeActivity;

public class ActMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_menu);

        ConstraintLayout btncomenzar = findViewById(R.id.opcion_uno);
        ConstraintLayout btncomenzar2 = findViewById(R.id.opcion_dos);

        // Se establecen las funciones del botón
        btncomenzar.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(ActMenu.this, OptionTypeActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });

        btncomenzar2.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(ActMenu.this, OptionTypeActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });
    }
}
