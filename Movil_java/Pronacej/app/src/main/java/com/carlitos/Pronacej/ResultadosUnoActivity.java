package com.carlitos.Pronacej;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
C:\Users\HP\Downloads\Pronacej (1).zip\Pronacej\app\src\main\res\drawable

public class ResultadosUnoActivity extends AppCompatActivity {

    // Declarar variables del gráfico
    private BarChart barChart;

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_uno);



        // Recibir los valores pasados a través del Intent con grafico de barras
        Intent intent = getIntent();
        String valueText = intent.getStringExtra("value");
        String menoresText = intent.getStringExtra("menores");
        String mayoresText = intent.getStringExtra("mayores");

        // Convertir los valores a números enteros
        int value = Integer.parseInt(valueText);
        int menores = Integer.parseInt(menoresText);
        int mayores = Integer.parseInt(mayoresText);

        // Calcular otros valores necesarios para el gráfico
        int totalJovenes = menores + mayores;
        float porcentajeMenores = (float) menores / totalJovenes * 100;
        float porcentajeMayores = (float) mayores / totalJovenes * 100;

        // Configurar el gráfico utilizando MPAndroidChart
        BarChart barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, porcentajeMenores));
        entries.add(new BarEntry(1f, porcentajeMayores));

        BarDataSet barDataSet = new BarDataSet(entries, "Porcentaje de Jóvenes");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);

        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.getDescription().setText("Porcentaje de Menores y Mayores");
        barChart.animateY(2000);
        barChart.invalidate();
    }



}
