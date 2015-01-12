package com.csgit.cao.dao;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class CaoContentProvider extends ContentProvider{
	
	private SQLiteDatabase db;

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		
		Context context = getContext();
		CaoCreateDBProvider dbHelper = new CaoCreateDBProvider(context);
		
		/*
		 * Crea y/o abre la Base de Datos
		 */
		
		db = dbHelper.getWritableDatabase();
		return  (db == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] colums, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		
		String[] tabla = uri.toString().split("/");
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		qb.setTables(tabla[3]);

		Cursor c = qb.query(db, colums, selection, selectionArgs, null, null, sortOrder);
		
		/*
		 * Registro de URI para el contenido de los cambios
		 */
		c.setNotificationUri(getContext().getContentResolver(), uri);

		return c;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		
		String[] tabla = uri.toString().split("/");

		Uri _uri = null;
		
//		long rowID = db.insertWithOnConflict(tabla[3], "", values, SQLiteDatabase.CONFLICT_IGNORE);
		long rowID = db.insertWithOnConflict(tabla[3], "", values, SQLiteDatabase.CONFLICT_REPLACE);

		/*
		 * Si el registro fue insertado correctamente
		 */
		if (rowID > 0) {
			_uri = ContentUris.withAppendedId(ConstantesBD.CAO_CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(uri, null);
//			Log.i("URI insert", uri.toString());
			return _uri;
		}
//		throw new SQLException("Error insert registro en: " + uri);
		return _uri = Uri.parse("0");
		
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		
		int count = 0;
		String[] tabla = uri.toString().split("/");

		/*
		 * Borra un registro
		 */
		count = db.delete(tabla[3], selection, null);
		getContext().getContentResolver().notifyChange(uri, null);
		
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		
		int count = 0;
		String[] tabla = uri.toString().split("/");
		
		/*
		 * Actualiza un registro.
		 */
		count = db.update(tabla[3], values, selection, selectionArgs);

		getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

	
	public static class CaoCreateDBProvider extends SQLiteOpenHelper{
		
		CaoCreateDBProvider(Context context){
			super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL(ConstantesBD.sqlCreateEmpresa);
			db.execSQL(ConstantesBD.sqlCreateUbicacionObra);
			db.execSQL(ConstantesBD.sqlCreateObras);
			db.execSQL(ConstantesBD.sqlCreateAvanceFinanciero);
			db.execSQL(ConstantesBD.sqlCreateAvanceFisico);
			db.execSQL(ConstantesBD.sqlCreateDirectorio);
			db.execSQL(ConstantesBD.sqlCreateMinutas);
			db.execSQL(ConstantesBD.sqlCreateDocumentosObra);
			db.execSQL(ConstantesBD.sqlCreateConceptos);
			db.execSQL(ConstantesBD.sqlCreateAditivaDeductiva);
			db.execSQL(ConstantesBD.sqlCreateEstimacion);
			db.execSQL(ConstantesBD.sqlCreateMultimendia);
			db.execSQL(ConstantesBD.sqlCreateCatPersonal);
			db.execSQL(ConstantesBD.sqlCreateNotas);
			db.execSQL(ConstantesBD.sqlCreateCatTipoMaquinaria);
			db.execSQL(ConstantesBD.sqlCreateCatMaquinaria);
			db.execSQL(ConstantesBD.sqlCreateReporteDiario);
			db.execSQL(ConstantesBD.sqlCreateRepoPersonalCatPersonal);
			db.execSQL(ConstantesBD.sqlCreateRepoMaquinariaCatMaquinaria);
			db.execSQL(ConstantesBD.sqlCreateAvanceConcepto);
			db.execSQL(ConstantesBD.sqlCreateSync);
			db.execSQL(ConstantesBD.sqlCreatePrograma);
			
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
