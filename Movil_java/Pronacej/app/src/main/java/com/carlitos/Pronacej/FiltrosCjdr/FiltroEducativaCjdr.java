package com.carlitos.Pronacej.FiltrosCjdr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.carlitos.Pronacej.OpcionesCjdr.InsercionEducativaCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.TratamientoDiferenciadoCjdrActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroEducativaCjdr extends AppCompatActivity {

    private int sea_estudia;
    private int sea_termino_basico;
    private int sea_termino_no_doc;
    private int reinsercion_educativa;
    private int insercion_productiva;
    private int continuidad_edu;
    private int apoyo_regularizar;
    private int cebr;
    private int ceba;
    private int cepre;
    private int academia;
    private int cetpro;
    private int instituto;
    private int universidad;
    private int ninguno;
    private EditText etFechaInicio;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;

    private CjdrService cjdrService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_educativa_cjdr);

        etFechaInicio = findViewById(R.id.etFechaInicio);
        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        cjdrService = Apis.getCjdrService();

        btnGenerarGrafico.setOnClickListener(view -> {
            String fechaInicio = etFechaInicio.getText().toString().trim();

            if (validarFechaFormato(fechaInicio)) {
                tvErrorFecha.setVisibility(View.GONE);
                llamarEndPoint(fechaInicio);
            } else {
                tvErrorFecha.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean validarFechaFormato(String fecha) {
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return fecha.matches(pattern);
    }

    private void llamarEndPoint(String fechaInicio) {
        Call<List<Map<String, Object>>> call = cjdrService.obtenerIE(fechaInicio, null);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {

                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        Map<String, Object> firstElement = data.get(0);
                        sea_estudia = ((Double) firstElement.get("sea_estudia")).intValue();
                        sea_termino_basico = ((Double) firstElement.get("sea_termino_basico")).intValue();
                        sea_termino_no_doc = ((Double) firstElement.get("sea_termino_no_doc")).intValue();
                        reinsercion_educativa = ((Double) firstElement.get("reinsercion_educativa")).intValue();
                        insercion_productiva = ((Double) firstElement.get("insercion_productiva")).intValue();
                        continuidad_edu = ((Double) firstElement.get("continuidad_edu")).intValue();
                        apoyo_regularizar = ((Double) firstElement.get("apoyo_regularizar")).intValue();
                        cebr = ((Double) firstElement.get("cebr")).intValue();
                        ceba = ((Double) firstElement.get("ceba")).intValue();
                        cepre = ((Double) firstElement.get("cepre")).intValue();
                        academia = ((Double) firstElement.get("academia")).intValue();
                        cetpro = ((Double) firstElement.get("cetpro")).intValue();
                        instituto = ((Double) firstElement.get("instituto")).intValue();
                        universidad = ((Double) firstElement.get("universidad")).intValue();
                        ninguno = ((Double) firstElement.get("ninguno")).intValue();

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroEducativaCjdr.this, InsercionEducativaCjdrActivity.class);
                        intent.putExtra("sea_estudia", sea_estudia);
                        intent.putExtra("sea_termino_basico", sea_termino_basico);
                        intent.putExtra("sea_termino_no_doc", sea_termino_no_doc);
                        intent.putExtra("reinsercion_educativa", reinsercion_educativa);
                        intent.putExtra("insercion_productiva", insercion_productiva);
                        intent.putExtra("continuidad_edu", continuidad_edu);
                        intent.putExtra("apoyo_regularizar", apoyo_regularizar);
                        intent.putExtra("cebr", cebr);
                        intent.putExtra("ceba", ceba);
                        intent.putExtra("cepre", cepre);
                        intent.putExtra("academia", academia);
                        intent.putExtra("cetpro", cetpro);
                        intent.putExtra("instituto", instituto);
                        intent.putExtra("universidad", universidad);
                        intent.putExtra("ninguno", ninguno);

                        // Iniciar la actividad PoblacionCjdrActivity
                        startActivity(intent);
                    }
                } else {
                    // Maneja el caso de error
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                // Maneja el caso de fallo de la llamada
            }
        });
    }
}
