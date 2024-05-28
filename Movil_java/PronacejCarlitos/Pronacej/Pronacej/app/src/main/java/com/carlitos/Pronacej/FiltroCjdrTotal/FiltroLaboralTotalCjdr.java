package com.carlitos.Pronacej.FiltroCjdrTotal;

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

import com.carlitos.Pronacej.OpcionesCjdr.InsercionLaboralCjdrActivity;
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

public class FiltroLaboralTotalCjdr extends AppCompatActivity {

    private int seguro_sis;
    private int seguro_essalud;
    private int seguro_particular;
    private int seguro_ninguno;
    private int inser_labo_interna;
    private int inser_labo_externa;
    private int no_trabaja;

    private Button etFechaInicio;
    private Button etFechaFin;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;

    private CjdrService cjdrService;

    private DatePickerDialog datePickerDialogInicio;
    private DatePickerDialog datePickerDialogFinal;
    private Button dateButtonInicio;
    private Button dateButtonFinal;
    private String selectedDateInicio;
    private String selectedDateFinal;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_laboral_total_cjdr);

        dateButtonInicio = findViewById(R.id.etFechaInicio);
        dateButtonFinal = findViewById(R.id.etFechaFin);
        initDatePickerInicio();
        initDatePickerFinal();
        selectedDateInicio = getTodaysDate();
        selectedDateFinal = getTodaysDate();


        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        cjdrService = Apis.getCjdrService();

        btnGenerarGrafico.setOnClickListener(view -> {
            String fechaInicio = showSelectedDateInicio(dateButtonInicio).toString().trim();
            String fechaFin = showSelectedDateFinal(dateButtonFinal).toString().trim();

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
        Call<List<Map<String, Object>>> call = cjdrService.obtenerIL(fechaInicio, fechaFin);
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
                        Intent intent = new Intent(FiltroLaboralTotalCjdr.this, InsercionLaboralCjdrActivity.class);
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







    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(dayOfMonth, month, year);
    }

    private void initDatePickerInicio() {
        // Establece el Locale a español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectedDateInicio = makeDateString(dayOfMonth, month, year);
                dateButtonInicio.setText(selectedDateInicio);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialogInicio = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
        datePickerDialogInicio.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private void initDatePickerFinal() {
        // Establece el Locale a español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectedDateFinal = makeDateString(dayOfMonth, month, year);
                dateButtonFinal.setText(selectedDateFinal);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialogFinal = new DatePickerDialog(this, style, dateSetListener, year, month, dayOfMonth);
        datePickerDialogFinal.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month) + " " + dayOfMonth + " " + year;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1: return "ENE";
            case 2: return "FEB";
            case 3: return "MAR";
            case 4: return "ABR";
            case 5: return "MAY";
            case 6: return "JUN";
            case 7: return "JUL";
            case 8: return "AGO";
            case 9: return "SEP";
            case 10: return "OCT";
            case 11: return "NOV";
            case 12: return "DIC";
            default: return "ENE";
        }
    }

    private String formatDateToYMD(int year, int month, int dayOfMonth) {
        return String.format("%04d-%02d-%02d", year, month, dayOfMonth);
    }

    public void openDatePickerInicio(View view) {
        datePickerDialogInicio.show();
    }

    public void openDatePickerFinal(View view) {
        datePickerDialogFinal.show();
    }

    public String showSelectedDateInicio(View view) {
        String[] datePartsInicio = selectedDateInicio.split(" ");
        int dayInicio = Integer.parseInt(datePartsInicio[1]);
        int monthInicio = getMonthNumber(datePartsInicio[0]);
        int yearInicio = Integer.parseInt(datePartsInicio[2]);
        String formattedDateInicio = formatDateToYMD(yearInicio, monthInicio, dayInicio);

        return formattedDateInicio;

    }
    public String showSelectedDateFinal(View view) {
        String[] datePartsFinal = selectedDateFinal.split(" ");
        int dayFinal = Integer.parseInt(datePartsFinal[1]);
        int monthFinal = getMonthNumber(datePartsFinal[0]);
        int yearFinal = Integer.parseInt(datePartsFinal[2]);
        String formattedDateFinal = formatDateToYMD(yearFinal, monthFinal, dayFinal);

        return formattedDateFinal;

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
}
