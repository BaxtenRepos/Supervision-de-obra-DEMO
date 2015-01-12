package com.csgit.cao.utils;

import android.net.Uri;

public class Utl_Uri {

	public static long getIdInsert(Uri uri){
		long id = 0;
		String[] aux = uri.toString().split("/");
		id = Long.parseLong(aux[(aux.length -1)]);
		return id;
	}
}
