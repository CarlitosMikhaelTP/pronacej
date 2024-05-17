package com.carlitos.Pronacej;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.GraphicsActivitys.GraficoUnoActivity;
import com.carlitos.Pronacej.Model.Sabana;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.SabanaService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SabanaActivity extends AppCompatActivity {

    // Declarar variables
    EditText txtValue, txtSentenciados, txtProcesados, txtCapacidad;
   // Inicando el servicio con los métodos
    SabanaService service;


    // Inicializando variables de tipo Spinner
    Spinner spinnerAdmin, spinnerTableTables, spinnerProcess, spinnerState;

    // Arrays para almacenar las opciones y los IDs correspondientes
    String[] adminOptions = {"Administrador Uno", "Administrador Dos", "Administrador Tres", "Administrador Cuatro"};
    Integer[] adminIds = {1, 2, 3, 4};

    String[] tableOptions = {"Centro Juvenil CJDR: LIMA", "Centro Juvenil SOA: RIMAC", "Centro Juvenil PASPE: PISCO"};
    Integer[] tableIds = {1, 11, 36};

    String[] processOptions = {"Reporte Diario", "Reporte Mensual", "Reporte Anual"};
    Integer[] processIds = {1, 2, 3};

    String[] stateOptions = {"Habilitar", "Deshabilitar"};
    Integer[] stateIds = {1, 2};
    // Incializando botón para generar gráfico
    Button btnGraphic;
    // Inicializando propiedades de librería BarChart
    BarChart barChart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sabana_layout);

        // Asociar vistas
        txtValue = findViewById(R.id.txtValue);
        txtSentenciados = findViewById(R.id.txtSentenciados);
        txtProcesados = findViewById(R.id.txtProcesados);
        txtCapacidad = findViewById(R.id.txtCapacidad);
        btnGraphic = findViewById(R.id.btnGraphic);

        // Inicializar Spinners
        spinnerAdmin = findViewById(R.id.txtAdmin);
        spinnerTableTables = findViewById(R.id.txtTableTables);
        spinnerProcess = findViewById(R.id.txtProcess);
        spinnerState = findViewById(R.id.txtState);

        // Campos label
        TextView labelAdmin = (TextView)findViewById(R.id.labelAdmin);
        TextView labelTable = (TextView)findViewById(R.id.labelTableTables);
        TextView labelProcess = (TextView)findViewById(R.id.labelProcess);
        TextView labelValue = (TextView)findViewById(R.id.labelValue);
        TextView labelState = (TextView) findViewById(R.id.labelState);
        TextView labelCapacidad = (TextView) findViewById(R.id.labelCapacidad);
        TextView labelSentenciados = (TextView) findViewById(R.id.labelSentenciados);
        TextView labelProcesados = (TextView) findViewById(R.id.labelProcesados);
        TextView labelMenores = (TextView) findViewById(R.id.labelMenores);
        TextView labelMayores = (TextView) findViewById(R.id.labelMayores);

        // Campos para insertar info
        EditText txtValue = (EditText) findViewById(R.id.txtValue);
        EditText txtCapacidad = (EditText) findViewById(R.id.txtCapacidad);
        EditText txtSentenciados = (EditText) findViewById(R.id.txtSentenciados);
        EditText txtProcesados = (EditText) findViewById(R.id.txtProcesados);
        EditText txtMenores = (EditText) findViewById(R.id.txtMenores);
        EditText txtMayores = (EditText) findViewById(R.id.txtMayores);

        // Butones
        Button btnSave = (Button)findViewById(R.id.btnSave);
        Button btnGraphic = (Button)findViewById(R.id.btnGraphic);

        // Inicializando el servicio
        service = Apis.getSabanaService();

        // LLamando al método que cargará los valores del Spinner
        loadSpinnerData();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sabana s = new Sabana();
                s.setAdminId(getSelectedAdminId());
                s.setTableTablesId(getSelectedTableId());
                s.setProcessHeaderId(getSelectedProcessId());
                s.setValue(txtValue.getText().toString());
                s.setState(getSelectedState());

                // Llamada a método para agregar Sabana y actualizar la lista
                addSabanaAndUpdateList(s);
            }
        });

        btnGraphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los campos de texto
                String valueText = txtValue.getText().toString();
                String menoresText = txtMenores.getText().toString();
                String mayoresText = txtMayores.getText().toString();

                // Crear un Intent y pasar los valores a ResultadosUnoActivity
                Intent intent = new Intent(SabanaActivity.this, ResultadosUnoActivity.class);
                intent.putExtra("value", valueText);
                intent.putExtra("menores", menoresText);
                intent.putExtra("mayores", mayoresText);
                startActivity(intent);
            }
        });
    }


    private void addSabanaAndUpdateList(Sabana s) {
        service.addSabana(s).enqueue(new Callback<Sabana>() {
            @Override
            public void onResponse(Call<Sabana> call, Response<Sabana> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SabanaActivity.this, "Se registró con éxito", Toast.LENGTH_LONG).show();
                    loadSpinnerData(); // Actualiza la lista
                    Intent intent = new Intent(SabanaActivity.this, GraficoUnoActivity.class);
                    startActivity(intent); // Inicia la actividad después de actualizar la lista
                } else {
                    Toast.makeText(SabanaActivity.this, "Error al registrar", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Sabana> call, Throwable t) {
                Log.e("Error:", t.getMessage());
                Toast.makeText(SabanaActivity.this, "Error al registrar", Toast.LENGTH_LONG).show();
            }
        });
    }



    // Métodos para obtener el ID seleccionado en cada opción desplegable
    private Integer getSelectedAdminId(){
        int selectedIndex = spinnerAdmin.getSelectedItemPosition();
        return adminIds[selectedIndex];
    }

    private Integer getSelectedTableId(){
        int selectedIndex = spinnerTableTables.getSelectedItemPosition();
        return tableIds[selectedIndex];
    }

    private Integer getSelectedProcessId(){
        int selectedIndex = spinnerProcess.getSelectedItemPosition();
        return processIds[selectedIndex];
    }

    private Integer getSelectedState(){
        int selectedIndex = spinnerState.getSelectedItemPosition();
        return stateIds[selectedIndex];
    }


    // Método para insertar registros de Sábana
    public void addSabana(Sabana s){
        service = Apis.getSabanaService();
        Call<Sabana>call=service.addSabana(s);
        call.enqueue(new Callback<Sabana>() {
            @Override
            public void onResponse(Call<Sabana> call, Response<Sabana> response) {
                if (response != null){
                    Toast.makeText(SabanaActivity.this, "Se registro con éxito", Toast.LENGTH_LONG).show();
                    loadSpinnerData();
                }
            }

            @Override
            public void onFailure(Call<Sabana> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
        Intent intent = new Intent(SabanaActivity.this, GraficoUnoActivity.class);
        startActivity(intent);

    }

    // Método para cargar las opciones en los Spinner
    public void loadSpinnerData(){

        // Configurar adaptadores para los Spinners
        ArrayAdapter<String> adminAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, adminOptions);
        ArrayAdapter<String> tableAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tableOptions);
        ArrayAdapter<String> processAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, processOptions);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateOptions);

        // Asignar adaptadores a los Spinners
        spinnerAdmin.setAdapter(adminAdapter);
        spinnerTableTables.setAdapter(tableAdapter);
        spinnerProcess.setAdapter(processAdapter);
        spinnerState.setAdapter(stateAdapter);
    }


}
