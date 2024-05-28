package com.carlitos.Pronacej.FiltroCjdrTotal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.OpcionesCjdr.InsercionEducativaCjdrActivity;
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

public class FiltroEducativaTotalCjdr extends AppCompatActivity {

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
        setContentView(R.layout.filtro_educativa_total_cjdr);

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

    private void llamarEndPoint(String fechaInicio, @Nullable String fechaFin){
        Call<List<Map<String, Object>>> call = cjdrService.obtenerIE(fechaInicio, fechaFin);
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
                        Intent intent = new Intent(FiltroEducativaTotalCjdr.this, InsercionEducativaCjdrActivity.class);
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
