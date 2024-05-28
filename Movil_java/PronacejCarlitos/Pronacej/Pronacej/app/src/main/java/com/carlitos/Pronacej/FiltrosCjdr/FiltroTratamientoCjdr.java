package com.carlitos.Pronacej.FiltrosCjdr;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.OpcionesCjdr.InfraccionesCometidasCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.OpcionesCjdr.TratamientoDiferenciadoCjdrActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroTratamientoCjdr extends AppCompatActivity {

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
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private String selectedDate;
    private CjdrService cjdrService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_tratamiento_cjdr);


        initDatePicker();
        dateButton = findViewById(R.id.etFechaInicio);
        selectedDate = getTodaysDate();
        dateButton.setText(selectedDate);

        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        cjdrService = Apis.getCjdrService();

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
        Call<List<Map<String, Object>>> call = cjdrService.obtenerTD(fechaInicio, null);
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
                        Intent intent = new Intent(FiltroTratamientoCjdr.this, TratamientoDiferenciadoCjdrActivity.class);
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
