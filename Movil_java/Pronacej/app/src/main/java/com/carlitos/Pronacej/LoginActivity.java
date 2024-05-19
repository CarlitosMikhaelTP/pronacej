package com.carlitos.Pronacej;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.InicioActivitys.MainActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Button btncomenzar = findViewById(R.id.login);

        // Se establecen las funciones del botón
        btncomenzar.setOnClickListener(view -> {
            // Intent para pasar a otro activity
            Intent intent = new Intent(LoginActivity.this, ActMenu.class);
            // Llamado a la acción de intent
            startActivity(intent);
        });
    }
}
