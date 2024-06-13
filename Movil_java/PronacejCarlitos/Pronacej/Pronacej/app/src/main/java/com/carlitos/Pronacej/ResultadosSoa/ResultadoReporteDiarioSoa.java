package com.carlitos.Pronacej.ResultadosSoa;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultadoReporteDiarioSoa extends AppCompatActivity {

    private HorizontalBarChart horizontalBarChart;
    private TextView legendTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_reporte_diario_soa);

        horizontalBarChart = findViewById(R.id.horizontalBarChart);

        ArrayList<HashMap<String, String>> reportData = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("reportData");

        if (reportData != null) {
            mostrarGrafico(reportData);
        } else {
            Log.e("DATA_ERROR", "No se recibieron datos para el gráfico");
        }
    }

    private void mostrarGrafico(ArrayList<HashMap<String, String>> reportData) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < reportData.size(); i++) {
            HashMap<String, String> entry = reportData.get(i);
            String centroSoa = entry.get("centro_soa");
            float poblacionSoa = Float.parseFloat(entry.get("poblacion_soa"));
            entries.add(new BarEntry(i, poblacionSoa));
            labels.add(centroSoa);
        }

        BarDataSet dataSet = new BarDataSet(entries, "Población por Centro SOA");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        horizontalBarChart.setData(barData);
        horizontalBarChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < labels.size()) {
                    return labels.get(index);
                } else {
                    return "";
                }
            }
        });
        horizontalBarChart.invalidate(); // refresh
    }
}
