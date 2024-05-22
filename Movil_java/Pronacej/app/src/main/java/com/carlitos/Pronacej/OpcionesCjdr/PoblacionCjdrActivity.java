package com.carlitos.Pronacej.OpcionesCjdr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosTotalPoblacionCjdr;


public class PoblacionCjdrActivity extends AppCompatActivity {

    private int totalRegistros;
    private int ingresoSentenciado;
    private int ingresoProcesado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion_cjdr);

        Intent intent = getIntent();
        totalRegistros = intent.getIntExtra("totalRegistros", 0);
        ingresoSentenciado = intent.getIntExtra("ingresoSentenciado", 0);
        ingresoProcesado = intent.getIntExtra("ingresoProcesado", 0);
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);

        opcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 1
                Intent intentOpcion1 = new Intent(PoblacionCjdrActivity.this, ResultadosTotalPoblacionCjdr.class);
                intentOpcion1.putExtra("totalRegistros", totalRegistros);
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(PoblacionCjdrActivity.this, ResultadosSituacionJuridicaCjdr.class);
                intentOpcion2.putExtra("ingresoSentenciado", ingresoSentenciado);
                intentOpcion2.putExtra("ingresoProcesado", ingresoProcesado);
                startActivity(intentOpcion2);
            }
        });
    }

}
