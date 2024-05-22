package com.carlitos.Pronacej.FiltroSoaTotal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroTratamientoTotalCjdr;
import com.carlitos.Pronacej.OpcionesCjdr.TratamientoDiferenciadoCjdrActivity;
import com.carlitos.Pronacej.OpcionesSoa.TratamientoDiferenciadoSoaActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroTratamientoTotalSoa extends AppCompatActivity {

    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;
    private int justicia_si;
    private int justicia_no;
    private int agresor_si;
    private int agresor_no;
    private int salud_si;
    private int salud_no;
    private int adn_si;
    private int adn_no;
    private int intervencion_aplica;
    private int intervencion_no_aplica;
    private int firmes_aplica;
    private int firmes_no_aplica;

    private EditText etFechaInicio;
    private EditText etFechaFin;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private SoaService soaService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_tratamiento_total_soa);

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

    private void llamarEndPoint(String fechaInicio, @Nullable String fechaFin) {
        Call<List<Map<String, Object>>> call = soaService.obtenerTD(fechaInicio, fechaFin);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        Map<String, Object> firstElement = data.get(0);
                        participa_programa_uno = ((Double) firstElement.get("participa_programa_uno")).intValue();
                        participa_programa_dos = ((Double) firstElement.get("participa_programa_dos")).intValue();
                        participa_programa_tres = ((Double) firstElement.get("participa_programa_tres")).intValue();
                        participa_programa_cuatro = ((Double) firstElement.get("participa_programa_cuatro")).intValue();
                        participa_programa_cinco = ((Double) firstElement.get("participa_programa_cinco")).intValue();
                        participa_programa_no = ((Double) firstElement.get("participa_programa_no")).intValue();
                        justicia_si = ((Double) firstElement.get("justicia_si")).intValue();
                        justicia_no = ((Double) firstElement.get("justicia_no")).intValue();
                        agresor_si = ((Double) firstElement.get("agresor_si")).intValue();
                        agresor_no = ((Double) firstElement.get("agresor_no")).intValue();
                        salud_si = ((Double) firstElement.get("salud_si")).intValue();
                        salud_no = ((Double) firstElement.get("salud_no")).intValue();
                        adn_si = ((Double) firstElement.get("adn_si")).intValue();
                        adn_no = ((Double) firstElement.get("adn_no")).intValue();
                        intervencion_aplica = ((Double) firstElement.get("intervencion_aplica")).intValue();
                        intervencion_no_aplica = ((Double) firstElement.get("intervencion_no_aplica")).intValue();
                        firmes_aplica = ((Double) firstElement.get("firmes_aplica")).intValue();
                        firmes_no_aplica = ((Double) firstElement.get("firmes_no_aplica")).intValue();

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroTratamientoTotalSoa.this, TratamientoDiferenciadoSoaActivity.class);
                        intent.putExtra("participa_programa_uno", participa_programa_uno);
                        intent.putExtra("participa_programa_dos", participa_programa_dos);
                        intent.putExtra("participa_programa_tres", participa_programa_tres);
                        intent.putExtra("participa_programa_cuatro", participa_programa_cuatro);
                        intent.putExtra("participa_programa_cinco", participa_programa_cinco);
                        intent.putExtra("participa_programa_no", participa_programa_no);
                        intent.putExtra("justicia_si", justicia_si);
                        intent.putExtra("justicia_no", justicia_no);
                        intent.putExtra("agresor_si", agresor_si);
                        intent.putExtra("agresor_no", agresor_no);
                        intent.putExtra("salud_si", salud_si);
                        intent.putExtra("salud_no", salud_no);
                        intent.putExtra("adn_si", adn_si);
                        intent.putExtra("adn_no", adn_no);
                        intent.putExtra("intervencion_aplica", intervencion_aplica);
                        intent.putExtra("intervencion_no_aplica", intervencion_no_aplica);
                        intent.putExtra("firmes_aplica", firmes_aplica);
                        intent.putExtra("ingresoProcesado", firmes_no_aplica);

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