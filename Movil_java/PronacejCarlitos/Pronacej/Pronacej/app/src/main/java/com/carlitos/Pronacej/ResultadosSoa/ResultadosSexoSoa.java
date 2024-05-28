package com.carlitos.Pronacej.ResultadosSoa;

import android.content.Intent;
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

public class ResultadosSexoSoa extends AppCompatActivity {

    private int sexo_masculino;
    private int sexo_femenino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_sexo_soa);

        // Obtener los valores de ingresoSentenciado y ingresoProcesado
        Intent intent = getIntent();
        sexo_masculino = intent.getIntExtra("sexo_masculino", 0);
        sexo_femenino = intent.getIntExtra("sexo_femenino", 0);

        // Crear una lista de entradas de datos para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, sexo_masculino));
        entries.add(new BarEntry(1, sexo_femenino));

        // Crear un conjunto de datos para el gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Sexo");
        dataSet.setColors(new int[]{Color.BLUE, Color.RED}); // Colores de las barras

        // Configurar el gráfico de barras
        BarChart chart = findViewById(R.id.chart);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);

        // Crear una instancia de BarData y configurarla con el conjunto de datos
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);

        // Establecer los datos en el gráfico
        chart.setData(barData);

        // Configurar la leyenda
        Legend legend = chart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        chart.invalidate();
    }
}
