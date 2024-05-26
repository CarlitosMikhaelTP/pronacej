package com.carlitos.Pronacej.FiltroSoaTotal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroPoblacionTotalCjdr;
import com.carlitos.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.OpcionesSoa.PoblacionSoaActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroPoblacionTotalSoa extends AppCompatActivity {

    private int totalRegistros;
    private int ingresoSentenciado;
    private int ingresoProcesado;

    private EditText etFechaInicio;
    private EditText etFechaFin;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private SoaService soaService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_poblacion_total_cjdr);

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
        Call<List<Map<String, Object>>> call = soaService.obtenerePopulation(fechaInicio, fechaFin);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        Map<String, Object> firstElement = data.get(0);
                        totalRegistros = ((Double) firstElement.get("total_registros")).intValue();
                        ingresoSentenciado = ((Double) firstElement.get("ingreso_sentenciado")).intValue();
                        ingresoProcesado = ((Double) firstElement.get("ingreso_procesado")).intValue();

                        // Imprimir o almacenar los valores obtenidos
                        Log.d("FiltroPoblacionTotalSoa", "Total Registros: " + totalRegistros);
                        Log.d("FiltroPoblacionTotalSoa", "Ingreso Sentenciado: " + ingresoSentenciado);
                        Log.d("FiltroPoblacionTotalSoa", "Ingreso Procesado: " + ingresoProcesado);

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroPoblacionTotalSoa.this, PoblacionSoaActivity.class);
                        intent.putExtra("totalRegistros", totalRegistros);
                        intent.putExtra("ingresoSentenciado", ingresoSentenciado);
                        intent.putExtra("ingresoProcesado", ingresoProcesado);

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