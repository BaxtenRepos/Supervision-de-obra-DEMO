package com.csgit.cao.utils;

import java.io.File;
import java.io.IOException;

import com.csgit.cao.dao.ConstantesBD;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class Utl_Base_Datos {
	
	public static void limpiarBaseDatos(Context context){
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaUbicacionObra), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFinanciero), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceFisico), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaDirectorio), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaNota), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaObra), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMinuta), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatPersonal), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoPersonalCatPersonal), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaReporteDiario), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaAvanceConcepto), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaDocumentosObra), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEstimacion), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaMultimedia), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaEmpresas), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaRepoMaquinariaCatMaquinaria), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatMaquinaria), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaCatTipoMaquinaria), null, null);
		context.getContentResolver().delete(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaSync), null, null);
		
	}
	
	public static void borrarDirectorios(){
		String TAG = "RUTAS";
		File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			File fileObras = new File(path + Utl_Constantes.RUTA_PRINCIPAL + Utl_Constantes.RUTA_OBRAS);
			File fileMaquinaria = new File(path + Utl_Constantes.RUTA_PRINCIPAL + Utl_Constantes.RUTA_CAT_MAQUINARIA);
			
			if(fileObras.exists()){
				String deleteCmd = "rm -r " + fileObras;
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec(deleteCmd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, "Error al borrar rutas: " + e.getMessage());
				}
			}
			
			if(fileMaquinaria.exists()){
				String deleteCmd = "rm -r " + fileMaquinaria;
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec(deleteCmd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, "Error al borrar rutas: " + e.getMessage());
				}
			}
		}else{
			Log.d(TAG, "External storage is not mounted READ/WRITE.");
		}
	}
}
