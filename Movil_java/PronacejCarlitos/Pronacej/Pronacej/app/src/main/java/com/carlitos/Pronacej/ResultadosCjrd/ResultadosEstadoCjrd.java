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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosEstadoCjrd extends AppCompatActivity {

    private int estado_cierre_post;
    private int estado_egr;
    private int estado_ing;
    private int estado_ing_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_estado_cjdr);

        Intent intent = getIntent();
        estado_cierre_post = intent.getIntExtra("estado_cierre_post", 0);
        estado_egr = intent.getIntExtra("estado_egr", 0);
        estado_ing = intent.getIntExtra("estado_ing", 0);
        estado_ing_post = intent.getIntExtra("estado_ing_post", 0);

        // Crear una lista de entradas de datos para el gráfico de barras
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, estado_cierre_post));
        entries.add(new BarEntry(1, estado_egr));
        entries.add(new BarEntry(2, estado_ing));
        entries.add(new BarEntry(3, estado_ing_post));

        // Crear un conjunto de datos para el gráfico de barras
        BarDataSet dataSet = new BarDataSet(entries, "Estado");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // Colores de las barras

        // Configurar el gráfico de barras
        BarChart chart = findViewById(R.id.barChart);
        chart.getDescription().setEnabled(false);

        // Crear una instancia de BarData y configurarla con el conjunto de datos
        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);

        // Establecer los datos en el gráfico
        chart.setData(barData);
        chart.invalidate();

        // Configurar la leyenda
        Legend legend = chart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda
        legend.setTextSize(12f); // Tamaño del texto de la leyenda
        legend.setForm(Legend.LegendForm.SQUARE); // Forma de la leyenda
        legend.setTextColor(Color.BLACK); // Color del texto de la leyenda
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM); // Alineación vertical de la leyenda
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER); // Alineación horizontal de la leyenda
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); // Orientación de la leyenda
        legend.setDrawInside(false); // Dibujar la leyenda dentro del gráfico
    }


}
