package com.carlitos.Pronacej.ActivitysPadres;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;

public class PresentacionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);

        // Se declara el btn a usar
        TextView btncomenzar = findViewById(R.id.btnComenzar);

        // Se establecen las funciones del botón
        btncomenzar.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(PresentacionActivity.this, LoginActivity.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}

