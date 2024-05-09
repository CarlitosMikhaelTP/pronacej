package com.carlitos.Pronacej;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OptionTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        LinearLayout buttonUserA = findViewById(R.id.Opcion1);
        LinearLayout buttonUserB = findViewById(R.id.Opcion2);
        LinearLayout buttonUserC = findViewById(R.id.Opcion3);

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
