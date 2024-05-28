package com.carlitos.Pronacej.ResultadosCjrd;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ResultadosSituacionEducativaActualCjdr extends AppCompatActivity {

    private int sea_estudia;
    private int sea_termino_basico;
    private int sea_termino_no_doc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_situacion_academica_actual_cjdr);

        // Obtener los valores de las variables desde el intent
        sea_estudia = getIntent().getIntExtra("sea_estudia", 0);
        sea_termino_basico = getIntent().getIntExtra("sea_termino_basico", 0);
        sea_termino_no_doc = getIntent().getIntExtra("sea_termino_no_doc", 0);

        ScatterChart scatterChart = findViewById(R.id.scatterChart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, sea_estudia));
        entries.add(new Entry(1, sea_termino_basico));
        entries.add(new Entry(2, sea_termino_no_doc));

        ScatterDataSet dataSet = new ScatterDataSet(entries, "Situación Educativa Actual");
        dataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        dataSet.setScatterShapeSize(15f);
        dataSet.setColor(ColorTemplate.COLORFUL_COLORS[0]);

        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        ScatterData scatterData = new ScatterData(dataSets);
        scatterChart.setData(scatterData);

        scatterChart.getDescription().setEnabled(false);

        XAxis xAxis = scatterChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Estudia", "Terminó Básico", "Terminó No Doc"}));

        YAxis yAxis = scatterChart.getAxisLeft();
        yAxis.setDrawGridLines(false);

        scatterChart.getAxisRight().setEnabled(false);

        Legend legend = scatterChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        scatterChart.invalidate();
    }

}
