package com.csgit.cao.utils;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.csgit.cao.model.Mod_BlobStore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@SuppressWarnings("deprecation")
public class Utl_HttpClient {

	public static HttpClient HttpClient(){
		HttpParams httpParams = new BasicHttpParams();
		int timeOutConnection = 50000;
		HttpConnectionParams.setConnectionTimeout(httpParams, timeOutConnection);
		int timeOutSocket = 50000;
		HttpConnectionParams.setSoTimeout(httpParams, timeOutSocket);
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		return httpClient;
	}
	
	public String getUrlBlobStore(String url){
		String data;
		HttpClient httpClient = Utl_HttpClient.HttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			data = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			// TODO: handle exception
			data = null;
		}
		return data;
	}
	
	public Mod_BlobStore setMultimedia(String urlBlobStore, String path, String idReferencia, 
			String tipoArchivo, String formato, String tipoReferencia, String descripcion, String mimeType) throws UnsupportedEncodingException{
		
		Type arrayListType = new TypeToken<Mod_BlobStore>(){}.getType();
		Gson gson = new Gson();
		Mod_BlobStore blobStore = new Mod_BlobStore();
		FileBody fileBody;
		
		HttpClient httpClient = Utl_HttpClient.HttpClient();
		HttpPost httpPost = new HttpPost(urlBlobStore);
		
		MultipartEntity multipartEntity = new MultipartEntity();
		if(mimeType.isEmpty())
			fileBody = new FileBody(new File(path), ContentType.create(Utl_Imagen.getMimeType(path)), Utl_Imagen.getNombreImagen(path));
		else
			fileBody = new FileBody(new File(path), ContentType.create(mimeType), Utl_Imagen.getNombreImagen(path));
		
		Log.i("ContentType", fileBody.getContentType().toString());
		
		multipartEntity.addPart(Utl_Constantes.MULTIPART_FILE, fileBody);
		multipartEntity.addPart(Utl_Constantes.MULTIPART_ID_REFERENCIA, new StringBody(idReferencia));
		multipartEntity.addPart(Utl_Constantes.MULTIPART_TIPO_ARCHIVO, new StringBody(tipoArchivo));
		multipartEntity.addPart(Utl_Constantes.MULTIPART_FORMATO, new StringBody(formato));
		multipartEntity.addPart(Utl_Constantes.MULTIPART_TIPO_REFERENCIA, new StringBody(tipoReferencia));
		multipartEntity.addPart(Utl_Constantes.MULTIPART_DESCRIPCION, new StringBody(descripcion));
		
		httpPost.setEntity(multipartEntity);
		
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			Reader reader = new InputStreamReader(entity.getContent());
			blobStore = gson.fromJson(reader, arrayListType);
		} catch (Exception e) {
			// TODO: handle exception
			blobStore = null;
		}
		
		return blobStore;
	}
}
