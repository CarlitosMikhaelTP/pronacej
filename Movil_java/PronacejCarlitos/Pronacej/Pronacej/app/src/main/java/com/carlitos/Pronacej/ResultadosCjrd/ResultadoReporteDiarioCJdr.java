package com.carlitos.Pronacej.ResultadosCjrd;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultadoReporteDiarioCJdr extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_reporte_diario_cjdr);

        pieChart = findViewById(R.id.pieChart);

        // Obtener los datos pasados desde la actividad anterior
        ArrayList<HashMap<String, String>> reportData = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("reportData");

        // Crear los objetos PieEntry a partir de los datos
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (HashMap<String, String> data : reportData) {
            String nombreCentro = data.get("centro_cjdr");
            float poblacion = Float.parseFloat(data.get("poblacion_cjdr"));
            String descripcion = nombreCentro + ": " + poblacion;
            entries.add(new PieEntry(poblacion, descripcion));
        }

        // Crear el conjunto de datos para el gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "Población por Centro Juvenil");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(R.color.black);

        // Crear los datos del gráfico de pastel
        PieData pieData = new PieData(dataSet);

        // Configurar la leyenda
        pieChart.getLegend().setEnabled(true);
        pieChart.getLegend().setTextSize(12f);

        // Configurar descripción del gráfico
        pieChart.getDescription().setEnabled(false);

        // Establecer los datos en el gráfico y refrescar
        pieChart.setData(pieData);
        pieChart.invalidate(); // refrescar
    }
}
