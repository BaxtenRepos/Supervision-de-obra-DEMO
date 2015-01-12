package com.csgit.cao.dao;

import android.net.Uri;

public class ConstantesBD {
	
	/*
	 * Datos de la base de Datos
	 */
	public static String DATABASE_NAME = "cao.db";
	public static int DATABASE_VERSION = 1;
	
	/*
	 * Configuración del ContentProvider
	 */
	public static final String CAO_PROVIDER_NAME = "com.csgit.cao.dao.CaoContentProvider";
	public static String CAO_URL = "content://" + CAO_PROVIDER_NAME + "/";
	public static final Uri CAO_CONTENT_URI = Uri.parse(CAO_URL);
	
	
	/*
	 * Definición de Base de Datos.
	 */
	public static final String sqlCreateEmpresa = "CREATE TABLE Empresas (IdEmpresa INTEGER NOT NULL, IdTipoEmpresa INTEGER,"
			+ " Nombre TEXT, Calle TEXT, CP INTEGER, Colonia TEXT, DelMuni TEXT, Entidad TEXT, IMSS TEXT, NumExt TEXT, NumInt TEXT, RFC TEXT,"
			+ " Siglas TEXT)";
	
	public static final String sqlCreateUbicacionObra = "CREATE TABLE UbicacionObra (IdUbicacionObra INTEGER NOT NULL PRIMARY KEY,"
			+ " Ubicacion TEXT)";
	
	public static final String sqlCreateAvanceFinanciero = "CREATE TABLE AvanceFinanciero (IdAvanceFinanciero INTEGER NOT NULL PRIMARY KEY,"
			+ " IdObra INTEGER NOT NULL, PorcentajeAvance REAL, PorcentajeTendencia REAL, FechaReporte TEXT, Estado INTEGER,"
			+ " FOREIGN KEY (IdObra) REFERENCES Obra (_id))";
	
	public static final String sqlCreateAvanceFisico = "CREATE TABLE AvanceFisico (IdAvanceFisico INTEGER NOT NULL PRIMARY KEY,"
			+ " IdObra INTEGER NOT NULL, PorcentajeAvance REAL, PorcentajeTendencia REAL, FechaReporte TEXT, Estado INTEGER,"
			+ " FOREIGN KEY (IdObra) REFERENCES Obra (_id))";
	
	public static final String sqlCreateDirectorio = "CREATE TABLE Directorio (IdDirectorio INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdObra INTEGER NOT NULL, RfcEmpresa TEXT, TipoEmpresa INTEGER, NombreEmpresa TEXT, Nombre TEXT, ApPaterno TEXT, ApMaterno TEXT, "
			+ " RfcPersonal TEXT, Cargo TEXT, TituloProfesional TEXT, CedulaProfesional TEXT, Fotografia TEXT, Skype TEXT, Email TEXT,"
			+ " Telefonos TEXT, Radios TEXT, visible INTEGER)";
	
	public static final String sqlCreateObras = "CREATE TABLE Obra (_id INTEGER NOT NULL PRIMARY KEY,"
			+ " IdProyecto INTEGER NOT NULL, NumContrato TEXT, RfcEmpresa TEXT, NombreCorto TEXT, IdGobierno INTEGER, IdSecretaria INTEGER,"
			+ " IdDependencia INTEGER, Direccion TEXT, SubDireccion TEXT, Area TEXT, Descripcion TEXT, FechaContrato TEXT, TipoContrato TEXT,"
			+ " ImptContratoSinIva REAL, NomEjercitoFiscal1 TEXT, ImptFiscal1SinIva REAL, ImptConvAmpliacion REAL, ImptConvReduccion REAL,"
			+ " ImptAjusteCostos REAL, FechaInicioContrato TEXT, FechaTerminoContrato TEXT, PartidaPresupuestal TEXT, Anticipo TEXT,"
			+ " NumFianzaAnticipo INTEGER, FechaFianzaAnticipo TEXT, MontoFianzaAnticipo REAL, NumFianzaCumpli TEXT, FechaFianzaCumpli TEXT,"
			+ " MontoFianzaCumpli REAL, CargoRevision1 TEXT, NombreRevision1 TEXT, CargoRevision2 TEXT, NombreRevision2 TEXT,"
			+ " NombreQuienAutoriza TEXT, CargoVoBo TEXT, NombreVoBo TEXT, EntidadFederativa TEXT, EmpresaContratista TEXT, SuperIntendente TEXT,"
			+ " Borrador TEXT, limiteDesvio TEXT, IdUbicacionObra INTEGER NOT NULL, visible INTEGER,"
			+ " FOREIGN KEY (IdUbicacionObra) REFERENCES UbicacionObra (IdUbicacionObra))";
	
	public static final String sqlCreateMinutas = "CREATE TABLE Minuta (IdMinuta INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdObra INTEGER NOT NULL, Formato TEXT, PathMinuta TEXT, FechaMinuta TEXT, Tipo INTEGER, UrlServer TEXT, BlobKey TEXT, "
			+ " IsSync INTEGER,"
			+ " FOREIGN KEY (IdObra) REFERENCES Obra (_id))";
	
	public static final String sqlCreateDocumentosObra = "CREATE TABLE DocumentosObra (IdDocumentosObra INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdObra INTEGER NOT NULL, PathDocumento TEXT, Formato TEXT, FechaDocumento TEXT,"
			+ " NombreDocumento TEXT, MimeType TEXT, UrlServer TEXT, BlobKey TEXT, IsSync INTEGER, Tipo INTEGER,"
			+ " FOREIGN KEY (IdObra) REFERENCES Obra (_id))";
	
	public static final String sqlCreateConceptos = "CREATE TABLE Concepto (IdConcepto INTEGER NOT NULL PRIMARY KEY,"
			+ " IdObra INTEGER NOT NULL, Clave TEXT, Descripcion TEXT, UnidadMedidad TEXT, CantidadTotal REAL, PrecioUnitario REAL,"
			+ " Importe REAL, CantidadAcumulada REAL, FechaInicio TEXT, FechaFin TEXT, IdPadre INTEGER, CantidadTotalRespaldo REAL,"
			+ " visible INTEGER,"
			+ " FOREIGN KEY (IdObra) REFERENCES Obra (_id))";
	
	public static final String sqlCreateMultimendia = "CREATE TABLE Multimedia (IdMultimedia INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdReporteDiario INTEGER NOT NULL, Formato TEXT, Path TEXT, Descripcion TEXT, Tipo INTEGER, UrlServer TEXT, BlobKey TEXT,"
			+ " Sync INTEGER NOT NULL, IdObra INTEGER NOT NULL, Fecha TEXT,"
			+ " FOREIGN KEY (IdReporteDiario) REFERENCES ReporteDiario (IdReporteDiario), FOREIGN KEY (IdObra) REFERENCES Obra (_id))";
	
	public static final String sqlCreateReporteDiario = "CREATE TABLE ReporteDiario (IdReporteDiario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdObra INTEGER NOT NULL, FechaReporteDiario TEXT, Sincronizado INTEGER, Elementos INTEGER,"
			+ " FOREIGN KEY (IdObra) REFERENCES Obra (_id))";
	
	public static final String sqlCreateAvanceConcepto = "CREATE TABLE AvanceConcepto (IdAvanceConcepto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdReporteDiario INTEGER NOT NULL, IdConceptos INTEGER NOT NULL, CantidadAvance REAL, fecha TEXT, unidadMedidad TEXT,"
			+ " FOREIGN KEY (IdReporteDiario) REFERENCES ReporteDiario (IdReporteDiario), FOREIGN KEY (IdConceptos) REFERENCES Concepto (IdConcepto))";
	
	public static final String sqlCreateNotas = "CREATE TABLE Nota (IdRepoNotas INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdReporteDiario INTEGER NOT NULL, Descripcion TEXT, Fecha TEXT, Titulo TEXT,"
			+ " FOREIGN KEY (IdReporteDiario) REFERENCES ReporteDiario (IdReporteDiario))";
	
	public static final String sqlCreateRepoPersonalCatPersonal = "CREATE TABLE RepoPersonalCatPersonal (IdRepoPersonalCatPersonal INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdCatPersonal INTEGER NOT NULL, IdReporteDiario INTEGER NOT NULL, Cantidad INTEGER NOT NULL,"
			+ " FOREIGN KEY (IdCatPersonal) REFERENCES CatPersonal (IdCatPersonal), FOREIGN KEY (IdReporteDiario) REFERENCES ReporteDiario (IdReporteDiario))";
	
	public static final String sqlCreateCatPersonal = "CREATE TABLE CatPersonal (IdCatPersonal INTEGER NOT NULL PRIMARY KEY,"
			+ " Nombre TEXT, Descripcion TEXT, visible INTEGER)";
	
	public static final String sqlCreateRepoMaquinariaCatMaquinaria = "CREATE TABLE RepoMaquinariaCatMaquinaria (IdRepoMaquinariaCatMaquinaria INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdCatMaquinaria INTEGER NOT NULL, IdReporteDiario INTEGER NOT NULL, Cantidad INTEGER NOT NULL,"
			+ " FOREIGN KEY (IdCatMaquinaria) REFERENCES CatMaquinaria (IdCatMaquinaria), FOREIGN KEY (IdReporteDiario) REFERENCES ReporteDiario (IdReporteDiario))";
	
	public static final String sqlCreateCatMaquinaria = "CREATE TABLE CatMaquinaria (IdCatMaquinaria INTEGER NOT NULL PRIMARY KEY,"
			+ " IdTipoMaquinaria INTEGER NOT NULL, PathImagen TEXT, Descripcion TEXT, visible INTEGER,"
			+ " FOREIGN KEY (IdTipoMaquinaria) REFERENCES TipoMaquinaria (IdTipoMaquinaria))";
	
	public static final String sqlCreateCatTipoMaquinaria = "CREATE TABLE TipoMaquinaria (IdTipoMaquinaria INTEGER NOT NULL PRIMARY KEY,"
			+ " TipoMaquinaria TEXT, Descripcion TEXT)";
	
	public static final String sqlCreateEstimacion = "CREATE TABLE Estimacion (IdEstimacion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " Estimacion INTEGER NOT NULL, IdObra INTEGER NOT NULL,IdConcepto INTEGER NOT NULL, FechaEstimacionInicio TEXT, "
			+ " FechaEstimacionTermino TEXT, Numero TEXT, CantidadAutorizada REAL, FechaEstimacion TEXT, Sync INTEGER,"
			+ " FOREIGN KEY (IdObra) REFERENCES Obra (_id), FOREIGN KEY (IdConcepto) REFERENCES Concepto (IdConcepto))";
	
	public static final String sqlCreateSync = "CREATE TABLE Sync (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
			+ " Entidad INTEGER, IdRegistro INTEGER, Accion INTEGER, Estado INTEGER)";
	
	public static final String sqlCreatePrograma = "CREATE TABLE Programa (IdPrograma INTEGER NOT NULL PRIMARY KEY, IdReferencia INTEGER,"
			+ " TipoReferencia INTEGER, TipoAvance INTEGER, Fecha TEXT, PorcentajeAvance REAL)";
	
	public static final String sqlCreateAditivaDeductiva = "CREATE TABLE AditivaDeductiva (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
			+ " IdConcepto INTEGER NOT NULL, TipoOperacion INTEGER NOT NULL, Cantidad REAL NOT NULL, Fecha TEXT NOT NULL,"
			+ " FOREIGN KEY (IdConcepto) REFERENCES Concepto (IdConcepto))"; 
	
	/*
	 * Columanas y nombre de la tablas de la base de datos
	 */
	public static final String nomTablaAditivaDeductiva = "AditivaDeductiva";
	public static final String[] colAditivaDeductiva = {"_id", "IdConcepto", "TipoOperacion", "Cantidad", "Fecha"};
	
	public static final String nomTablaPrograma = "Programa";
	public static final String[] colPrograma = {"IdPrograma", "IdReferencia", "TipoReferencia", "TipoAvance", "Fecha", "PorcentajeAvance"};
	
	public static final String nomTablaSync = "Sync";
	public static final String[] colSync = {"_id", "Entidad", "IdRegistro", "Accion", "Estado"};
	
	public static final String nomTablaEmpresas = "Empresas";
	public static final String[] colEmpresas = {"IdEmpresa", "IdTipoEmpresa", "Nombre", "Calle", "CP", "Colonia", "DelMuni", "Entidad", "IMSS", "NumExt", "NumInt", "RFC", "Siglas"};
	
	public static final String nomTablaUbicacionObra = "UbicacionObra";
	public static final String[] ColUbicacionObra = {"IdUbicacionObra", "Ubicacion"};
	
	public static final String nomTablaAvanceFinanciero = "AvanceFinanciero";
	public static final String[] ColAvanceFinanciero = {"IdAvanceFinanciero", "IdObra", "PorcentajeAvance", "PorcentajeTendencia",
		"FechaReporte", "Estado"};
	
	public static final String nomTablaAvanceFisico = "AvanceFisico";
	public static final String[] ColAvanceFisico = {"IdAvanceFisico", "IdObra", "PorcentajeAvance", "PorcentajeTendencia", "FechaReporte",
		"Estado"};
	
	public static final String nomTablaDirectorio = "Directorio";
	public static final String[] ColDirectorio = {"IdDirectorio", "IdObra", "RfcEmpresa", "TipoEmpresa", "NombreEmpresa", "Nombre", 
		"ApPaterno", "ApMaterno", "RfcPersonal", "Cargo", "TituloProfesional", "CedulaProfesional", "Fotografia", "Skype", "Email", 
		"Telefonos", "Radios", "visible"};
		
	public static final String nomTablaObra = "Obra";
	public static final String[] ColObras = {"_id", "IdProyecto", "NumContrato", "RfcEmpresa", "NombreCorto", "IdGobierno", "IdSecretaria",
		"IdDependencia", "Direccion", "SubDireccion", "Area", "Descripcion", "FechaContrato", "TipoContrato", "ImptContratoSinIva",
		"NomEjercitoFiscal1", "ImptFiscal1SinIva", "ImptConvAmpliacion", "ImptConvReduccion", "ImptAjusteCostos", "FechaInicioContrato",
		"FechaTerminoContrato", "PartidaPresupuestal", "Anticipo", "NumFianzaAnticipo", "FechaFianzaAnticipo", "MontoFianzaAnticipo",
		"NumFianzaCumpli", "FechaFianzaCumpli", "MontoFianzaCumpli", "CargoRevision1", "NombreRevision1", "CargoRevision2", "NombreRevision2",
		"NombreQuienAutoriza", "CargoVoBo", "NombreVoBo", "EntidadFederativa", "EmpresaContratista", "SuperIntendente", "Borrador", 
		"limiteDesvio", "IdUbicacionObra", "visible"};
	
	public static final String nomTablaMinuta = "Minuta";
	public static final String[] ColMinutas = {"IdMinuta", "IdObra", "Formato", "PathMinuta", "FechaMinuta" ,"Tipo", "UrlServer", "BlobKey",
		"IsSync"};
	
	public static final String nomTablaDocumentosObra = "DocumentosObra";
	public static final String[] ColDocumentosObra = {"IdDocumentosObra", "IdObra", "PathDocumento", "Formato", "FechaDocumento", 
		"NombreDocumento", "MimeType", "UrlServer", "BlobKey", "IsSync", "Tipo"};
	
	public static final String nomTablaConcepto = "Concepto";
	public static final String[] ColConceptos = {"IdConcepto", "IdObra", "Clave", "Descripcion", "UnidadMedidad", "CantidadTotal",
		"PrecioUnitario", "Importe", "CantidadAcumulada", "FechaInicio", "FechaFin", "IdPadre", "CantidadTotalRespaldo", "visible"};
	
	public static final String nomTablaMultimedia = "Multimedia";
	public static final String[] ColMultimedia = {"IdMultimedia", "IdReporteDiario", "Formato", "Path", "Descripcion", "Tipo", "UrlServer",
		"BlobKey", "Sync", "IdObra", "Fecha"};
	
	public static final String nomTablaReporteDiario = "ReporteDiario";
	public static final String[] ColReporteDiario = {"IdReporteDiario", "IdObra", "FechaReporteDiario", "Sincronizado", "Elementos"};
	
	public static final String nomTablaAvanceConcepto = "AvanceConcepto";
	public static final String[] ColAvanceConcepto = {"IdAvanceConcepto", "IdReporteDiario", "IdConceptos", "CantidadAvance", "fecha",
		"unidadMedidad"};
	
	public static final String nomTablaNota = "Nota";
	public static final String[] ColNotas = {"IdRepoNotas", "IdReporteDiario", "Descripcion", "Fecha", "Titulo"};
	
	public static final String nomTablaRepoPersonalCatPersonal = "RepoPersonalCatPersonal";
	public static final String[] ColRepoPersonalCatPersonal = {"IdRepoPersonalCatPersonal", "IdCatPersonal", "IdReporteDiario", "Cantidad"};
	
	public static final String nomTablaCatPersonal = "CatPersonal";
	public static final String[] ColCatPersonal = {"IdCatPersonal", "Nombre", "Descripcion", "visible"};
	
	public static final String nomTablaRepoMaquinariaCatMaquinaria = "RepoMaquinariaCatMaquinaria";
	public static final String[] ColRepoMaquinariaCatMaquinaria = {"IdRepoMaquinariaCatMaquinaria", "IdCatMaquinaria", "IdReporteDiario",
		"Cantidad"};
	
	public static final String nomTablaCatMaquinaria = "CatMaquinaria";
	public static final String[] ColCatMaquinaria = {"IdCatMaquinaria", "IdTipoMaquinaria", "PathImagen", "Descripcion", "visible"};
	
	public static final String nomTablaCatTipoMaquinaria = "TipoMaquinaria";
	public static final String[] ColCatTipoMaquinaria = {"IdTipoMaquinaria", "TipoMaquinaria", "Descripcion"};
	
	public static final String nomTablaEstimacion = "Estimacion";
	public static final String[] ColEstimacion = {"IdEstimacion", "Estimacion", "IdObra", "IdConcepto", "FechaEstimacionInicio", 
		"FechaEstimacionTermino", "Numero", "CantidadAutorizada" , "FechaEstimacion", "Sync"};
	
}
