package com.csgit.cao.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.Mod_Documentos;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Uri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class Bus_Documentos {

	private Context context;
	private Bus_Sync bus_sync;
	
	public Bus_Documentos(Context context){
		this.context = context;
		bus_sync = new Bus_Sync(context);
	}
	
	public Uri insertDocumento(Mod_Documentos documento){
		
		ContentValues values = new ContentValues();

		values.put(ConstantesBD.ColDocumentosObra[1], documento.getIdObra());
		values.put(ConstantesBD.ColDocumentosObra[2], documento.getPathDocumento());
		values.put(ConstantesBD.ColDocumentosObra[3], documento.getFormato());
		values.put(ConstantesBD.ColDocumentosObra[4], documento.getFecha());
		values.put(ConstantesBD.ColDocumentosObra[5], documento.getNombreDocumento());
		values.put(ConstantesBD.ColDocumentosObra[6], documento.getMimeType());
		values.put(ConstantesBD.ColDocumentosObra[9], documento.getIsSync());
		values.put(ConstantesBD.ColDocumentosObra[10], documento.getTipoArchivo());

		Uri uri  = context.getContentResolver().insert(Uri.parse(ConstantesBD.CAO_URL +
				ConstantesBD.nomTablaDocumentosObra), values);

		// temporal
		long idSync = Utl_Uri.getIdInsert(uri);
		ContentValues valTemp = new ContentValues();
		valTemp.put(ConstantesBD.colSync[1], Utl_Constantes.TABLA_DOCUMENTOS_OBRA);
		valTemp.put(ConstantesBD.colSync[2], idSync);
		valTemp.put(ConstantesBD.colSync[3], Utl_Constantes.ACC_INSERT);
		valTemp.put(ConstantesBD.colSync[4], Utl_Constantes.REG_NO_SINC);
		context.getContentResolver().insert(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaSync), valTemp);
		
		return uri;
	}

	public List<Mod_Documentos> getDocumentosFromObra(long idObra){
		List<Mod_Documentos> lista = new ArrayList<Mod_Documentos>();
		Cursor cursor = context.getContentResolver().query
				(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaDocumentosObra),
						ConstantesBD.ColDocumentosObra, 
						ConstantesBD.ColDocumentosObra[1] + " = " + idObra, null, null);
		
		while(cursor.moveToNext()){
			Mod_Documentos documento = new Mod_Documentos();
			documento.setIdDocumento(cursor.getLong(0));
			documento.setIdObra(cursor.getLong(1));
			documento.setPathDocumento(cursor.getString(2));
			documento.setFormato(cursor.getString(3));
			documento.setFecha(cursor.getString(4));
			documento.setNombreDocumento(cursor.getString(5));
			documento.setMimeType(cursor.getString(6));
			documento.setIsSync(cursor.getInt(9));
			
			lista.add(documento);
		}
		
		cursor.close();
		
		return lista;
	}
	
	public Mod_Documentos getDocumento(long idDocumento){
		Mod_Documentos documento = new Mod_Documentos();
		Cursor cursor = context.getContentResolver().query
				(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaDocumentosObra),
						ConstantesBD.ColDocumentosObra, 
						ConstantesBD.ColDocumentosObra[0] + " = " + idDocumento, null, null);
		
		if(cursor.moveToFirst()){
			
			documento.setIdDocumento(cursor.getLong(0));
			documento.setIdObra(cursor.getLong(1));
			documento.setPathDocumento(cursor.getString(2));
			documento.setFormato(cursor.getString(3));
			documento.setFecha(cursor.getString(4));
			documento.setNombreDocumento(cursor.getString(5));
			documento.setMimeType(cursor.getString(6));
			documento.setUrlServer(cursor.getString(7));
			documento.setBlobKey(cursor.getString(8));
			documento.setTipoArchivo(cursor.getInt(10));
		}
		
		cursor.close();
		
		return documento;
	}

	public int updateDocumento(long idDocumento, ContentValues values){
		int val = context.getContentResolver().update(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaDocumentosObra), values, 
				ConstantesBD.ColDocumentosObra[0] + " = " + idDocumento, null);
		return val;
	}
	
	public int deleteDocumento(long idDocumento, String path){
		int op = 0;
		
		int i = context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + 
				ConstantesBD.nomTablaDocumentosObra), 
				ConstantesBD.ColDocumentosObra[0] + " = " + idDocumento, null);
		if(i != 0){
			op = bus_sync.deleteSync(Utl_Constantes.TABLA_DOCUMENTOS_OBRA, idDocumento);
			
//			Borrar archivo
			File file = new File(path);
			file.delete();
		}
		
		return op;
	}
}
