package com.carlitos.Pronacej.ResultadosSoa;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSeguroMedicoSoa extends AppCompatActivity {

    private int seguro_sis;
    private int seguro_essalud;
    private int seguro_particular;
    private int seguro_ninguno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_seguro_medico_soa);

        // Obtener los valores de las variables desde el intent
        seguro_sis = getIntent().getIntExtra("seguro_sis", 0);
        seguro_essalud = getIntent().getIntExtra("seguro_essalud", 0);
        seguro_particular = getIntent().getIntExtra("seguro_particular", 0);
        seguro_ninguno = getIntent().getIntExtra("seguro_ninguno", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, seguro_sis));
        entries.add(new BarEntry(1f, seguro_essalud));
        entries.add(new BarEntry(2f, seguro_particular));
        entries.add(new BarEntry(3f, seguro_ninguno));

        // Crear el conjunto de datos del gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(new int[]{Color.BLUE, Color.GREEN, Color.RED, Color.GRAY});
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = barChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        // Deshabilitar el eje X
        barChart.getXAxis().setEnabled(false);

        // Agregar los datos al gráfico de barras
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.invalidate(); // Refrescar el gráfico
    }
}
