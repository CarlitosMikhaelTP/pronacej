package com.carlitos.Pronacej;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GraficoDosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagetwo);

        // Obtener referencias a los elementos de la interfaz
        ImageView imageView = findViewById(R.id.imageViewDisplayed);
        TextView textViewDescription = findViewById(R.id.textViewDescription);

        // Establecer la descripción de la imagen
        textViewDescription.setText("Descripción de la imagen");

        // Aquí puedes establecer la imagen que se mostrará
        // Por ejemplo, si deseas establecerla desde el intent de la actividad anterior:
        Intent intent = getIntent();
        if (intent != null) {
            int imageResourceId = intent.getIntExtra("imageResourceId", R.drawable.graficos2);
            imageView.setImageResource(imageResourceId);
        }
    }
}
