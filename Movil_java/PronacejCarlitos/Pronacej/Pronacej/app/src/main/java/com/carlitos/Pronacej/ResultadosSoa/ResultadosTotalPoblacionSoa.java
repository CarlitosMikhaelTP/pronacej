package com.carlitos.Pronacej.ResultadosSoa;

import android.graphics.Color;
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

public class ResultadosTotalPoblacionSoa extends AppCompatActivity {

    private int totalRegistros;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_total_soa);

        BarChart barChart = findViewById(R.id.barChart);

        totalRegistros = getIntent().getIntExtra("totalRegistros", 0);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, totalRegistros));

        BarDataSet dataSet = new BarDataSet(entries, "Total Registros");
        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);

        // Agregar leyenda al gráfico
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

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();
    }
}
