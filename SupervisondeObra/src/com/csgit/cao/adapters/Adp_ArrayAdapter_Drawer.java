package com.csgit.cao.adapters;

import java.util.List;

import com.csgit.cao.R;
import com.csgit.cao.model.Mod_Item_Drawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adp_ArrayAdapter_Drawer extends ArrayAdapter<Mod_Item_Drawer>{

	private Context context;
	private List<Mod_Item_Drawer> listItems;
	private int layoutResourceID;
	
	public Adp_ArrayAdapter_Drawer(Context context, int layoutResourceID, List<Mod_Item_Drawer> listItems){
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.listItems = listItems;
		this.layoutResourceID = layoutResourceID;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		DrawerItemHolder drawerHolder;
        View view = convertView;
        
        if(view ==  null){
        	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResourceID, parent, false);
            drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

            view.setTag(drawerHolder);
        }else{
        	  drawerHolder = (DrawerItemHolder) view.getTag();
        }
        
        Mod_Item_Drawer dItem = (Mod_Item_Drawer) this.listItems.get(position);
        
        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getIdResource()));
        drawerHolder.ItemName.setText(dItem.getItemName());
    
		return view;
	}
	
	private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
  }
}
