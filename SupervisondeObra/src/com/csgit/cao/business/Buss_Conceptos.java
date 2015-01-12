package com.csgit.cao.business;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.csgit.cao.dao.ConstantesBD;
import com.csgit.cao.model.communicationchannel.model.Concepto;

public class Buss_Conceptos {

	public List<Concepto> getConceptos(ContentResolver contentR){
		List<Concepto> lista_conceptos = new ArrayList<Concepto>();
		
		Cursor cursorConceptos = contentR.query(Uri.parse(ConstantesBD.CAO_URL + ConstantesBD.nomTablaConcepto),
				ConstantesBD.ColConceptos, null, null, null);
		while(cursorConceptos.moveToNext()){
			Concepto concepto = new Concepto();
//			concepto.setClave(cursorConceptos.getLong(0));
			concepto.setCantidadAvance(cursorConceptos.getDouble(8));
			
			lista_conceptos.add(concepto);
		}
		
		return lista_conceptos;
	}
}
