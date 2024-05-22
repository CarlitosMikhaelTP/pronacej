package com.carlitos.Pronacej.ResultadosSoa;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;

import java.util.ArrayList;

public class ResultadoReinsercionEducativaSoa extends AppCompatActivity {

    private int reinsercion_educativa;
    private int insercion_productiva;
    private int continuidad_edu;
    private int apoyo_regularizar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_reinsercion_edu_soa);

        // Obtener los valores de las variables desde el intent
        reinsercion_educativa = getIntent().getIntExtra("reinsercion_educativa", 0);
        insercion_productiva = getIntent().getIntExtra("insercion_productiva", 0);
        continuidad_edu = getIntent().getIntExtra("continuidad_edu", 0);
        apoyo_regularizar = getIntent().getIntExtra("apoyo_regularizar", 0);

        BubbleChart bubbleChart = findViewById(R.id.bubbleChart);

        ArrayList<BubbleEntry> entries = new ArrayList<>();
        entries.add(new BubbleEntry(0, reinsercion_educativa, reinsercion_educativa * 0.1f)); // (x, y, tamaño de burbuja)
        entries.add(new BubbleEntry(1, insercion_productiva, insercion_productiva * 0.1f));
        entries.add(new BubbleEntry(2, continuidad_edu, continuidad_edu * 0.1f));
        entries.add(new BubbleEntry(3, apoyo_regularizar, apoyo_regularizar * 0.1f));

        BubbleDataSet dataSet = new BubbleDataSet(entries, "Resultado Reinserción Educativa");
        dataSet.setColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary_dark));

        ArrayList<IBubbleDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BubbleData bubbleData = new BubbleData(dataSets);
        bubbleChart.setData(bubbleData);

        bubbleChart.getDescription().setEnabled(false);

        XAxis xAxis = bubbleChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Reinserción Educativa", "Inserción Productiva", "Continuidad Educativa", "Apoyo para Regularizar"}));

        YAxis yAxis = bubbleChart.getAxisLeft();
        yAxis.setDrawGridLines(false);

        bubbleChart.getAxisRight().setEnabled(false);

        Legend legend = bubbleChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        bubbleChart.invalidate();
    }

}
