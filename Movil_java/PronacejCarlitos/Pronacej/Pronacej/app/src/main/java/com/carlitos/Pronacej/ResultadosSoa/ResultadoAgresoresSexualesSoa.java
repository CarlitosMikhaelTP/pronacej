package com.carlitos.Pronacej.ResultadosSoa;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultadoAgresoresSexualesSoa extends AppCompatActivity {

    private int agresor_si;
    private int agresor_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_agresores_sexuales_soa);

        // Obtener los valores de las variables desde el intent
        agresor_si = getIntent().getIntExtra("agresor_si", 0);
        agresor_no = getIntent().getIntExtra("agresor_no", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        // Crear las entradas para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, agresor_si));
        entries.add(new BarEntry(1, agresor_no));

        // Configurar los colores para las barras
        int[] colors = {getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_red_light)};

        // Crear el conjunto de datos del gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Resultados de Agresores Sexuales");
        dataSet.setColors(colors);

        // Configurar el tamaño del texto dentro de las barras
        dataSet.setValueTextSize(12f);

        // Configurar el eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        // Agregar los datos al gráfico de barras
        BarData data = new BarData(dataSet);
        barChart.setData(data);

        // Configurar la leyenda
        Legend legend = barChart.getLegend();
        legend.setEnabled(true);

        barChart.invalidate(); // Refrescar el gráfico
    }
}
