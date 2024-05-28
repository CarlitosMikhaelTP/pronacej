package com.carlitos.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSituacionJuridicaIngresoCjdr extends AppCompatActivity {

    private int ingreso_sentenciado;
    private int ingreso_procesado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_situacion_juridica_ingreso_cjdr);

        // Obtener los valores de ingresoSentenciado y ingresoProcesado
        Intent intent = getIntent();
        ingreso_sentenciado = intent.getIntExtra("ingreso_sentenciado", 0);
        ingreso_procesado = intent.getIntExtra("ingreso_procesado", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Configurar los datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, ingreso_sentenciado));
        entries.add(new BarEntry(1f, ingreso_procesado));

        BarDataSet barDataSet = new BarDataSet(entries, "Situación Jurídica Ingreso");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(12f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);

        // Configurar ejes y leyenda
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        Legend legend = barChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        // Establecer los datos en el gráfico
        barChart.setData(barData);
        barChart.invalidate();
    }
}
