package com.carlitos.Pronacej.ResultadosSoa;

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

public class ResultadosProgramasSoa extends AppCompatActivity {

    private int participa_programa_uno;
    private int participa_programa_dos;
    private int participa_programa_tres;
    private int participa_programa_cuatro;
    private int participa_programa_cinco;
    private int participa_programa_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_programacion_soa);

        //Obtener los valores de ingresoSentenciado y ingresoProcesado
        Intent intent = getIntent();
        participa_programa_uno = intent.getIntExtra("participa_programa_uno", 0);
        participa_programa_dos = intent.getIntExtra("participa_programa_dos", 0);
        participa_programa_tres = intent.getIntExtra("participa_programa_tres", 0);
        participa_programa_cuatro = intent.getIntExtra("participa_programa_cuatro", 0);
        participa_programa_cinco = intent.getIntExtra("participa_programa_cinco", 0);
        participa_programa_no = intent.getIntExtra("participa_programa_no", 0);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Configurar los datos para el gráfico
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, participa_programa_uno));
        entries.add(new BarEntry(0f, participa_programa_dos));
        entries.add(new BarEntry(0f, participa_programa_tres));
        entries.add(new BarEntry(0f, participa_programa_cuatro));
        entries.add(new BarEntry(0f, participa_programa_cinco));
        entries.add(new BarEntry(0f, participa_programa_no));

        BarDataSet barDataSet = new BarDataSet(entries, "Participación en programas");
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
