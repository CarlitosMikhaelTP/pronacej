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
import com.carlitos.Pronacej.ResultadosSoa.ResultadosEstadoCivilSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosEstadoSoa;
import com.carlitos.Pronacej.ResultadosSoa.ResultadosSexoSoa;
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

    private int estado_cierre_post;
    private int estado_egr;
    private int estado_ing;
    private int estado_ing_post;
    private int estado_civil_casado;
    private int estado_civil_conviviente;
    private int estado_civil_separado;
    private int estado_civil_soltero;
    private int estado_civil_viudo;
    private int sexo_masculino;
    private int sexo_femenino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion_soa);

        Intent intent = getIntent();
        totalRegistros = intent.getIntExtra("totalRegistros", 0);
        ingresoSentenciado = intent.getIntExtra("ingresoSentenciado", 0);
        ingresoProcesado = intent.getIntExtra("ingresoProcesado", 0);
        estado_cierre_post = intent.getIntExtra("estado_cierre_post", 0);
        estado_egr = intent.getIntExtra("estado_egr", 0);
        estado_ing = intent.getIntExtra("estado_ing", 0);
        estado_ing_post = intent.getIntExtra("estado_ing_post", 0);
        estado_civil_casado = intent.getIntExtra("estado_civil_casado", 0);
        estado_civil_conviviente = intent.getIntExtra("estado_civil_conviviente", 0);
        estado_civil_separado = intent.getIntExtra("estado_civil_separado", 0);
        estado_civil_soltero = intent.getIntExtra("estado_civil_soltero", 0);
        estado_civil_viudo = intent.getIntExtra("estado_civil_viudo", 0);
        sexo_masculino = intent.getIntExtra("sexo_masculino", 0);
        sexo_femenino = intent.getIntExtra("sexo_femenino", 0);
        // Asignar click listener a los ConstraintLayouts
        ConstraintLayout opcion1 = findViewById(R.id.Opcion1);
        ConstraintLayout opcion2 = findViewById(R.id.Opcion2);
        ConstraintLayout opcion3 = findViewById(R.id.Opcion3);
        ConstraintLayout opcion4 = findViewById(R.id.Opcion4);
        ConstraintLayout opcion5 = findViewById(R.id.Opcion5);

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

        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion3 = new Intent(PoblacionSoaActivity.this, ResultadosEstadoSoa.class);
                intentOpcion3.putExtra("estado_cierre_post", estado_cierre_post);
                intentOpcion3.putExtra("estado_egr", estado_egr);
                intentOpcion3.putExtra("estado_ing", estado_ing);
                intentOpcion3.putExtra("estado_ing_post", estado_ing_post);
                startActivity(intentOpcion3);
            }
        });

        opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion4 = new Intent(PoblacionSoaActivity.this, ResultadosEstadoCivilSoa.class);
                intentOpcion4.putExtra("estado_civil_casado", estado_civil_casado);
                intentOpcion4.putExtra("estado_civil_conviviente", estado_civil_conviviente);
                intentOpcion4.putExtra("estado_civil_separado", estado_civil_separado);
                intentOpcion4.putExtra("estado_civil_soltero", estado_civil_soltero);
                intentOpcion4.putExtra("estado_civil_viudo", estado_civil_viudo);
                startActivity(intentOpcion4);
            }
        });


        opcion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la actividad correspondiente al presionar el ConstraintLayout 2
                Intent intentOpcion5 = new Intent(PoblacionSoaActivity.this, ResultadosSexoSoa.class);
                intentOpcion5.putExtra("sexo_masculino", sexo_masculino);
                intentOpcion5.putExtra("sexo_femenino", sexo_femenino);
                startActivity(intentOpcion5);
            }
        });
    }

}
