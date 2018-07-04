package com.pets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Adapterr extends ArrayAdapter<Data> {


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Data da = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView name = convertView.findViewById(R.id.name);
        name.setText(da.getName());

        TextView family = convertView.findViewById(R.id.family);
        family.setText(da.getFamily());

        TextView sex = convertView.findViewById(R.id.sex);
        sex.setText(da.getSex());

        TextView poid = convertView.findViewById(R.id.poid);
        poid.setText(da.getPoids());


        return convertView;
    }
}
