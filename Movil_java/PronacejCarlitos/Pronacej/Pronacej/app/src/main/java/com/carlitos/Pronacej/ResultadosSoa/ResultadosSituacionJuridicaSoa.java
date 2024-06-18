package com.carlitos.Pronacej.ResultadosSoa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ResultadosSituacionJuridicaSoa extends AppCompatActivity {

    private int ingresoSentenciado;
    private int ingresoProcesado;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_situacion_juridica_soa);

        // Obtener los valores de ingresoSentenciado y ingresoProcesado
        Intent intent = getIntent();
        ingresoSentenciado = intent.getIntExtra("ingresoSentenciado", 0);
        ingresoProcesado = intent.getIntExtra("ingresoProcesado", 0);

        // Calcular el total de participantes
        int totalParticipantes = ingresoSentenciado + ingresoProcesado;

        // Calcular los porcentajes
        double porcentajeSentenciado = (double) ingresoSentenciado / totalParticipantes * 100;
        double porcentajeProcesado = (double) ingresoProcesado / totalParticipantes * 100;

        // Configurar el gráfico combinado
        CombinedChart combinedChart = findViewById(R.id.barChart);
        combinedChart.getDescription().setEnabled(false);
        combinedChart.setDrawGridBackground(false);
        combinedChart.setDrawBarShadow(false);

        // Configurar los datos para la gráfica
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, ingresoSentenciado));

        List<Entry> lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(0, ingresoProcesado));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Ingreso Sentenciado");
        barDataSet.setColor(getColor(R.color.Pronacej4));
        barDataSet.setValueTextColor(getColor(R.color.black));
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setBarShadowColor(Color.TRANSPARENT);
        barDataSet.setHighlightEnabled(false);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(10f);

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Ingreso Procesado");
        lineDataSet.setColor(getColor(R.color.Pronacej8));
        lineDataSet.setCircleColor(getColor(R.color.Pronacej8));
        lineDataSet.setLineWidth(2.5f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setDrawValues(true);
        lineDataSet.setValueTextSize(10f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.4f);

        LineData lineData = new LineData(lineDataSet);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(barData);
        combinedData.setData(lineData);

        // Configurar ejes y leyenda
        combinedChart.setData(combinedData);
        combinedChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        combinedChart.getAxisRight().setEnabled(false);

        Legend legend = combinedChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        ((TextView) findViewById(R.id.textViewingresoSentenciadoPorcentaje)).setText(String.format("%.2f%%", porcentajeSentenciado));
        ((TextView) findViewById(R.id.textViewingresoSentenciado)).setText("Sentenciado");
        ((TextView) findViewById(R.id.textViewingresoProcesadoPorcentaje)).setText(String.format("%.2f%%", porcentajeProcesado));
        ((TextView) findViewById(R.id.textViewingresoProcesado)).setText("Procesado");
    }
}
