package com.carlitos.Pronacej.GraphicsActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.carlitos.Pronacej.Model.Sabana;
import com.carlitos.Pronacej.R;
import com.carlitos.Pronacej.SabanaActivity;
import com.carlitos.Pronacej.SabanaAdapter;
import com.carlitos.Pronacej.Utils.Apis;
import com.carlitos.Pronacej.Utils.SabanaService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GraficoUnoActivity extends AppCompatActivity {

    SabanaService sabanaService;
    List<Sabana>listaSabana=new ArrayList<Sabana>();
    ListView listView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageone);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.listView);

        sabanaService = Apis.getSabanaService();
        listarSabanas();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GraficoUnoActivity.this, SabanaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void listarSabanas(){
        Call<List<Sabana>>call= sabanaService.getSabana();
        call.enqueue(new Callback<List<Sabana>>() {
            @Override
            public void onResponse(Call<List<Sabana>> call, Response<List<Sabana>> response) {
               if (response.isSuccessful()) {
                   listaSabana = response.body();
                   listView.setAdapter(new SabanaAdapter(GraficoUnoActivity.this, R.layout.content_option1, listaSabana));
               }
            }
            @Override
            public void onFailure(Call<List<Sabana>> call, Throwable t) {
                // Mostrar mensaje de error al usuario
                Snackbar.make(listView, "Error de red: " + t.getMessage(), Snackbar.LENGTH_LONG).show();
                Log.e("Error :", t.getMessage());
            }
        });

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionalItemSelected(MenuItem item){
        int id = item.getItemId();
    }*/


}
