package com.csgit.util;

import java.util.Comparator;

import com.csgit.cao.model.Reportsmodel;

public class ReportsComparatorConsecutivo implements Comparator<Reportsmodel> {

	@Override
	public int compare(Reportsmodel o1, Reportsmodel o2) {
		// TODO Auto-generated method stub
		return o1.getIdConsecutivo().compareTo(o2.getIdConsecutivo());
	}

}
