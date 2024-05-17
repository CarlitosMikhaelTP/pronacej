package com.carlitos.Pronacej;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadoDosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_dos);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String sentenciados = intent.getStringExtra("sentenciados");
        String procesados = intent.getStringExtra("procesados");

        // Configurar el gráfico circular (PieChart)
        PieChart pieChart = findViewById(R.id.pieChart);

        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(Integer.parseInt(sentenciados), "Sentenciados"));
        pieEntries.add(new PieEntry(Integer.parseInt(procesados), "Procesados"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Sentenciados vs Procesados");

        // Personalizar colores
        int[] colors = {Color.rgb(255, 102, 102), Color.rgb(102, 204, 255)}; // Colores para Sentenciados y Procesados respectivamente
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}
