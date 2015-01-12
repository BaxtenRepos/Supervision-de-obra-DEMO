package com.csgit.cao.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.csgit.cao.business.Bus_Maquinaria;
import com.csgit.cao.model.communicationchannel.Communicationchannel;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseMultimedia;
import com.csgit.cao.model.communicationchannel.model.Maquinaria;
import com.csgit.cao.model.communicationchannel.model.Multimedia;

public class Utl_Imagen{
	
	/**
	 * Procesa la imagen de evidencia antes de ser guardado el registro
	 * @param pathImagen
	 */
	public static void procesarImagen(String pathImagen){
	    Bitmap bit = Utl_Imagen.optimizaImagen(pathImagen);
		try {
			File fileImage = new File(pathImagen);
			FileOutputStream outputStream = new FileOutputStream(fileImage);
			bit.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("Desc. Evidencia", "Error: " + e);
		}
	}
	
	/**
	 * Descarga las imagenes del blobstore
	 * @param lista lista de imagenes que se tienen que descargar
	 */
	public static void getFileBlobStore(List<?> lista, int tipoEntidad, Context context){
		String TAG = "Descarga";
		List<?> datos = null;
		Bus_Maquinaria bus_maquinaria = new Bus_Maquinaria(context);
		
		try {
			switch (tipoEntidad) {
//			case Constantes.REF_DOCUMENTOS:
//				datos = new ArrayList<Mod_Documentos>();
//				datos = lista;
//				Multimediaendpoint multiEndPoint = null;
//				Multimediaendpoint.Builder multiBuider = new Multimediaendpoint.Builder(
//						AndroidHttp.newCompatibleTransport(),
//						new JacksonFactory(), 
//						new HttpRequestInitializer() {
//							
//							@Override
//							public void initialize(HttpRequest arg0) throws IOException {
//								// TODO Auto-generated method stub
//								
//							}
//						});
//				multiEndPoint = CloudEndpointUtils.updateBuilder(multiBuider).build();
//
//				for (Object object : datos) {
//					Mod_Documentos maquinaria = (Mod_Documentos) object;
//					CollectionResponseMultimedia multimedia = multiEndPoint.listMultimedia(Long.parseLong(
//							String.valueOf(Constantes.REF_CAT_MAQUINARIA)), maquinaria.getIdMaquinaria(),
//							Long.parseLong(String.valueOf(Constantes.TIPO_IMAGEN))).execute();
//					
//					List<Multimedia> listaDatos = multimedia.getItems();
//					for (Multimedia multimedia2 : listaDatos) {
//						String blobKey = multimedia2.getPath();
//						
//						URL url = new URL(Constantes.URL_GET_BLOB_STORE + blobKey); // Cambiar
//						String suffix = getFormatoString(Integer.parseInt(multimedia2.getFormato().toString()));
//						File file = crearArchivoFromBlobStore(Constantes.ARCH_CAT_MAQUINARIA, Long.valueOf("0"), context, suffix);
//						URLConnection urlConn = url.openConnection();
//						InputStream is = urlConn.getInputStream();
//						BufferedInputStream bis = new BufferedInputStream(is);
//						ByteArrayBuffer baf = new ByteArrayBuffer(50);
//						int current = 0;
//						while((current = bis.read()) != -1){
//							baf.append((byte) current);
//						}
////						Convertir los Bytes leídos en un String
//						FileOutputStream fos = new FileOutputStream(file);
//						fos.write(baf.toByteArray());
//						fos.close();
//						
////						Actualizar tabla catMaquinaria
//						Bus_Maquinaria bus_maquinaria = new Bus_Maquinaria(context.getContentResolver());
//						int val = bus_maquinaria.updatePathImagenMaquinaria(multimedia2.getIdReferencia(), file.toString());
//						
//						Log.i(TAG, "Imagen Obtenida Correctamente: " + val);
//					}
//
//				}
//				break;
				
			case Utl_Constantes.REF_CAT_MAQUINARIA:
				datos = new ArrayList<Maquinaria>();
				datos = lista;
				Communicationchannel channel = Utl_EndPoints.initEndPoint();
				for (Object object : datos) {
					Maquinaria maquinaria = (Maquinaria) object;
						CollectionResponseMultimedia multimedia = channel.listMultimedia(Long.parseLong(
								String.valueOf(Utl_Constantes.REF_CAT_MAQUINARIA)), maquinaria.getIdMaquinaria(),
								Long.parseLong(String.valueOf(Utl_Constantes.TIPO_IMAGEN))).execute();
//					CollectionResponseMultimedia mult = channel.listMultimedia(tipoReferencia, idReferencia, tipoArchivo);
						
						List<Multimedia> listaDatos = multimedia.getItems();
						if(listaDatos != null){
							for (Multimedia multimedia2 : listaDatos) {
								String blobKey = multimedia2.getPath();
								
								URL url = new URL(Utl_Constantes.URL_GET_BLOB_STORE + blobKey); // Cambiar
								String suffix = getFormatoString(Integer.parseInt(multimedia2.getFormato().toString()));
								File file = crearArchivoFromBlobStore(Utl_Constantes.ARCH_CAT_MAQUINARIA, Long.valueOf("0"), context, suffix);
								URLConnection urlConn = url.openConnection();
								InputStream is = urlConn.getInputStream();
								BufferedInputStream bis = new BufferedInputStream(is);
								ByteArrayBuffer baf = new ByteArrayBuffer(50);
								int current = 0;
								while((current = bis.read()) != -1){
									baf.append((byte) current);
								}
//								Convertir los Bytes leídos en un String
								FileOutputStream fos = new FileOutputStream(file);
								fos.write(baf.toByteArray());
								fos.close();
								
//								Actualizar tabla catMaquinaria
								int val = bus_maquinaria.updatePathImagenMaquinaria(multimedia2.getIdReferencia(), file.toString());
								
								Log.i(TAG, "Imagen Obtenida Correctamente: " + val);
							}
						}
					
				}
				break;
				
			default:
				break;
			}	
		} catch (Exception e) {
			// TODO: handle exception
			Log.i(TAG, "Error Imagen Cat Maquinaria: " + e.getMessage());
		}

	}
	
	/**
	 * Obtiene tipo de formato en un int.
	 * @param formato extención del archivo
	 * @return int que indica el formato
	 */
	public static int getFormatoInt(String formato){
		int aux = 0;

		switch (formato) {
		case Utl_Constantes.formatJpg:
			aux = Utl_Constantes.idJpg;
			break;
		case Utl_Constantes.formatJpeg:
			aux = Utl_Constantes.idJpeg;
			break;
		case Utl_Constantes.formatPng:
			aux = Utl_Constantes.idPng;
			break;
		case Utl_Constantes.formatAvi:
			aux = Utl_Constantes.idAvi;
			break;
		case Utl_Constantes.formatMp4:
			aux = Utl_Constantes.idMp4;
			break;
		case Utl_Constantes.format3gp:
			aux = Utl_Constantes.id3gp;
			break;
		case Utl_Constantes.formatPdf:
			aux = Utl_Constantes.idPdf;
			break;
		case Utl_Constantes.formatExcel:
		case Utl_Constantes.formatAuxExcel1:
		case Utl_Constantes.formatAuxExcel2:
		case Utl_Constantes.formatAuxExcel3:
			aux = Utl_Constantes.idExcel;
			break;
		case Utl_Constantes.formatWord:
		case Utl_Constantes.formatAuxWord1:
		case Utl_Constantes.formatAuxWord2:
			aux = Utl_Constantes.idWord;
			break;
		case Utl_Constantes.formatPowerPoint:
		case Utl_Constantes.formatAuxPower1:
			aux = Utl_Constantes.idPowerPoint;
			break;
		default:
			break;
		}
		return aux;
	}
	
	/**
	 * Obtiene tipo de formato en un String.
	 * @param formato extención del archivo
	 * @return int que indica el formato
	 */
	public static String getFormatoString(int formato){
		String aux = "";
		switch (formato) {
		case Utl_Constantes.idJpg:
			aux = Utl_Constantes.formatJpg;
			break;
		case Utl_Constantes.idJpeg:
			aux = Utl_Constantes.formatJpeg;
			break;
		case Utl_Constantes.idPng:
			aux = Utl_Constantes.formatPng;
			break;
		case Utl_Constantes.idAvi:
			aux = Utl_Constantes.formatAvi;
			break;
		case Utl_Constantes.idMp4:
			aux = Utl_Constantes.formatMp4;
			break;
		default:
			break;
		}
		return "." + aux;
	}
	
	/**
	 * Obtiene el noembre de la imagen
	 * @param path
	 * @return
	 */
	public static String getNombreImagen(String path){
		String cadena = "";
		String[] aux = path.split(Utl_Constantes.CARAC_SEPARA_RUTAS);
		cadena = aux[aux.length -1]; 
		return cadena;
	}
	
	/**
	 * Obtiene la extecion de una evidencia
	 * @param nombre
	 * @return
	 */
	public static String getExtencionEvidencia(String nombre){
		String[] aux = nombre.split("\\.");
//		String formato = "." + aux[aux.length -1];
		String formato = aux[aux.length -1];
		return formato;
	}
	
	/**
	 * Obtiene el mimetype de un archivo
	 * @param url
	 * @return
	 */
	public static String getMimeType(String url){
	    String type = null;
	    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
	    if (extension != null) {
	        MimeTypeMap mime = MimeTypeMap.getSingleton();
	        type = mime.getMimeTypeFromExtension(extension);
	    }
	    return type;
	}
	
	/**
	 * @param tipoArchivo
	 * @param id_Obra
	 * @param context
	 * @param suffix
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static File crearArchivoFromBlobStore(int tipoArchivo, Long id_Obra, Context context, String suffix){
		
		File file = null, album = null;
		String timeStamp = null;
		String fileName = null;
		String pathGeneral = null;
		String targetPath = null;
		
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			
			timeStamp = new SimpleDateFormat(Utl_Constantes.FORMAT_DATE_EVIDENCIA).format(new Date());
			pathGeneral = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + Utl_Constantes.RUTA_PRINCIPAL;
			
			switch (tipoArchivo) {
			case Utl_Constantes.ARCH_IMAGEN:
				targetPath = pathGeneral + Utl_Constantes.RUTA_OBRAS + Utl_Constantes.CARAC_SEPARA_RUTAS + id_Obra + Utl_Constantes.RUTA_EVIDENCIA;
				album = new File(targetPath);
				album.mkdirs();
				
//					fileName = Constantes.JPEG_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA;
				fileName = Utl_Constantes.JPEG_FILE_PREFIX + timeStamp + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//					fileName = Constantes.JPEG_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//					file = File.createTempFile(fileName, Constantes.JPEG_FILE_SUFFIX, album);
//					file = File.createTempFile(fileName, suffix, album);
				file = new File(album + Utl_Constantes.CARAC_SEPARA_RUTAS + fileName + Utl_Constantes.JPEG_FILE_SUFFIX);
//					file = new File(album + Constantes.CARAC_SEPARA_RUTAS + fileName + Constantes.VIDEO_FILE_SUFFIX);
				break;
			case Utl_Constantes.ARCH_VIDEO:
				
				targetPath = pathGeneral + Utl_Constantes.RUTA_OBRAS + Utl_Constantes.CARAC_SEPARA_RUTAS + id_Obra + Utl_Constantes.RUTA_EVIDENCIA;
				album = new File(targetPath);
				album.mkdirs();
				
//					fileName = Constantes.VIDEO_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA;
				fileName = Utl_Constantes.VIDEO_FILE_PREFIX + timeStamp + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//					file = File.createTempFile(fileName, Constantes.VIDEO_FILE_SUFFIX, album);
//					file = File.createTempFile(fileName, suffix, album);
				file = new File(album + Utl_Constantes.CARAC_SEPARA_RUTAS + fileName + Utl_Constantes.VIDEO_FILE_SUFFIX);
				break;
			case Utl_Constantes.ARCH_MINUTA:
				targetPath = pathGeneral + Utl_Constantes.RUTA_OBRAS + Utl_Constantes.CARAC_SEPARA_RUTAS + id_Obra + Utl_Constantes.RUTA_MINUTAS;
				album = new File(targetPath);
				album.mkdirs();
				
//					fileName = Constantes.JPEG_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA;
				fileName = Utl_Constantes.JPEG_FILE_PREFIX + timeStamp + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//					file = File.createTempFile(fileName, Constantes.JPEG_FILE_SUFFIX, album);
//					file = File.createTempFile(fileName, suffix, album);
				file = new File(album + Utl_Constantes.CARAC_SEPARA_RUTAS + fileName + Utl_Constantes.JPEG_FILE_SUFFIX);
				
				break;
			case Utl_Constantes.ARCH_DOCUMENTO://pendiente
				targetPath = pathGeneral + Utl_Constantes.RUTA_DOCUMENTOS_OBRA;
				
				break;
			case Utl_Constantes.ARCH_CAT_MAQUINARIA:
				targetPath = pathGeneral + Utl_Constantes.RUTA_CAT_MAQUINARIA;
				album = new File(targetPath);
				album.mkdirs();
				
					fileName = Utl_Constantes.JPEG_FILE_PREFIX + timeStamp + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA;
//				fileName = Constantes.JPEG_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//					file = File.createTempFile(fileName, Constantes.JPEG_FILE_SUFFIX, album);
				try {
					file = File.createTempFile(fileName, suffix, album);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				file = new File(album + Constantes.CARAC_SEPARA_RUTAS + fileName + Constantes.JPEG_FILE_SUFFIX);
				
				break;

			default:
				break;
			}
			
//			file.delete();
		}
		return file;
	}
	
	@SuppressLint("SimpleDateFormat")
	public static File crearAchivoEvidencia(boolean tipoArchivo, Long id_Obra, Context context){
		
		File file = null, album = null;
		String timeStamp = null;
		String fileName = null;
		String pathGeneral = null;
		String targetPath = null;
		
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			
			timeStamp = new SimpleDateFormat(Utl_Constantes.FORMAT_DATE_EVIDENCIA).format(new Date());
			pathGeneral = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + Utl_Constantes.RUTA_PRINCIPAL;
			targetPath = pathGeneral + Utl_Constantes.RUTA_OBRAS + Utl_Constantes.CARAC_SEPARA_RUTAS + id_Obra + Utl_Constantes.RUTA_EVIDENCIA;
			
			album = new File(targetPath);
			album.mkdirs();
			
			if(tipoArchivo){ // Imagen
//					fileName = Constantes.JPEG_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA;
				fileName = Utl_Constantes.JPEG_FILE_PREFIX + timeStamp + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//					file = File.createTempFile(fileName, Constantes.JPEG_FILE_SUFFIX, album);
				file = new File(album + Utl_Constantes.CARAC_SEPARA_RUTAS + fileName + Utl_Constantes.JPEG_FILE_SUFFIX);
			}else{ // Video
//					fileName = Constantes.VIDEO_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA;
				fileName = Utl_Constantes.VIDEO_FILE_PREFIX + timeStamp + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//					file = File.createTempFile(fileName, Constantes.VIDEO_FILE_SUFFIX, album);
				file = new File(album + Utl_Constantes.CARAC_SEPARA_RUTAS + fileName + Utl_Constantes.VIDEO_FILE_SUFFIX);
			}
			
			file.delete();
		}
		return file;
	}
	
	@SuppressLint("SimpleDateFormat")
	public static File crearAchivoMinuta(Long id_Obra, Context context){
		
		File file = null, album = null;
		String timeStamp = null;
		String fileName = null;
		String pathGeneral = null;
		String targetPath = null;
		
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			
			timeStamp = new SimpleDateFormat(Utl_Constantes.FORMAT_DATE_EVIDENCIA).format(new Date());
			pathGeneral = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + Utl_Constantes.RUTA_PRINCIPAL;
			targetPath = pathGeneral + Utl_Constantes.RUTA_OBRAS + Utl_Constantes.CARAC_SEPARA_RUTAS + id_Obra + Utl_Constantes.RUTA_MINUTAS;
			
			album = new File(targetPath);
			album.mkdirs();
			
			// Imagen
//				fileName = Constantes.JPEG_FILE_PREFIX + timeStamp + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra + Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA;
			fileName = Utl_Constantes.JPEG_FILE_PREFIX + timeStamp + Utl_Constantes.CARAC_SEPARADOR_NOMBRE_EVIDENCIA + id_Obra;
//				file = File.createTempFile(fileName, Constantes.JPEG_FILE_SUFFIX, album);
			file = new File(album + Utl_Constantes.CARAC_SEPARA_RUTAS + fileName + Utl_Constantes.JPEG_FILE_SUFFIX);
			file.delete();
		}
		return file;
	}
	
	public static Bitmap optimizaImagen(String pathImagen){
		Bitmap aux = BitmapFactory.decodeFile(pathImagen);
		
		// Get current dimensions
	    int width = aux.getWidth();
	    int height = aux.getHeight();

	    int boundBoxInDp = 800;
	    
	    // Determine how much to scale: the dimension requiring less scaling is
	    // closer to the its side. This way the image always stays inside your
	    // bounding box AND either x/y axis touches it.
	    float xScale = ((float) boundBoxInDp) / width;
	    float yScale = ((float) boundBoxInDp) / height;
	    float scale = (xScale <= yScale) ? xScale : yScale;

	    // Create a matrix for the scaling and add the scaling data
	    Matrix matrix = new Matrix();
	    matrix.postScale(scale, scale);
	    
	    // Create a new bitmap and convert it to a format understood by the ImageView
	    Bitmap bit = Bitmap.createBitmap(aux, 0, 0, width, height, matrix, true);
	    
	    return bit;
	}
	
	public static Bitmap badImagen(String pathImagen, int width, int heigth){
		Bitmap unscaledBitmap = BitmapFactory.decodeFile(pathImagen);
		Bitmap scaleBitmap = Bitmap.createScaledBitmap(unscaledBitmap, width, heigth, true);
//		unscaledBitmap.recycle();
		unscaledBitmap = null;
		return scaleBitmap;
	}
	
	public static Bitmap escalarImagen(String pathImagen, int width, int heigth){
		
		// Decode Imagen
		Bitmap unscaledBitmap = decodeResource(pathImagen, width, heigth, 
				ScalingLogic.CROP);
		
		// Scale Imagen
		Bitmap scaledBitmap = createScaledBitmap(unscaledBitmap, width, heigth,
				ScalingLogic.CROP);
		unscaledBitmap.recycle();	
		return scaledBitmap;
	}

	/**
     * Utility function for decoding an image resource. The decoded bitmap will
     * be optimized for further scaling to the requested destination dimensions
     * and scaling logic.
     *
     * @param res The resources object containing the image data
     * @param resId The resource id of the image data
     * @param dstWidth Width of destination area
     * @param dstHeight Height of destination area
     * @param scalingLogic Logic to use to avoid image stretching
     * @return Decoded bitmap
     */
    public static Bitmap decodeResource(String path, int dstWidth, int dstHeight,
            ScalingLogic scalingLogic) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth,
                dstHeight, scalingLogic);
        Bitmap unscaledBitmap = BitmapFactory.decodeFile(path, options);

        return unscaledBitmap;
    }

    /**
     * Utility function for creating a scaled version of an existing bitmap
     *
     * @param unscaledBitmap Bitmap to scale
     * @param dstWidth Wanted width of destination bitmap
     * @param dstHeight Wanted height of destination bitmap
     * @param scalingLogic Logic to use to avoid image stretching
     * @return New scaled bitmap object
     */
    public static Bitmap createScaledBitmap(Bitmap unscaledBitmap, int dstWidth, int dstHeight,
            ScalingLogic scalingLogic) {
    	
        Rect srcRect = calculateSrcRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(),
                dstWidth, dstHeight, scalingLogic);
        Rect dstRect = calculateDstRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(),
                dstWidth, dstHeight, scalingLogic);
        Bitmap scaledBitmap = Bitmap.createBitmap(dstRect.width(), dstRect.height(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    /**
     * ScalingLogic defines how scaling should be carried out if source and
     * destination image has different aspect ratio.
     *
     * CROP: Scales the image the minimum amount while making sure that at least
     * one of the two dimensions fit inside the requested destination area.
     * Parts of the source image will be cropped to realize this.
     *
     * FIT: Scales the image the minimum amount while making sure both
     * dimensions fit inside the requested destination area. The resulting
     * destination dimensions might be adjusted to a smaller size than
     * requested.
     */
    public static enum ScalingLogic {
        CROP, FIT
    }

    /**
     * Calculate optimal down-sampling factor given the dimensions of a source
     * image, the dimensions of a destination area and a scaling logic.
     *
     * @param srcWidth Width of source image
     * @param srcHeight Height of source image
     * @param dstWidth Width of destination area
     * @param dstHeight Height of destination area
     * @param scalingLogic Logic to use to avoid image stretching
     * @return Optimal down scaling sample size for decoding
     */
    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight,
            ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                return srcWidth / dstWidth;
            } else {
                return srcHeight / dstHeight;
            }
        } else {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                return srcHeight / dstHeight;
            } else {
                return srcWidth / dstWidth;
            }
        }
    }

    /**
     * Calculates source rectangle for scaling bitmap
     *
     * @param srcWidth Width of source image
     * @param srcHeight Height of source image
     * @param dstWidth Width of destination area
     * @param dstHeight Height of destination area
     * @param scalingLogic Logic to use to avoid image stretching
     * @return Optimal source rectangle
     */
    public static Rect calculateSrcRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight,
            ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.CROP) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                final int srcRectWidth = (int)(srcHeight * dstAspect);
                final int srcRectLeft = (srcWidth - srcRectWidth) / 2;
                return new Rect(srcRectLeft, 0, srcRectLeft + srcRectWidth, srcHeight);
            } else {
                final int srcRectHeight = (int)(srcWidth / dstAspect);
                final int scrRectTop = (int)(srcHeight - srcRectHeight) / 2;
                return new Rect(0, scrRectTop, srcWidth, scrRectTop + srcRectHeight);
            }
        } else {
            return new Rect(0, 0, srcWidth, srcHeight);
        }
    }

    /**
     * Calculates destination rectangle for scaling bitmap
     *
     * @param srcWidth Width of source image
     * @param srcHeight Height of source image
     * @param dstWidth Width of destination area
     * @param dstHeight Height of destination area
     * @param scalingLogic Logic to use to avoid image stretching
     * @return Optimal destination rectangle
     */
    public static Rect calculateDstRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight,
            ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;

            if (srcAspect > dstAspect) {
                return new Rect(0, 0, dstWidth, (int)(dstWidth / srcAspect));
            } else {
                return new Rect(0, 0, (int)(dstHeight * srcAspect), dstHeight);
            }
        } else {
            return new Rect(0, 0, dstWidth, dstHeight);
        }
    }
}
