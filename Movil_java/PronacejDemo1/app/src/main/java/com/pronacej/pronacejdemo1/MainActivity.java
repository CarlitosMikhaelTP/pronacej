package com.pronacej.pronacejdemo1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private BarChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lineChart();
        barChart();
        pieChart();
        scatterChart();
        radarChart();
    }

    //
    public ArrayList<CentroJuvenil> data(){
        ArrayList<CentroJuvenil> data = new ArrayList<CentroJuvenil>();

        // Agregar los centros juveniles a la lista
        data.add(new CentroJuvenil(1, "CJ LIMA", 1, 1));
        data.add(new CentroJuvenil(2, "CJ SANTA MARGARITA", 1, 1));
        data.add(new CentroJuvenil(3, "CJ ALFONSO UGARTE", 1, 1));
        data.add(new CentroJuvenil(4, "CJ JOSE QUIÑONES", 1, 1));
        data.add(new CentroJuvenil(5, "CJ MARCAVALLE", 1, 1));
        data.add(new CentroJuvenil(6, "CJ MIGUEL GRAU", 1, 1));
        data.add(new CentroJuvenil(7, "CJ PUCALLPA", 1, 1));
        data.add(new CentroJuvenil(8, "CJ TRUJILLO", 1, 1));
        data.add(new CentroJuvenil(9, "SOA RIMAC", 2, 1));
        data.add(new CentroJuvenil(10, "SOA TUMBES", 2, 1));
        data.add(new CentroJuvenil(11, "SOA HUAURA", 2, 1));
        data.add(new CentroJuvenil(12, "SOA CAÑETE", 2, 1));
        data.add(new CentroJuvenil(13, "SOA IQUITOS", 2, 1));
        data.add(new CentroJuvenil(14, "SOA ICA", 2, 1));
        data.add(new CentroJuvenil(15, "SOA AREQUIPA", 2, 1));
        data.add(new CentroJuvenil(16, "SOA LIMA NORTE", 2, 1));
        data.add(new CentroJuvenil(17, "SOA LIMA ESTE", 2, 1));
        data.add(new CentroJuvenil(18, "SOA CHICLAYO", 2, 1));
        data.add(new CentroJuvenil(19, "SOA TRUJILLO", 2, 1));
        return data;
    }
    public void lineChart(){
        LineChart chart = findViewById(R.id.lineChart);
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 1f));
        entries.add(new Entry(1f, 3f));
        entries.add(new Entry(2f, 2f));




        LineDataSet dataSet = new LineDataSet(entries, "Label");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }
    public void barChart(){
        // Obtener los datos de los centros juveniles
        ArrayList<CentroJuvenil> data = data();
        // Lista de valores en el eje X del gráfico
        List<String> xValues = Arrays.asList("CJR","SOAS");
        // Calcular la suma de centros juveniles de tipo 1 y tipo 2
        int sumaTipo1 = 0;
        int sumaTipo2 = 0;
        for (CentroJuvenil centro : data) {
            if (centro.campo1 == 1) {
                sumaTipo1++;
            }else if (centro.campo1 == 2) {
                sumaTipo2++;
            }
        }
        // Configurar el gráfico de barras
        BarChart chart = findViewById(R.id.barChart); // Obtener la referencia al gráfico desde el diseño XML
        chart.getAxisRight().setDrawLabels(false); // No mostrar etiquetas en el eje derecho
        // Añadir la entradas
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, sumaTipo1));
        entries.add(new BarEntry(1, sumaTipo2));
        entries.add(new BarEntry(1, sumaTipo2));
        entries.add(new BarEntry(1, sumaTipo2));
        entries.add(new BarEntry(1, sumaTipo2));

        //
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisLineWidth(2f);
        yAxis.setLabelCount(10);

        // Crear el conjunto de datos de barras
        BarDataSet dataSet = new BarDataSet(entries, "Centros Juveniles");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS); // Establecer colores para las barras

        // Configurar los datos del gráfico de barras
        BarData barData = new BarData(dataSet);
        chart.setData(barData); // Establecer los datos en el gráfico
        chart.invalidate(); // Actualizar el gráfico

        // Configurar el formato de los valores en el eje X
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM); // Posicionar el eje X en la parte inferior
        chart.getXAxis().setGranularity(1f); // Establecer la distancia mínima entre etiquetas en el eje X
        chart.getXAxis().setGranularityEnabled(true); // Habilitar la granularidad en el eje X

    }
    public void pieChart(){
        PieChart chart = findViewById(R.id.pieChart);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(1f, "Label 1"));
        entries.add(new PieEntry(2f, "Label 2"));
        entries.add(new PieEntry(3f, "Label 3"));
        PieDataSet dataSet = new PieDataSet(entries, "Label");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.invalidate(); // refresh

    }
    public void scatterChart(){
        ScatterChart chart = findViewById(R.id.scatterChart);
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 1f));
        entries.add(new Entry(1f, 3f));
        entries.add(new Entry(2f, 2f));
        ScatterDataSet dataSet = new ScatterDataSet(entries, "Label");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        ScatterData scatterData = new ScatterData(dataSet);
        chart.setData(scatterData);
        chart.invalidate(); // refresh
    }
    public void radarChart(){

        RadarChart chart = findViewById(R.id.radarChart);
        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(1f));
        entries.add(new RadarEntry(2f));
        entries.add(new RadarEntry(3f));
        RadarDataSet dataSet = new RadarDataSet(entries, "Label");
        RadarData radarData = new RadarData(dataSet);
        chart.setData(radarData);
        chart.invalidate(); // refresh
    }
}