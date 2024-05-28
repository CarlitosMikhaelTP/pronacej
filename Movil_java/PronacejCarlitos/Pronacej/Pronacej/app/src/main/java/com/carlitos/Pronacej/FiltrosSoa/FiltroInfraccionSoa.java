package com.carlitos.Pronacej.FiltrosSoa;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.OpcionesSoa.InfraccionesCometidasSoaActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroInfraccionSoa extends AppCompatActivity {

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
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private String selectedDate;

    private SoaService soaService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_infraccion_soa);

        initDatePicker();
        dateButton = findViewById(R.id.etFechaInicio);
        selectedDate = getTodaysDate();
        dateButton.setText(selectedDate);
        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        soaService = Apis.getSoaService();

        btnGenerarGrafico.setOnClickListener(view -> {
            showSelectedDate(etFechaInicio);

            String fechaInicio = showSelectedDate(etFechaInicio).toString().trim();

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
        Call<List<Map<String, Object>>> call = soaService.obtenerIC(fechaInicio, null);
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
                        Intent intent = new Intent(FiltroInfraccionSoa.this, InfraccionesCometidasSoaActivity.class);
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


    private void initDatePicker() {
        // Establece el Locale a español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectedDate = makeDateString(dayOfMonth, month, year);
                dateButton.setText(selectedDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }
    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }
    private String getMonthFormat(int month) {
        if (month == 1) return "ENE";
        if (month == 2) return "FEB";
        if (month == 3) return "MAR";
        if (month == 4) return "ABR";
        if (month == 5) return "MAY";
        if (month == 6) return "JUN";
        if (month == 7) return "JUL";
        if (month == 8) return "AGO";
        if (month == 9) return "SEP";
        if (month == 10) return "OCT";
        if (month == 11) return "NOV";
        if (month == 12) return "DIC";
        return "ENE";
    }
    private String formatDateToYMD(int year, int month, int dayOfMonth) {
        return String.format("%04d-%02d-%02d", year, month, dayOfMonth);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public String showSelectedDate(View view) {
        String[] dateParts = selectedDate.split(" ");
        int day = Integer.parseInt(dateParts[1]);
        int month = getMonthNumber(dateParts[0]);
        int year = Integer.parseInt(dateParts[2]);
        String formattedDate = formatDateToYMD(year, month, day);

        String formato;
        formato = formattedDate;
        return formattedDate;

    }


    private int getMonthNumber(String month) {
        switch (month) {
            case "ENE": return 1;
            case "FEB": return 2;
            case "MAR": return 3;
            case "ABR": return 4;
            case "MAY": return 5;
            case "JUN": return 6;
            case "JUL": return 7;
            case "AGO": return 8;
            case "SEP": return 9;
            case "OCT": return 10;
            case "NOV": return 11;
            case "DIC": return 12;
            default: return 1;
        }
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dayOfMonth, month, year);
    }
}
