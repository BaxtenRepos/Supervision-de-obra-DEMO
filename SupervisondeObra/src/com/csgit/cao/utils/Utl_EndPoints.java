package com.csgit.cao.utils;

import java.io.IOException;

import com.csgit.cao.CloudEndpointUtils;
import com.csgit.cao.model.communicationchannel.Communicationchannel;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

public class Utl_EndPoints {

	/**
	 * Inicializa los endpoints para la conexión al backend
	 */
	public static Communicationchannel initEndPoint(){
		Communicationchannel.Builder builder = new Communicationchannel.Builder(
				AndroidHttp.newCompatibleTransport(), 
				new JacksonFactory(), 
				new HttpRequestInitializer() {
					
					@Override
					public void initialize(HttpRequest arg0) throws IOException {
						// TODO Auto-generated method stub
						
					}
				});
		return CloudEndpointUtils.updateBuilder(builder).build();
	}
}
