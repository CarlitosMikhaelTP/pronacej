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
import com.carlitos.Pronacej.OpcionesCjdr.InsercionLaboralCjdrActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroLaboralCjdr extends AppCompatActivity {

    private int seguro_sis;
    private int seguro_essalud;
    private int seguro_particular;
    private int seguro_ninguno;
    private int inser_labo_interna;
    private int inser_labo_externa;
    private int no_trabaja;

    private EditText etFechaInicio;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;

    private CjdrService cjdrService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_laboral_cjdr);

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
        Call<List<Map<String, Object>>> call = cjdrService.obtenerIL(fechaInicio, null);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {

                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {

                        Map<String, Object> firstElement = data.get(0);
                        seguro_sis = ((Double) firstElement.get("seguro_sis")).intValue();
                        seguro_essalud = ((Double) firstElement.get("seguro_essalud")).intValue();
                        seguro_particular = ((Double) firstElement.get("seguro_particular")).intValue();
                        seguro_ninguno = ((Double) firstElement.get("seguro_ninguno")).intValue();
                        inser_labo_interna = ((Double) firstElement.get("inser_labo_interna")).intValue();
                        inser_labo_externa = ((Double) firstElement.get("inser_labo_externa")).intValue();
                        no_trabaja = ((Double) firstElement.get("no_trabaja")).intValue();

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroLaboralCjdr.this, InsercionLaboralCjdrActivity.class);
                        intent.putExtra("seguro_sis", seguro_sis);
                        intent.putExtra("seguro_essalud", seguro_essalud);
                        intent.putExtra("seguro_particular", seguro_particular);
                        intent.putExtra("seguro_ninguno", seguro_ninguno);
                        intent.putExtra("inser_labo_interna", inser_labo_interna);
                        intent.putExtra("inser_labo_externa", inser_labo_externa);
                        intent.putExtra("no_trabaja", no_trabaja);
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
