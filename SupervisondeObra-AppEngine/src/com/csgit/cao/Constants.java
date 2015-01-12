package com.csgit.cao;

public class Constants {
	public static final String WEB_CLIENT_ID = "1018107768-3vlbndllah8ntfs43dpsddcgr8s8bgpb.apps.googleusercontent.com";
	public static final String ANDROID_CLIENT_ID = "1018107768-tk42vn7bi8g7tlleg1rfha19vcapv78h.apps.googleusercontent.com";
	public static final String IOS_CLIENT_ID = "3-ios-apps.googleusercontent.com";
	public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
	public static final int Tipo_Avance_Proyecto = 0;
	public static final int Tipo_Avance_Avance = 1;
	public static final int Estado_Atrasado = 0;
	public static final int Estado_Conforme_Programa = 1;
	public static final int Estado_Adelantado = 2;
	public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
	public static final int Avance_Fisico = 0;
	public static final int Avance_Financiaro = 1;
	public static final int Proyecto = 0;
	public static final int Obra = 1;
	public static final int CatalogoMaquinaria = 2;
	public static final int Logotipos = 3;
	public static final int CatalogoPersonas = 4;
	public static final int CatalogoEmpresas = 5;
	public static final int Biblioteca = 6;
	public static final int Minuta = 7;
	public static final int Evidencia = 8;
	

	//URL para obtener del Blob Store
	//	public static final String URL_GET_BLOB_STORE = "http://1-dot-focal-furnace-615.appspot.com/serve?blob-key=";
	//	public static final String URL_GET_BLOB_STORE = "http://1-dot-iuyet-cao-csgit.appspot.com/serve?blob-key=";



	//    public static final String URL_GET_BLOB_STORE = "http://1-dot-iuyet-csgit-cao.appspot.com/serve?blob-key=";
	//	public static final String URL_GET_BLOB_STORE = "http://localhost/_ah/img/";
	//	public static final String URL_GET_BLOB_STORE = "http://1-dot-cao-iuyet-server.appspot.com/serve?blob-key=";
	//	public static final String URL_GET_BLOB_STORE = "http://1-dot-proyecto-cao-yoban.appspot.com/serve?blob-key=";
	//	public static final String URL_GET_BLOB_STORE = "http://1-dot-cao-iuyet-pruebas.appspot.com/serve?blob-key=";
	//	public static final String URL_GET_BLOB_STORE = "http://localhost:8888/_ah/img/";
		public static final String URL_GET_BLOB_STORE = "http://1-dot-superobra-adquem.appspot.com/serve?blob-key=";


	// Tipo de Empresas textos

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
	public static final int idSecretaria = 5;
	public static final int idGobierno = 6;

	// Tipos de archivos

	public static final int imagen = 0;
	public static final int video = 1;
	public static final int file = 2;






	// Formatos

	public static final String formatJpg = "jpg";
	public static final String formatJpeg = "jpeg";
	public static final String formatPng = "png";
	public static final String formatAvi = "avi";
	public static final String formatMp4 = "mp4";
	public static final String formatPdf = "pdf";
	public static final String formatExcel = "xls";
	public static final String formatWord = "doc";
	public static final String formatPowerPoint = "ppt";
	public static final String format3Gp = "3gp";


	public static final int idJpg = 0;
	public static final int idJpeg = 1;
	public static final int idPng = 2;
	public static final int idAvi = 3;
	public static final int idMp4 = 4;
	public static final int idPdf = 5;
	public static final int idExcel = 6;
	public static final int idWord = 7;
	public static final int idPowerPoint = 8;
	public static final int id3Gp = 9;
	/*
	 * excel:      application/vnd.ms-excel 
	 * 			   application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
	 * 
	 * pdf:        application/pdf
	 * 
	 * 
	 * word:       application/vnd.openxmlformats-officedocument.wordprocessingml.document
	 * 			   application/msword
	 * 
	 * powerpoint: application/vnd.ms-powerpoint
	 * 			   application/vnd.openxmlformats-officedocument.presentationml.presentation
	 * 
	 *  3gp		   video/3gpp
	 *  
	 *  jpg/jpeg   image/jpeg
	 *  
	 *  png        image/png
	 *  
	 *  avi        video/x-msvideo
	 *  
	 *  mp4        video/mp4
	 *  		   application/mp4
	 *
	 */









	public static final int estadoAtrasado = 0;
	public static final int estadoConformePrograma = 1;
	public static final int estadoAdelantado = 2;


	//Tipo de Maquinaria

	public static final String pesadaTexto = "Maquinaria Pesada";
	public static final String ligeraTexto = "Maquinaria Ligera";
	public static final String equipoTexto = "Equipo";

	public static final int idPesada = 1;
	public static final int idLigera = 2;
	public static final int idEquipo = 3;

	//Tipo de Usuarios

	public static final String administradorTexto = "Administrador";
	public static final String directivoTexto = "Directivo";
	public static final String supervisorTexto = "Supervisor";

	public static final int idAdministrador = 1;
	public static final int idDirectivo = 2;
	public static final int idSupervisor = 3;
	public static final String ERROR_NOT_REGISTERED = "no registrado";

	//Nombre para guardar y rescatar informacion del Usuario
	public static final String InfoUsrNombre = "nombre";
	public static final String InfoUsrApellido = "apellido";
	public static final String InfoUsrEmail = "email";
	public static final String InfoUsrFoto = "foto";
	public static final String SecretClient = "rkY_MKxASXvD959Y6YUyq6Bh";



}


		
	