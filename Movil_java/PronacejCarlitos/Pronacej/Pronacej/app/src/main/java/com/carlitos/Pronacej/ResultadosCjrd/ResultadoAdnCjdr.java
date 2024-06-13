package com.carlitos.Pronacej.ResultadosCjrd;

import android.os.Bundle;
import android.widget.TextView;

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

public class ResultadoAdnCjdr extends AppCompatActivity {

    private int adn_si;
    private int adn_no;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_adn_cjdr);
        TextView txtSummary = findViewById(R.id.textView28);

        // Obtener los datos de la actividad anterior
        adn_si = getIntent().getIntExtra("adn_si", 0);
        adn_no = getIntent().getIntExtra("adn_no", 0);

        // Calcular el total
        int total = adn_si + adn_no;

        // Calcular los porcentajes
        double por_si = (total != 0) ? (adn_si * 100.0 / total) : 0;
        double por_no = (total != 0) ? (adn_no * 100.0 / total) : 0;

        // Configurar el TextView con los valores de adn_si y adn_no


        // Configurar el TextView con el resumen
        String summaryText = String.format(
                "El gráfico muestra que existe un %.2f%% (%d) de personas que NO tienen ADN del familiar mientras que el resto %.2f%% (%d) tienen ADN familiar.",
                por_si, adn_si, por_no, adn_no
        );
        txtSummary.setText(summaryText);

        // Configurar el gráfico de barras
        BarChart barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawGridBackground(false);

        // Crear las entradas para el gráfico de barras
        List<BarEntry> entriesSi = new ArrayList<>();
        List<BarEntry> entriesNo = new ArrayList<>();
        entriesSi.add(new BarEntry(0, adn_si));
        entriesNo.add(new BarEntry(1, adn_no));

        // Crear el conjunto de datos del gráfico de barras
        BarDataSet dataSetSi = new BarDataSet(entriesSi, "NO ADN");
        dataSetSi.setColor(getResources().getColor(android.R.color.holo_green_light));
        dataSetSi.setValueTextSize(12f);

        BarDataSet dataSetNo = new BarDataSet(entriesNo, "ADN");
        dataSetNo.setColor(getResources().getColor(android.R.color.holo_red_light));
        dataSetNo.setValueTextSize(12f);

        // Configurar ejes y leyenda
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int intValue = Math.round(value);
                if (intValue == 0) {
                    return "Sí";
                } else {
                    return "No";
                }
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(true); // Habilitar la leyenda
        legend.setForm(Legend.LegendForm.SQUARE); // Forma de la leyenda
        legend.setTextSize(12f); // Tamaño del texto de la leyenda
        legend.setTextColor(getResources().getColor(android.R.color.black)); // Color del texto de la leyenda

        // Agregar los datos al gráfico de barras
        BarData data = new BarData(dataSetSi, dataSetNo);
        barChart.setData(data);
        barChart.invalidate(); // Refrescar el gráfico
    }
}