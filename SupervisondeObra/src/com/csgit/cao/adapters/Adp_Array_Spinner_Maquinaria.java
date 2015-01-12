package com.csgit.cao.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csgit.cao.model.communicationchannel.model.Maquinaria;

public class Adp_Array_Spinner_Maquinaria extends ArrayAdapter<Maquinaria>{
	private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<Maquinaria> values;

    public Adp_Array_Spinner_Maquinaria(Context context, int textViewResourceId,
    		ArrayList<Maquinaria> categoria) {
        super(context, textViewResourceId, categoria);
        this.context = context;
        this.values = categoria;
    }


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.size();
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public Maquinaria getItem(int position) {
		// TODO Auto-generated method stub
		return values.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 TextView label = new TextView(context);
	        label.setTextColor(Color.BLACK);
	        // Then you can get the current item using the values array (Users array) and the current position
	        // You can NOW reference each method you has created in your bean object (User class)
	        label.setText(values.get(position).getDescripcion());
	        label.setTextSize(18);
	        // And finally return your dynamic (or custom) view for each spinner item
	        return label;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(values.get(position).getDescripcion());

        return label;
	}
}
