package com.csgit.util;

import java.util.Comparator;
import java.util.Date;

import com.csgit.cao.model.Concepto;

public class ConceptoComparatorInicio implements Comparator<Concepto> {

	@Override
	public int compare(Concepto o1, Concepto o2) {
		// TODO Auto-generated method stub

		return o1.getFecha_Inicio().compareTo(o2.getFecha_Inicio());
	}
}
