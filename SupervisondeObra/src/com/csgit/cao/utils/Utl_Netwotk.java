package com.csgit.cao.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utl_Netwotk {
	
	
	public static NetworkInfo getNetworkInfo(Context context){
		ConnectivityManager connMgr = (ConnectivityManager) 
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return connMgr.getActiveNetworkInfo();
	}
}
