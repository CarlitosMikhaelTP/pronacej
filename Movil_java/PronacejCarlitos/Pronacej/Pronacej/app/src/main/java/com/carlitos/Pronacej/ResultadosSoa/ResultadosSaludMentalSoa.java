package com.carlitos.Pronacej.ResultadosSoa;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSaludMentalSoa extends AppCompatActivity {

    private int salud_si;
    private int salud_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_salud_mental_soa);

        salud_si = getIntent().getIntExtra("salud_si", 0);
        salud_no = getIntent().getIntExtra("salud_no", 0);

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(salud_si, "Salud Sí"));
        entries.add(new PieEntry(salud_no, "Salud No"));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light));

        // Configurar el tamaño del texto dentro del gráfico de pastel
        dataSet.setValueTextSize(12f);

        // Configurar la leyenda
        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(12f);
        legend.setTextColor(Color.BLACK);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(12f);
        legend.setXEntrySpace(10f);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        // Agregar los datos al gráfico de pastel
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refrescar el gráfico
    }
}
