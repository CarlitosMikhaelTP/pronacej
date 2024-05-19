package com.carlitos.Pronacej.InicioActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.R;

public class OptionTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        ConstraintLayout buttonUserA = findViewById(R.id.Opcion1);
        ConstraintLayout buttonUserB = findViewById(R.id.Opcion2);
        ConstraintLayout buttonUserC = findViewById(R.id.Opcion3);

        buttonUserA.setOnClickListener(view -> {
            startActivity(new Intent(OptionTypeActivity.this, MenuActivity.class));
            // Acción cuando se presiona el botón Usuario A
            Toast.makeText(OptionTypeActivity.this, "Seleccionaste CJDR", Toast.LENGTH_SHORT).show();
        });

        buttonUserB.setOnClickListener(view -> {
            startActivity(new Intent(OptionTypeActivity.this, MenuActivity.class));
            // Acción cuando se presiona el botón Usuario B
            Toast.makeText(OptionTypeActivity.this, "Seleccionaste SOA", Toast.LENGTH_SHORT).show();
        });

        buttonUserC.setOnClickListener(view -> {
            startActivity(new Intent(OptionTypeActivity.this, MenuActivity.class));
            // Acción cuando se presiona el botón Usuario C
            Toast.makeText(OptionTypeActivity.this, "Seleccionaste PASPE", Toast.LENGTH_SHORT).show();
        });
    }
}
