package com.csgit.cao.dao;

import java.util.List;

import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.controllers.Frag_Obras;
import com.csgit.cao.model.Mod_Obras;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

public class Dao_ContentObserver extends ContentObserver{

	private Context context;
	
	public Dao_ContentObserver(Handler handler, Context context) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@SuppressLint("NewApi")
	@Override
	public void onChange(boolean selfChange, Uri uri) {
		// TODO Auto-generated method stub
//		super.onChange(selfChange, uri);
		Log.i("Observer", "Cambio la BD: " + uri.toString());
		
		String[] aux = uri.toString().split("/");
		String entidad = aux[3];
		
		if(entidad.equals(ConstantesBD.nomTablaObra)){
//			obtenerObras();
		}
	}
	
//	public void obtenerObras(){
//		Bus_Obras buss_obras = new Bus_Obras(context.getContentResolver());
//		List<Mod_Obras> obras = buss_obras.getObras();
//		Frag_Obras.mostrarNuevasObras(obras, context);
//	}
}
