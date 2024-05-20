package com.carlitos.Pronacej.ActivitysPadres;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.R;

public class OpcionesCentrosJuvenilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_centros_juveniles);

        ConstraintLayout buttonUserA = findViewById(R.id.Opcion1);
        ConstraintLayout buttonUserB = findViewById(R.id.Opcion2);
        ConstraintLayout buttonUserC = findViewById(R.id.Opcion3);

        buttonUserA.setOnClickListener(view -> {
            startActivity(new Intent(OpcionesCentrosJuvenilesActivity.this, MenuCjdrActivity.class));
            // Acción cuando se presiona el botón Usuario A
            Toast.makeText(OpcionesCentrosJuvenilesActivity.this, "Seleccionaste CJDR", Toast.LENGTH_SHORT).show();
        });

        buttonUserB.setOnClickListener(view -> {
            startActivity(new Intent(OpcionesCentrosJuvenilesActivity.this, MenuCjdrActivity.class));
            // Acción cuando se presiona el botón Usuario B
            Toast.makeText(OpcionesCentrosJuvenilesActivity.this, "Seleccionaste SOA", Toast.LENGTH_SHORT).show();
        });

        buttonUserC.setOnClickListener(view -> {
            startActivity(new Intent(OpcionesCentrosJuvenilesActivity.this, MenuCjdrActivity.class));
            // Acción cuando se presiona el botón Usuario C
            Toast.makeText(OpcionesCentrosJuvenilesActivity.this, "Seleccionaste PASPE", Toast.LENGTH_SHORT).show();
        });
    }
}
