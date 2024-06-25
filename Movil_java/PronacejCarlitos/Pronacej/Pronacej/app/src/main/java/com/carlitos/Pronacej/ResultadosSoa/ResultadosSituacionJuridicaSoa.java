package com.carlitos.Pronacej.ResultadosSoa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultadosSituacionJuridicaSoa extends AppCompatActivity {

    private HorizontalBarChart barChart;
    private ArrayList<HashMap<String, String>> reportData;
    private TextView[] textViewsPorcentaje = new TextView[26];
    private TextView[] textViewsNombre = new TextView[26];

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_situacion_juridica_soa);// Obtener los valores de ingresoSentenciado y ingresoProcesado
        barChart = findViewById(R.id.barChart);

        // Obtener los datos pasados desde la actividad anterior
        Intent intent = getIntent();
        reportData = (ArrayList<HashMap<String, String>>) intent.getSerializableExtra("reportData");

        // Verificar si reportData es null
        if (reportData == null || reportData.isEmpty()) {
            // Manejo de error si no hay datos
            return;
        }

        // Referencias a los TextView de porcentaje
        textViewsPorcentaje[25] = findViewById(R.id.textViewTrujilloPorcentaje3);
        textViewsPorcentaje[24] = findViewById(R.id.textViewJose_QuinonesPorcentaje3);
        textViewsPorcentaje[23] = findViewById(R.id.textViewMiguel_GrauPorcentaje3);
        textViewsPorcentaje[22] = findViewById(R.id.textViewSanta_MargaritaPorcentaje3);
        textViewsPorcentaje[21] = findViewById(R.id.textViewAnexoIIIPorcentaje3);
        textViewsPorcentaje[20] = findViewById(R.id.textViewLimaPorcentaje3);
        textViewsPorcentaje[19] = findViewById(R.id.textViewAlfonsoUgartePorcentaje2);
        textViewsPorcentaje[18] = findViewById(R.id.textViewMarcavallePorcentaje2);
        textViewsPorcentaje[17] = findViewById(R.id.textViewPucallpaPorcentaje2);
        textViewsPorcentaje[16] = findViewById(R.id.textViewEl_TamboPorcentaje2);
        textViewsPorcentaje[15] = findViewById(R.id.textViewTrujilloPorcentaje2);
        textViewsPorcentaje[14] = findViewById(R.id.textViewJose_QuinonesPorcentaje2);
        textViewsPorcentaje[13] = findViewById(R.id.textViewMiguel_GrauPorcentaje2);
        textViewsPorcentaje[12] = findViewById(R.id.textViewSanta_MargaritaPorcentaje2);
        textViewsPorcentaje[11] = findViewById(R.id.textViewAnexoIIIPorcentaje2);
        textViewsPorcentaje[10] = findViewById(R.id.textViewLimaPorcentaje2);
        textViewsPorcentaje[9] = findViewById(R.id.textViewAlfonsoUgartePorcentaje);
        textViewsPorcentaje[8] = findViewById(R.id.textViewMarcavallePorcentaje);
        textViewsPorcentaje[7] = findViewById(R.id.textViewPucallpaPorcentaje);
        textViewsPorcentaje[6] = findViewById(R.id.textViewEl_TamboPorcentaje);
        textViewsPorcentaje[5] = findViewById(R.id.textViewTrujilloPorcentaje);
        textViewsPorcentaje[4] = findViewById(R.id.textViewJose_QuinonesPorcentaje);
        textViewsPorcentaje[3] = findViewById(R.id.textViewMiguel_GrauPorcentaje);
        textViewsPorcentaje[2] = findViewById(R.id.textViewSanta_MargaritaPorcentaje);
        textViewsPorcentaje[1] = findViewById(R.id.textViewAnexoIIIPorcentaje);
        textViewsPorcentaje[0] = findViewById(R.id.textViewLimaPorcentaje);

        // Referencias a los TextView de nombres
        textViewsNombre[25] = findViewById(R.id.textViewTrujillo3);
        textViewsNombre[24] = findViewById(R.id.textViewJose_Quinones3);
        textViewsNombre[23] = findViewById(R.id.textViewMiguel_Grau3);
        textViewsNombre[22] = findViewById(R.id.textViewSanta_Margarita3);
        textViewsNombre[21] = findViewById(R.id.textViewAnexoIII3);
        textViewsNombre[20] = findViewById(R.id.textViewLima3);
        textViewsNombre[19] = findViewById(R.id.textViewAlfonso_Ugarte2);
        textViewsNombre[18] = findViewById(R.id.textViewMarcavalle2);
        textViewsNombre[17] = findViewById(R.id.textViewPucallpa2);
        textViewsNombre[16] = findViewById(R.id.textViewEl_Tambo2);
        textViewsNombre[15] = findViewById(R.id.textViewTrujillo2);
        textViewsNombre[14] = findViewById(R.id.textViewJose_Quinones2);
        textViewsNombre[13] = findViewById(R.id.textViewMiguel_Grau2);
        textViewsNombre[12] = findViewById(R.id.textViewSanta_Margarita2);
        textViewsNombre[11] = findViewById(R.id.textViewAnexoIII2);
        textViewsNombre[10] = findViewById(R.id.textViewLima2);
        textViewsNombre[9] = findViewById(R.id.textViewAlfonso_Ugarte);
        textViewsNombre[8] = findViewById(R.id.textViewMarcavalle);
        textViewsNombre[7] = findViewById(R.id.textViewPucallpa);
        textViewsNombre[6] = findViewById(R.id.textViewEl_Tambo);
        textViewsNombre[5] = findViewById(R.id.textViewTrujillo);
        textViewsNombre[4] = findViewById(R.id.textViewJose_Quinones);
        textViewsNombre[3] = findViewById(R.id.textViewMiguel_Grau);
        textViewsNombre[2] = findViewById(R.id.textViewSanta_Margarita);
        textViewsNombre[1] = findViewById(R.id.textViewAnexoIII);
        textViewsNombre[0] = findViewById(R.id.textViewLima);

        // Crear los objetos BarEntry a partir de los datos
        ArrayList<BarEntry> entries = new ArrayList<>();

        // Calcular la población total
        float totalVarones = 0;
        float totalMujeres = 0;
        for (HashMap<String, String> data : reportData) {
            totalVarones += parseFloat(data.get("varones_soa"));
            totalMujeres += parseFloat(data.get("mujeres_soa"));
        }

        // Asignar los datos a los TextView y crear las entradas del gráfico
        for (int i = 0; i < reportData.size(); i++) {
            HashMap<String, String> data = reportData.get(i);
            float varones = parseFloat(data.get("varones_soa"));
            float mujeres = parseFloat(data.get("mujeres_soa"));
            float total = varones + mujeres;
            float porcentajeVarones = (varones / totalVarones) * 100;
            float porcentajeMujeres = (mujeres / totalMujeres) * 100;

            entries.add(new BarEntry(i, new float[]{varones, mujeres}));
            textViewsPorcentaje[i].setText(String.format("Varones: %.2f%%, Mujeres: %.2f%%", porcentajeVarones, porcentajeMujeres));
            textViewsNombre[i].setText(data.get("centro_soa"));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.Pronacej10));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej9));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej8));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej7));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej6));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej5));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej4));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej3));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej2));
        colors.add(ContextCompat.getColor(this, R.color.Pronacej1));

        // Crear el conjunto de datos para el gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Centro Juvenil");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.black));
        dataSet.setStackLabels(new String[]{"Varones", "Mujeres"});

        // Crear los datos del gráfico de barras
        BarData barData = new BarData(dataSet);

        // Configurar la leyenda
        barChart.getLegend().setEnabled(true);
        barChart.getLegend().setTextSize(8f);

        // Configurar descripción del gráfico
        barChart.getDescription().setEnabled(false);

        // Configurar el eje X
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return reportData.get((int) value).get("centro_soa");
            }
        });
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

        // Configurar el eje Y
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisRight().setEnabled(false);

        // Establecer los datos en el gráfico y refrescar
        barChart.setData(barData);
        barChart.invalidate(); // refrescar
    }
    private float parseFloat(String value) {
        try {
            return value == null || value.isEmpty() ? 0 : Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}