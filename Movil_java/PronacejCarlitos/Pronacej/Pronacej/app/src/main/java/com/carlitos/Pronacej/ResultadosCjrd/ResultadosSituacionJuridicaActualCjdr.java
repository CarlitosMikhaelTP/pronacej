package com.carlitos.Pronacej.ResultadosCjrd;

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

public class ResultadosSituacionJuridicaActualCjdr extends AppCompatActivity {

    private int juridica_sentenciado;
    private int juridica_procesado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_situacion_juridica_actual_cjdr);

        // Obtener los valores de ingresoSentenciado y ingresoProcesado
        Intent intent = getIntent();
        juridica_sentenciado = intent.getIntExtra("juridica_sentenciado", 0);
        juridica_procesado = intent.getIntExtra("juridica_procesado", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, juridica_sentenciado));
        entries.add(new BarEntry(1f, juridica_procesado));

        // Crear el conjunto de datos del gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(new int[]{Color.BLUE, Color.GREEN});
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = barChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda

        // Agregar los datos al gráfico de barras
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.invalidate(); // Refrescar el gráfico
    }
}
