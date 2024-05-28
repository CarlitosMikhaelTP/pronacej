package com.carlitos.Pronacej.FiltroSoaTotal;

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

import com.carlitos.Pronacej.FiltroCjdrTotal.FiltroPoblacionTotalCjdr;
import com.carlitos.Pronacej.OpcionesCjdr.PoblacionCjdrActivity;
import com.carlitos.Pronacej.OpcionesSoa.PoblacionSoaActivity;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.CjdrService;
import com.carlitos.Pronacej.Utils.SoaService;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltroPoblacionTotalSoa extends AppCompatActivity {

    private int totalRegistros;
    private int ingresoSentenciado;
    private int ingresoProcesado;
    private int estado_cierre_post;
    private int estado_egr;
    private int estado_ing;
    private int estado_ing_post;
    private int estado_civil_casado;
    private int estado_civil_conviviente;
    private int estado_civil_separado;
    private int estado_civil_soltero;
    private int estado_civil_viudo;
    private int sexo_masculino;
    private int sexo_femenino;

    private Button etFechaInicio;
    private Button etFechaFin;
    private TextView tvErrorFecha;
    private Button btnGenerarGrafico;
    private SoaService soaService;
    private DatePickerDialog datePickerDialogInicio;
    private DatePickerDialog datePickerDialogFinal;
    private Button dateButtonInicio;
    private Button dateButtonFinal;
    private String selectedDateInicio;
    private String selectedDateFinal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro_poblacion_total_cjdr);

        dateButtonInicio = findViewById(R.id.etFechaInicio);
        dateButtonFinal = findViewById(R.id.etFechaFin);
        initDatePickerInicio();
        initDatePickerFinal();
        selectedDateInicio = getTodaysDate();
        selectedDateFinal = getTodaysDate();


        tvErrorFecha = findViewById(R.id.tvErrorFecha);
        btnGenerarGrafico = findViewById(R.id.btnEnviar);
        soaService = Apis.getSoaService();

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
                        estado_cierre_post = ((Double) firstElement.get("estado_cierre_post")).intValue();
                        estado_egr = ((Double) firstElement.get("estado_egr")).intValue();
                        estado_ing = ((Double) firstElement.get("estado_ing")).intValue();
                        estado_ing_post = ((Double) firstElement.get("estado_ing_post")).intValue();
                        estado_civil_casado = ((Double) firstElement.get("estado_civil_casado")).intValue();
                        estado_civil_conviviente = ((Double) firstElement.get("estado_civil_conviviente")).intValue();
                        estado_civil_separado = ((Double) firstElement.get("estado_civil_separado")).intValue();
                        estado_civil_soltero = ((Double) firstElement.get("estado_civil_soltero")).intValue();
                        estado_civil_viudo = ((Double) firstElement.get("estado_civil_viudo")).intValue();
                        sexo_masculino = ((Double) firstElement.get("sexo_masculino")).intValue();
                        sexo_femenino = ((Double) firstElement.get("sexo_femenino")).intValue();

                        // Imprimir o almacenar los valores obtenidos
                        Log.d("FiltroPoblacionTotalSoa", "Total Registros: " + totalRegistros);
                        Log.d("FiltroPoblacionTotalSoa", "Ingreso Sentenciado: " + ingresoSentenciado);
                        Log.d("FiltroPoblacionTotalSoa", "Ingreso Procesado: " + ingresoProcesado);

                        // Crear el Intent y añadir los extras
                        Intent intent = new Intent(FiltroPoblacionTotalSoa.this, PoblacionSoaActivity.class);
                        intent.putExtra("totalRegistros", totalRegistros);
                        intent.putExtra("ingresoSentenciado", ingresoSentenciado);
                        intent.putExtra("ingresoProcesado", ingresoProcesado);
                        intent.putExtra("estado_cierre_post", estado_cierre_post);
                        intent.putExtra("estado_egr", estado_egr);
                        intent.putExtra("estado_ing", estado_ing);
                        intent.putExtra("estado_ing_post", estado_ing_post);
                        intent.putExtra("estado_civil_casado", estado_civil_casado);
                        intent.putExtra("estado_civil_conviviente", estado_civil_conviviente);
                        intent.putExtra("estado_civil_separado", estado_civil_separado);
                        intent.putExtra("estado_civil_soltero", estado_civil_soltero);
                        intent.putExtra("estado_civil_viudo", estado_civil_viudo);
                        intent.putExtra("sexo_masculino", sexo_masculino);
                        intent.putExtra("sexo_femenino", sexo_femenino);

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