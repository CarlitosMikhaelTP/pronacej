package com.carlitos.Pronacej;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.carlitos.Pronacej.Model.Sabana;

import java.util.List;

public class SabanaAdapter extends ArrayAdapter<Sabana> {

    private Context context;
    private List<Sabana> sabanas;

    public SabanaAdapter(@NonNull Context context, int resource, @NonNull List<Sabana> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sabanas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.content_option1, parent, false);


        //TextView txtId = (TextView) rowView.findViewById(R.id.id);
        //TextView txtIdAdmin = (TextView) rowView.findViewById(R.id.idAdmin);
        TextView txtIdTableTables = (TextView) rowView.findViewById(R.id.idTableTables);
        TextView txtIdProcessHeader = (TextView) rowView.findViewById(R.id.idProcessHeader);
        TextView txtValue = (TextView) rowView.findViewById(R.id.value);
        //TextView txtState = (TextView) rowView.findViewById(R.id.state);

        //txtId.setText(String.format("ID:%s", sabanas.get(position).getId()));
        //txtIdAdmin.setText(String.format("IdAdmin:%s",sabanas.get(position).getAdminId()));
        txtIdTableTables.setText(String.format("IdTableTables:%s",sabanas.get(position).getTableTablesId()));
        txtIdProcessHeader.setText(String.format("IdProcessHeader:%s",sabanas.get(position).getProcessHeaderId()));
        txtValue.setText(String.format("Value:%s",sabanas.get(position).getValue()));
        //txtState.setText(String.format("State:%s",sabanas.get(position).getState()));

        // Implementar el método para actualizar los datos
        return rowView;
    }


}