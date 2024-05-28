package com.carlitos.Pronacej.ResultadosSoa;

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

public class ResultadoJusticiaTerapeuticaSoa extends AppCompatActivity {

    private int justicia_si;
    private int justicia_no;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_justicia_terapeutica_soa);
        justicia_si = getIntent().getIntExtra("justicia_si", 0);
        justicia_no = getIntent().getIntExtra("justicia_no", 0);

        // Configurar el gráfico de pastel
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de pastel
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(justicia_si, "Justicia Sí"));
        entries.add(new PieEntry(justicia_no, "Justicia No"));

        // Configurar los colores para las entradas
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(android.R.color.holo_green_light));
        colors.add(getResources().getColor(android.R.color.holo_red_light));

        // Crear el conjunto de datos del gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "Resultados de Justicia Terapéutica");
        dataSet.setColors(colors);

        // Configurar el tamaño del texto dentro de las entradas
        dataSet.setValueTextSize(12f);

        // Configurar leyenda
        Legend legend = pieChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setWordWrapEnabled(true);

        // Agregar datos al gráfico de pastel
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate(); // Refrescar el gráfico
    }
}
