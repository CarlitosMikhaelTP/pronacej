package com.carlitos.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosDelitoCjdr extends AppCompatActivity {

    private int autoaborto;
    private int exposicion_peligro;
    private int feminicidio;
    private int homicidio_c;
    private int homicidio_s;
    private int lesiones_g;
    private int lesiones_l;
    private int parricidio;
    private int sicariato;
    private int otros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_delito_cjdr);

        //Obtener los valores de ingresoSentenciado y ingresoProcesado
        Intent intent = getIntent();
        autoaborto = intent.getIntExtra("autoaborto", 0);
        exposicion_peligro = intent.getIntExtra("exposicion_peligro", 0);
        feminicidio = intent.getIntExtra("feminicidio", 0);
        homicidio_c = intent.getIntExtra("homicidio_c", 0);
        homicidio_s = intent.getIntExtra("homicidio_s", 0);
        lesiones_g = intent.getIntExtra("lesiones_g", 0);
        lesiones_l = intent.getIntExtra("lesiones_l", 0);
        parricidio = intent.getIntExtra("parricidio", 0);
        sicariato = intent.getIntExtra("sicariato", 0);
        otros = intent.getIntExtra("otros", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Configurar los datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, autoaborto));
        entries.add(new BarEntry(0f, exposicion_peligro));
        entries.add(new BarEntry(0f, feminicidio));
        entries.add(new BarEntry(0f, homicidio_c));
        entries.add(new BarEntry(0f, homicidio_s));
        entries.add(new BarEntry(0f, lesiones_g));
        entries.add(new BarEntry(0f, lesiones_l));
        entries.add(new BarEntry(0f, parricidio));
        entries.add(new BarEntry(0f, sicariato));
        entries.add(new BarEntry(0f, otros));

        BarDataSet barDataSet = new BarDataSet(entries, "Lista de Delitos Específicos");
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
