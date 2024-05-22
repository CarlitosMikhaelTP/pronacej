package com.carlitos.Pronacej.ResultadosCjrd;

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
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;


public class ResultadosSituacionLaboralActualCjdr extends AppCompatActivity {

    private int inser_labo_interna;
    private int inser_labo_externa;
    private int no_trabaja;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_situacion_laboral_cjdr);

        // Obtener los valores de las variables desde el intent
        inser_labo_interna = getIntent().getIntExtra("inser_labo_interna", 0);
        inser_labo_externa = getIntent().getIntExtra("inser_labo_externa", 0);
        no_trabaja = getIntent().getIntExtra("no_trabaja", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, inser_labo_interna));
        entries.add(new BarEntry(1, inser_labo_externa));
        entries.add(new BarEntry(2, no_trabaja));

        BarDataSet dataSet = new BarDataSet(entries, "Situación Laboral Actual");
        dataSet.setColors(new int[]{getResources().getColor(com.google.android.material.R.color.design_default_color_primary),
                getResources().getColor(com.google.android.material.R.color.design_default_color_on_secondary),
                getResources().getColor(com.google.android.material.R.color.design_default_color_primary_dark)});
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.invalidate(); // Refresh the chart

        // Customize X axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                switch ((int) value) {
                    case 0:
                        return "Interna";
                    case 1:
                        return "Externa";
                    case 2:
                        return "No Trabaja";
                    default:
                        return "";
                }
            }
        });

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        // Customize legend
        Legend legend = barChart.getLegend();
        legend.setEnabled(false); // Disable legend or customize as needed
    }

}
