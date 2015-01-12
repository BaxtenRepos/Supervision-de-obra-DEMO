package com.csgit.cao.utils;

import android.graphics.Color;

/*
 * Objetivo 
 * Contiene todos los valores que se mantienen constantes dentro de la aplicación.
 * 
 */
public class Utl_Constantes {
	
	public static final String SENDER_ID = "395623470680";// iuyet-csgit-cao
//	public static final String SENDER_ID = "603363386470";// cao-iuyet-server
//	public static final String SENDER_ID = "875037667666";// cao-iuyet-pruebas
//	public static final String SENDER_ID = null;
	
	/**
	 * Urls
	 */
//	crypto-plane-782
	
	// Pruebas otro
//	public static final String URL_BLOB_STORE = "http://1-dot-crypto-plane-782.appspot.com/blob/androidserveurl";
//	public static final String URL_GET_BLOB_STORE = "http://1-dot-crypto-plane-782.appspot.com/serve?blob-key=";
		
	// Pruebas iuyet-csgit-cao
	public static final String URL_BLOB_STORE = "http://1-dot-superobra-adquem.appspot.com/blob/androidserveurl";
	public static final String URL_GET_BLOB_STORE = "http://1-dot-superobra-adquem.appspot.com/serve?blob-key=";
	
	// Produccion Iuyet cao-iuyet-server
//	public static final String URL_BLOB_STORE = "http://1-dot-cao-iuyet-server.appspot.com/blob/androidserveurl";
//	public static final String URL_GET_BLOB_STORE = "http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key=";
	
	// cao-iuyet-pruebas
//	public static final String URL_BLOB_STORE = "http://1-dot-cao-iuyet-pruebas.appspot.com/blob/androidserveurl";
//	public static final String URL_GET_BLOB_STORE = "http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key=";
	
	/**
	 * Minutas y Evidencias al dia
	 */
	public static final int NUMERO_MINUTAS_DIA = 10;
	public static final int NUMERO_EVIDENCIA_FOTO_DIA = 20;
	public static final int NUMERO_EVIDENCIA_VIDEO_DIA = 5;
	
	/**
	 * Menus
	 */
	public static final int MENU_BUSCAR_OBRAS_ID = 1;
	public static final int MENU_BUSCAR_CONCEPTOS_ID = 2;
	public static final int MENU_OPCIONES_ID = 3;
	public static final int MENU_UBICACION_ID = 4;
	public static final int MENU_DIRECTORIO_ID = 5;
	public static final int MENU_VER_DOCUMENTOS_ID = 6;
	public static final int MENU_TOMAR_EVIDENCIA = 7;
	public static final int MENU_GALERIAS = 8;
//	public static final int MENU_GALERIA_EVIDENCIA = 8;
//	public static final int MENU_GALERIA_MINUTAS_ID = 9;
	public static final int MENU_AGREGAR_REPORTE_ID = 9;
	public static final int MENU_BUSCAR_DOCUMENTOS_ID = 10;
	
//	public static final int MENU_ESTIMACION_ID = 10;
//	public static final int MENU_MAQUINARIA_ID = 11;
//	public static final int MENU_PERSONAL_ID = 12;
//	public static final int MENU_NOTAS_ID = 13;
	
	/**
	 * Fragmentos
	 */
	public static final int FRAG_OBRAS = 0;
	public static final int FRAG_VIEW_PAGER = 1; 
	
	/**
	 * Comando de procesamiento de video
	 */
	public static final String COMMAND1 = "ffmpeg -y -i ";
	public static final String COMMAND2 = " -strict experimental -s 480x360 -r 25 -vcodec mpeg4 -b 150k -ab 48000 -ac 2 -ar 22050 ";
	
	/**
	 * Estado de Registro
	 */
	public static final int REG_NO_VISIBLE_INT = 0;
	public static final int REG_VISIBLE_INT = 1;
	public static final boolean REG_NO_VISIBLE_BOOL = false;
	public static final boolean REG_VISIBLE_BOOL = true;
	
	/**
	 * Google play services
	 */
	public static final String URL_GOOGLE_PLAY_CALENDAR = "https://play.google.com/store/apps/details?id=com.google.android.calendar";
	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	
    /**
     * Aditivas y deductivas
     */
	public static final int OPERACION_ADITIVA = 0;
	public static final int OPERACION_DEDUCTIVA = 1;
	
	
	public static final String PORCENTAJE_MINIMO = "0%";
	public static final String PORCENTAJE_MAXIMO = "100%";
	public static final String VALOR_MINIMO = "0";
	public static final String VALOR_MAXIMO = "1";
	/**
	 * Tipo de Calculo
	 */
	public static final int CAL_AVANCE_FISICO = 0;
	public static final int CAL_AVANCE_FINANCIERO = 1;
	
	/**
	 * Tipos mime
	 */
	public static final String[] MIME_TYPES = {
		"image/jpeg", "image/png", 
		"application/pdf",
		"application/msword",
		"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
		"application/vnd.ms-powerpoint",
		"application/vnd.openxmlformats-officedocument.presentationml.presentation",
		"application/vnd.ms-excel",
		"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
	
	/**
	 * Estado de las Obras
	 */
	public static final int ESTADO_ATRASADO = 0;
	public static final int ESTADO_CONFORME_PROGRAMA = 1;
	public static final int ESTADO_ADELANTADO = 2;
	
	public static final String ESTADO_ATRASADO_STRING = "atrasado";
	public static final String ESTADO_CONFORME_PROGRAMA_STRING = "conforme al programa";
	public static final String ESTADO_ADELANTADO_STRING = "adelantado";
	
	/**
	 * Colores de estado de Obras
	 */
	public static final int COLOR_ROJO = Color.RED;
	public static final int COLOR_NARANJA = Color.parseColor("#EE820F");
	public static final int COLOR_VERDE = Color.parseColor("#137916");
	
	/**
	 * Acciones del Sincronizacion
	 */
	public static final int ACC_INSERT = 0;
	public static final int ACC_UPDATE = 1;
	public static final int ACC_DELETE = 2;
	
	/**
	 * Registro sincronizado
	 */
	public static final int REG_SINC = 1;
	public static final int REG_NO_SINC = 0;
	
	/**
	 * Tipo Acciones Reportes
	 */
	public static final int TIPO_ACCION_INSERT = 0;
	public static final int TIPO_ACCION_UPDATE = 1;
	public static final int TIPO_ACCION_DELETE = 3;
	public static final int TIPO_ACCION_NULL = 4;
	
	/**
	 * Expresiones regurales
	 */
	public static final String EXP_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/**
	 * Formatos
	 */
	public static final String FOR_FECHA = "dd/MM/yyyy hh:mm:ss";
	public static final String FOR_CANTIDADES_SIN_SINGO = "#,###.##";
	public static final String FOR_CANTIDADES = "$ #,###.##";
	
	/**
	 * Tags
	 */
	public static final String TAG_UPDATE_MULTIMEDIA = "TAG UPDATE MULTIMEDIA";
	public static final String TAG_UPDATE_DOCUMENTO = "TAG UPDATE DOCUMENTO";
	
	/**
	 * Configucacion de Video
	 */
	public static final int VID_DURACION_VIDEO_MAX = 300; // segundos
	public static final int VID_CALIDAD_MINIMA = 0;
	public static final int VID_CALIDAD_MAXIMA = 1;
	public static final int VID_LIMITE_TAMANO = 1024;
	
	/**
	 *  Formatos
	 */
	public static final String formatJpg = "jpg";
	public static final String formatJpeg = "jpeg";
	public static final String formatPng = "png";
	public static final String formatAvi = "avi";
	public static final String formatMp4 = "mp4";
	public static final String format3gp = "3gp";
	public static final String formatPdf = "pdf";
	public static final String formatExcel = "xls";
	public static final String formatWord = "doc";
	public static final String formatPowerPoint = "ppt";
	
	public static final String formatAuxExcel1 = "xlsx";
	public static final String formatAuxExcel2 = "xlst";
	public static final String formatAuxExcel3 = "xlt";
	public static final String formatAuxPower1 = "pptx";
	public static final String formatAuxWord1 = "docx";
	public static final String formatAuxWord2 = "dot";
	
	
	public static final int idJpg = 0;
	public static final int idJpeg = 1;
	public static final int idPng = 2;
	public static final int idAvi = 3;
	public static final int idMp4 = 4;
	public static final int idPdf = 5;
	public static final int idExcel = 6;
	public static final int idWord = 7;
	public static final int idPowerPoint = 8;
	public static final int id3gp = 9;
	
	/**
	 * Tipo Archivos
	 */
	public static final int TIPO_IMAGEN = 0;
//	public static final int TIPO_VIDEO = 1;
	public static final int TIPO_FILE = 2;
//	public static final int TIPO_MINUTA = 3;
	
	/**
	 * OpcineS para crear archivos descargados del Blob Store
	 */
	public static final int ARCH_MINUTA = 0;
	public static final int ARCH_IMAGEN = 1;
	public static final int ARCH_VIDEO = 2;
	public static final int ARCH_DOCUMENTO = 3;
	public static final int ARCH_CAT_MAQUINARIA = 4;
	
			
	/**
	 * Tipo de Empresas
	 */
	public static final String empresaTexto = "Empresa, ";
	public static final String contratistaTexto = "Contratista";
	public static final String supervisoraTexto = "Supervisora";
	public static final String dependenciaTexto = "Dependencia";
	public static final String particularTexto = "Particular";
	public static final String secretariaTexto = "Secretaria";
	public static final String GobiernoTexto = "Gobierno";
	
	public static final int idContratista = 1;
	public static final int idSupervisora = 2;
	public static final int idDependencia = 3;
	public static final int idParticular = 4;
	public static final int idSecretariaa = 5;
	public static final int idGobierno = 6;
	
	/**
	 * Dimesiones Imagen
	 */
	public static final int IMG_Width = 250;
	public static final int IMG_Height = 250;
	
	/**
	 * Parametros para el envio de archivos
	 */
	public static final String MULTIPART_FILE = "file";
	public static final String MULTIPART_ID_REFERENCIA = "idReferencia";
	public static final String MULTIPART_TIPO_ARCHIVO = "tipoArchivo";
	public static final String MULTIPART_FORMATO = "formato";
	public static final String MULTIPART_TIPO_REFERENCIA = "tipoReferencia";
	public static final String MULTIPART_DESCRIPCION = "descripcion";
	
	/**
	 * Estandarización de Proyectos y Obras
	 */
	public static final int REF_PROYECTO = 0;
	public static final int REF_OBRA = 1;
	public static final int REF_CAT_MAQUINARIA = 2;
	public static final int REF_DOCUMENTOS = 3;
	public static final int REF_MINUTA = 7;
	public static final int REF_EVIDENCIA = 8;
	
	/**
	 * Orden Sincronización GetEndPoints
	 */
	public static final int SINC_GET_ENDPOINT_UBICACION_OBRA = 1;
	public static final int SINC_GET_ENDPOINT_OBRAS = 2;
	public static final int SINC_GET_ENDPOINT_CONCEPTOS = 3;
	public static final int SINC_GET_ENDPOINT_AVANCE_FISICO = 4;
	public static final int SINC_GET_ENDPOINT_AVANCE_FINANCIERO = 5;
	public static final int SINC_GET_ENDPOINT_DIRECTORIO = 6;
	public static final int SINC_GET_ENDPOINT_CAT_PERSONAL = 7;
	public static final int SINC_GET_ENDPOINT_CAT_TIPO_MAQUINARIA = 8;
	public static final int SINC_GET_ENDPOINT_CAT_MAQUINARIA = 9;
	public static final int SINC_GET_ENDPOINT_EMPRESAS = 10;
	
	public static final int SINC_GET_NUMERO_TABLAS = 10;
	
	/*
	 * Tablas
	 */
	public static final int TABLA_DIRECTORIO = 1;
	public static final int TABLA_AVANCE_FISICO = 2;
	public static final int TABLA_AVANCE_FINANCIERO = 3;
	public static final int TABLA_UBICACION_OBRA = 4;
	public static final int TABLA_OBRA = 5;
	public static final int TABLA_NOTA = 6;
	public static final int TABLA_CAT_PERSONAL = 7;
	public static final int TABLA_REPO_PERSONAL_CAT_PERSONAL = 8;
	public static final int TABLA_REPORTE_DIARIO = 9;
	public static final int TABLA_CONCEPTO = 10;
	public static final int TABLA_DOCUMENTOS_OBRA = 11;
	public static final int TABLA_MINUTAS = 12;
	public static final int TABLA_ESTIMACION = 13;
	public static final int TABLA_MULTIMEDIA = 14;
	public static final int TABLA_AVANCE_CONCEPTO = 15;
	public static final int TABLA_REPO_MAQUINARIA_CAT_MAQUINARIA = 16;
	public static final int TABLA_CAT_MAQUINARIA = 17;
	public static final int TABLA_TIPO_MAQUINARIA = 18;
	public static final int TABLA_EMPRESAS = 19;
	public static final int TABLA_AVANCE_FISICO_CALCULO = 20;
	public static final int TABLA_ADITIVAS_DEDUCTIVAS = 21;
	
	/**
	 * Caracteres
	 */
	public static final String CARAC_SEPARA_PUNTOS_UBICACION = ":";
	public static final String CARAC_SEPARA_RUTAS = "/";
//	public static final String CARAC_MONEDA = "$ ";
	public static final String CARAC_SEPARA_FECHA_COMPLETA = " ";
	public static final String CARAC_SEPARA_FECHA = "/";
	public static final String CARAC_SEPARADOR_NOMBRE_EVIDENCIA = "_";
	public static final String CARAC_PORCENTAJE = "%";
	
	/**
	 * Número de Tabs del ViewPager
	 */
	public static final int NUMERO_TABS_PRINCIPAL = 7;
	public static final int NUMERO_TABS_HISTORIAL = 2;
	public static final int NUMERO_TABS_GALERIAS = 2;
	
	/**
	 * Opciones del Menú
	 */
	public static final int MENU_PERFIL = 7;
	public static final int MENU_MIS_OBRAS = 0;
	public static final int MENU_CALENDAR = 1;
	public static final int MENU_SINCRONIZAR = 2;
//	public static final int MENU_CONFIGURACIONES = 3;
	public static final int MENU_AYUDA = 3;
	public static final int MENU_ACERCA_DE = 4;
	public static final int MENU_CERRAR_SESION = 5;
	
	/**
	 * Orden de los Tabs de Obra
	 */
	
	public static final int TAB_OBRA_CONCEPTOS = 0;
	public static final int TAB_OBRA_AVANCE = 1;
	public static final int TAB_OBRA_INFORMACION = 2;
	public static final int TAB_OBRA_ESTIMACION = 3;
	public static final int TAB_OBRA_MAQUINARIA = 4;
	public static final int TAB_OBRA_PERSONAL = 5;
	public static final int TAB_OBRA_NOTAS = 6;
	
	/**
	 * Tabs Historial Operaciones
	 */
	public static final int TAB_AVANCES = 0;
	public static final int TAB_OPERACIONES = 1;
	
	/**
	 * Tabs Galerias
	 */
	public static final int TAB_EVIDENCIAS = 0;
	public static final int TAB_MINUTAS = 1;
	
	/**
	 * Evidencia
	 */
	public static final String FORMAT_DATE_EVIDENCIA = "yyyyMMdd_HHmmss";
	public static final String JPEG_FILE_PREFIX = "IMG_";
	public static final String JPEG_FILE_SUFFIX = ".jpg";
	public static final String VIDEO_FILE_PREFIX = "VID_";
//	public static final String VIDEO_FILE_SUFFIX = ".3gp";
	public static final String VIDEO_FILE_SUFFIX = ".mp4";
	public static final String DOC_FILE_PREFIX = "DOC_";
	public static final String DOC_FILE_SUFIX = "";
	
	/**
	 * Rutas
	 */
	public static final String RUTA_PRINCIPAL = "/Iuyet";
	public static final String RUTA_OBRAS = "/Obras";
	public static final String RUTA_EVIDENCIA = "/Evidencia";
	public static final String RUTA_MINUTAS = "/Minutas";
	public static final String RUTA_CAT_MAQUINARIA = "/Maquinaria";
	public static final String RUTA_DOCUMENTOS_OBRA = "/Documentos";
	
	/**
	 * SharePreferences
	 */
//	public static final String SHARED_PREFERENCES_NAME = "csgitPreferences";
	public static final String PRE_PATH_EVIDENCIA = "pathEvidencia";
	public static final String PRE_DEFAULT = "NA";
	public static final String PRE_PATH_MINUTAS = "pathEvidencia";
//	public static final String PRE_QUERY_OBRA = "queryObra";
	public static final String PRE_QUERY_CONCEPTO = "queryConcepto";
	public static final String PRE_ACCOUNT_NAME = "accountName";
	public static final String PRE_SESSION_INICIADA = "sessionActiva";
	public static final String PRE_G_PLUS_NAME = "gPlusName";
	
}
