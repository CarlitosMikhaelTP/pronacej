package com.carlitos.Pronacej.FiltroSoaTotal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroInfraccionTotalCjdr;
import com.carlitos.Pronacej.OpcionesCjdr.InfraccionesCometidasCjdrActivity;
import com.carlitos.Pronacej.OpcionesSoa.InfraccionesCometidasSoaActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroInfraccionTotalSoa extends AppCompatActivity {

    private int autoaborto;
    private int exposicion_peligro;
    private int feminicidio;
    private int homicidio_c;
    private int homicidio_s;
    private int lesiones_g;
    private int lesiones_l;
    private int parricidio;
    private int sicariato;
    private int otros;
    private int juridica_sentenciado;
    private int juridica_procesado;
    private int ingreso_sentenciado;
    private int ingreso_procesado;

    private EditText etFechaInicio;
    private EditText etFechaFin;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;

    private SoaService soaService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_infraccion_total_soa);

        etFechaInicio = findViewById(R.id.etFechaInicio);
        etFechaFin = findViewById(R.id.etFechaFin);
        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        soaService = Apis.getSoaService();

        btnGenerarGrafico.setOnClickListener(view -> {
            String fechaInicio = etFechaInicio.getText().toString().trim();
            String fechaFin = etFechaFin.getText().toString().trim();

            if (validarFechaFormato(fechaInicio) && (fechaFin.isEmpty() || validarFechaFormato(fechaFin))) {
                tvErrorFecha.setVisibility(View.GONE);
                llamarEndPoint(fechaInicio, fechaFin.isEmpty() ? null : fechaFin);
            } else {
                tvErrorFecha.setVisibility(View.VISIBLE);
            }
        });
    }

    private boolean validarFechaFormato(String fecha) {
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return fecha.matches(pattern);
    }

    private void llamarEndPoint(String fechaInicio, @Nullable String fechaFin)  {
        Call<List<Map<String, Object>>> call = soaService.obtenerIC(fechaInicio, fechaFin);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {

                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {

                        Map<String, Object> firstElement = data.get(0);
                        autoaborto = ((Double) firstElement.get("autoaborto")).intValue();
                        exposicion_peligro = ((Double) firstElement.get("exposicion_peligro")).intValue();
                        feminicidio = ((Double) firstElement.get("feminicidio")).intValue();
                        homicidio_c = ((Double) firstElement.get("homicidio_c")).intValue();
                        homicidio_s = ((Double) firstElement.get("homicidio_s")).intValue();
                        lesiones_g = ((Double) firstElement.get("lesiones_g")).intValue();
                        lesiones_l = ((Double) firstElement.get("lesiones_l")).intValue();
                        parricidio = ((Double) firstElement.get("parricidio")).intValue();
                        sicariato = ((Double) firstElement.get("sicariato")).intValue();
                        otros = ((Double) firstElement.get("otros")).intValue();
                        juridica_sentenciado = ((Double) firstElement.get("juridica_sentenciado")).intValue();
                        juridica_procesado = ((Double) firstElement.get("juridica_procesado")).intValue();
                        ingreso_sentenciado = ((Double) firstElement.get("ingreso_sentenciado")).intValue();
                        ingreso_procesado = ((Double) firstElement.get("ingreso_procesado")).intValue();

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroInfraccionTotalSoa.this, InfraccionesCometidasSoaActivity.class);
                        intent.putExtra("autoaborto", autoaborto);
                        intent.putExtra("exposicion_peligro", exposicion_peligro);
                        intent.putExtra("feminicidio", feminicidio);
                        intent.putExtra("homicidio_c", homicidio_c);
                        intent.putExtra("homicidio_s", homicidio_s);
                        intent.putExtra("lesiones_g", lesiones_g);
                        intent.putExtra("lesiones_l", lesiones_l);
                        intent.putExtra("parricidio", parricidio);
                        intent.putExtra("sicariato", sicariato);
                        intent.putExtra("otros", otros);
                        intent.putExtra("juridica_sentenciado", juridica_sentenciado);
                        intent.putExtra("juridica_procesado", juridica_procesado);
                        intent.putExtra("ingreso_sentenciado", ingreso_sentenciado);
                        intent.putExtra("ingreso_procesado", ingreso_procesado);
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

