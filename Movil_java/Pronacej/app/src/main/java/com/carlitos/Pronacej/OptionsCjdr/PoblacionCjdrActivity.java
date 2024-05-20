package com.carlitos.Pronacej.OptionsCjdr;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PoblacionCjdrActivity extends AppCompatActivity {

    private CjdrService cjdrService;
    private TextView tvTotalRegistros, tvIngresoSentenciado, tvIngresoProcesado;
    private EditText etFechaInicio;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion_cjdr);

        tvTotalRegistros = findViewById(R.id.tvTotalRegistros);
        tvIngresoSentenciado = findViewById(R.id.tvIngresoSentenciado);
        tvIngresoProcesado = findViewById(R.id.tvIngresoProcesado);
        etFechaInicio = findViewById(R.id.etFechaInicio);

        cjdrService = Apis.getCjdrService();
        etFechaInicio.setOnClickListener(v -> showDatePicker());

        Call<List<Map<String, Object>>> call = cjdrService.obtenerePopulation("2023-05-01", null);
        call.enqueue(new Callback<List<Map<String, Object>>>() {
            @Override
            public void onResponse(Call<List<Map<String, Object>>> call, Response<List<Map<String, Object>>> response) {
                if (response.isSuccessful()) {
                    List<Map<String, Object>> data = response.body();
                    if (data != null && !data.isEmpty()) {
                        Map<String, Object> firstElement = data.get(0);
                        int totalRegistros = ((Double) firstElement.get("total_registros")).intValue();
                        int ingresoSentenciado = ((Double) firstElement.get("ingreso_sentenciado")).intValue();
                        int ingresoProcesado = ((Double) firstElement.get("ingreso_procesado")).intValue();

                        Log.d("PoblacionCjdrActivity", "Total Registros: " + totalRegistros);
                        Log.d("PoblacionCjdrActivity", "Ingreso Sentenciado: " + ingresoSentenciado);
                        Log.d("PoblacionCjdrActivity", "Ingreso Procesado: " + ingresoProcesado);

                        // Muestra los valores en la interfaz de usuario o realiza otras operaciones necesarias
                    }
                } else {
                    // Maneja el caso de error
                }
            }

            @Override
            public void onFailure(Call<List<Map<String, Object>>> call, Throwable t) {
                //
            }
        });
    }
    private void showDatePicker() {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Seleccionar fecha");
        MaterialDatePicker<Long> picker = builder.build();

        picker.addOnPositiveButtonClickListener(selection -> {
            // Obtener la fecha seleccionada
            Long timestampMillis = selection;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTimeInMillis(timestampMillis);

            // Formatear la fecha en el formato requerido por el endpoint
            String fechaInicio = String.format("%04d:%02d:%02d", calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

            etFechaInicio.setText(fechaInicio); // O btnFechaInicio.setText(fechaInicio);

            // Realizar la llamada al endpoint con la fecha seleccionada
            Call<List<Map<String, Object>>> call = cjdrService.obtenerePopulation(fechaInicio, null);
            // ...
        });

        picker.show(getSupportFragmentManager(), "datePicker");
    }

}
