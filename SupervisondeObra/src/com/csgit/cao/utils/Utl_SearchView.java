package com.csgit.cao.utils;

import android.content.Context;
import android.widget.SearchView;

public class Utl_SearchView extends SearchView{

	public Utl_SearchView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onActionViewCollapsed() {
		// TODO Auto-generated method stub
		setQuery("", false);
		super.onActionViewCollapsed();
	}

}
