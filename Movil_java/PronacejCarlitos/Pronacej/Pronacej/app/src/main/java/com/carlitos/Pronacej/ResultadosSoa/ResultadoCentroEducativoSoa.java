package com.carlitos.Pronacej.ResultadosSoa;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class ResultadoCentroEducativoSoa extends AppCompatActivity {

    private int cebr;
    private int ceba;
    private int cepre;
    private int academia;
    private int cetpro;
    private int instituto;
    private int universidad;
    private int ninguno;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_centro_educativo_soa);

        // Obtener los valores de las variables desde el intent
        cebr = getIntent().getIntExtra("cebr", 0);
        ceba = getIntent().getIntExtra("ceba", 0);
        cepre = getIntent().getIntExtra("cepre", 0);
        academia = getIntent().getIntExtra("academia", 0);
        cetpro = getIntent().getIntExtra("cetpro", 0);
        instituto = getIntent().getIntExtra("instituto", 0);
        universidad = getIntent().getIntExtra("universidad", 0);
        ninguno = getIntent().getIntExtra("ninguno", 0);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, cebr));
        entries.add(new BarEntry(1, ceba));
        entries.add(new BarEntry(2, cepre));
        entries.add(new BarEntry(3, academia));
        entries.add(new BarEntry(4, cetpro));
        entries.add(new BarEntry(5, instituto));
        entries.add(new BarEntry(6, universidad));
        entries.add(new BarEntry(7, ninguno));

        BarDataSet dataSet = new BarDataSet(entries, "Centro Educativo");
        dataSet.setColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary_dark));

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData barData = new BarData(dataSets);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"CEBR", "CEBA", "CEPRE", "Academia", "CETPRO", "Instituto", "Universidad", "Ninguno"}));

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);

        Legend legend = barChart.getLegend();
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setWordWrapEnabled(true);

        barChart.invalidate();
    }
}
