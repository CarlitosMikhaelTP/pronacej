package com.carlitos.Pronacej.OpcionesSoa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.carlitos.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosSituacionJuridicaCjdr;
import com.carlitos.Pronacej.ResultadosCjrd.ResultadosTotalPoblacionCjdr;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosSituacionJuridicaSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosTotalPoblacionSoa;
import com.carlitos.Pronacej.Utils.CjdrService;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PoblacionSoaActivity extends AppCompatActivity {

    private int totalRegistros;
    private int ingresoSentenciado;
    private int ingresoProcesado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion_soa);

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
                Intent intentOpcion1 = new Intent(PoblacionSoaActivity.this, ResultadosTotalPoblacionSoa.class);
                intentOpcion1.putExtra("totalRegistros", totalRegistros);
                startActivity(intentOpcion1);
            }
        });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion2 = new Intent(PoblacionSoaActivity.this, ResultadosSituacionJuridicaSoa.class);
                intentOpcion2.putExtra("ingresoSentenciado", ingresoSentenciado);
                intentOpcion2.putExtra("ingresoProcesado", ingresoProcesado);
                startActivity(intentOpcion2);
            }
        });
    }

}
