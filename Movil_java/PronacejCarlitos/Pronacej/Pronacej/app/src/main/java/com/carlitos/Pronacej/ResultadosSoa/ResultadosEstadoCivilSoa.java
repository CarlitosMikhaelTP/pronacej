package com.carlitos.Pronacej.ResultadosCjrd;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultadosEstadoCivilCjdr extends AppCompatActivity {

    private int estado_civil_casado;
    private int estado_civil_conviviente;
    private int estado_civil_separado;
    private int estado_civil_soltero;
    private int estado_civil_viudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_estado_civil_cjdr);

        // Obtener los valores de estado civil
        Intent intent = getIntent();
        estado_civil_casado = intent.getIntExtra("estado_civil_casado", 0);
        estado_civil_conviviente = intent.getIntExtra("estado_civil_conviviente", 0);
        estado_civil_separado = intent.getIntExtra("estado_civil_separado", 0);
        estado_civil_soltero = intent.getIntExtra("estado_civil_soltero", 0);
        estado_civil_viudo = intent.getIntExtra("estado_civil_viudo", 0);

        // Crear una lista de entradas de datos para el gráfico de pastel
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(estado_civil_casado, "Casado"));
        entries.add(new PieEntry(estado_civil_conviviente, "Conviviente"));
        entries.add(new PieEntry(estado_civil_separado, "Separado"));
        entries.add(new PieEntry(estado_civil_soltero, "Soltero"));
        entries.add(new PieEntry(estado_civil_viudo, "Viudo"));

        // Crear un conjunto de datos para el gráfico de pastel
        PieDataSet dataSet = new PieDataSet(entries, "Estado Civil");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        // Configurar el gráfico de pastel
        PieChart chart = findViewById(R.id.pieChart);
        chart.getDescription().setEnabled(false);

        // Crear una instancia de PieData y configurarla con el conjunto de datos
        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.invalidate(); // Refrescar el gráfico
    }
}
