package com.csgit.cao.business;

public class Demo_Bus {
	public Boolean datos_nulo(String Nombre, String Pass) {

		if (Nombre.length() > 3 && Pass.length() > 3) {
			return true;

		} else {
			return false;
		}

	}
}
