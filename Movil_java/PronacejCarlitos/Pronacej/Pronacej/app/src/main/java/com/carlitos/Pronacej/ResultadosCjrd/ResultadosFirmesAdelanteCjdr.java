package com.carlitos.Pronacej.ResultadosCjrd;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosFirmesAdelanteCjdr extends AppCompatActivity {
    private int firmes_aplica;
    private int firmes_no_aplica;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_firme_cjdr);

        firmes_aplica = getIntent().getIntExtra("firmes_aplica", 0);
        firmes_no_aplica = getIntent().getIntExtra("firmes_no_aplica", 0);

        int totalFirmes = firmes_aplica + firmes_no_aplica;

        // Calcular los porcentajes
        double porcentajeAplica = (double) firmes_aplica / totalFirmes * 100;
        double porcentajeNoAplica = (double) firmes_no_aplica / totalFirmes * 100;

        LineChart lineChart = findViewById(R.id.lineChart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, firmes_aplica));
        entries.add(new Entry(1, firmes_no_aplica));

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.Pronacej6));
        colors.add(getResources().getColor(R.color.Pronacej2));


        LineDataSet dataSet = new LineDataSet(entries, "Firmes");
        dataSet.setColors(colors);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);

        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(new String[]{"Aplica", "No Aplica"}));

        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(12f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        lineChart.invalidate();

        ((TextView) findViewById(R.id.textViewfirmes_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeAplica));
        ((TextView) findViewById(R.id.textViewfirmes_aplica)).setText("Aplica");

        ((TextView) findViewById(R.id.textViewfirmes_no_aplicaPorcentaje)).setText(String.format("%.2f%%", porcentajeNoAplica));
        ((TextView) findViewById(R.id.textViewfirmes_no_aplica)).setText("No aplica");
    }
}
