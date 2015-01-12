package com.csgit.cao.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.accountmanager.Account_Constantes;
import com.csgit.cao.business.Bus_First_Sync;
import com.csgit.cao.business.Bus_Maquinaria;
import com.csgit.cao.business.Bus_Obras;
import com.csgit.cao.model.Mod_Directorio;
import com.csgit.cao.model.communicationchannel.Communicationchannel;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseAvanceFinanciero;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseAvanceFisico;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseCatPersonal;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseCatTipoMaquinaria;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseConcepto;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseDirectorio;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseEmpresa;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseMaquinaria;
import com.csgit.cao.model.communicationchannel.model.CollectionResponseObra;
import com.csgit.cao.model.communicationchannel.model.Directorio;
import com.csgit.cao.model.communicationchannel.model.Empresa;
import com.csgit.cao.model.communicationchannel.model.Maquinaria;
import com.csgit.cao.model.communicationchannel.model.Obra;
import com.csgit.cao.model.communicationchannel.model.Persona;
import com.csgit.cao.model.communicationchannel.model.Ubicaciones;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_EndPoints;
import com.csgit.cao.utils.Utl_GooglePlayServices;
import com.csgit.cao.utils.Utl_Imagen;
import com.csgit.cao.utils.Utl_Base_Datos;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.OnAccessRevokedListener;

public class Act_Login extends AccountAuthenticatorActivity implements View.OnClickListener, 
																	   ConnectionCallbacks, 
																	   OnConnectionFailedListener{
	
//	enum State {
//		REGISTERED, REGISTERING, UNREGISTERED, UNREGISTERING
//	}
//	private State curState = State.UNREGISTERED;
	
	private static final String TAG_SYNC = "Sync 1";
//	private static final String TAG_ACTIVITY = "Login";
	
//	private static final String TAG_TOKEN_GOOGLE = "Token";
	
//	private static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;
//	private static final int REQUEST_CODE_RESOLVE_ERR = 10000;

	private ProgressDialog mConnectionProgressDialog;
	private PlusClient mPlusClient;
	private ConnectionResult mConnectionResult;
	
	private Context contextoPrincipal;
	private SharedPreferences preferences;
	private Editor edit;
	
//	private Bus_Login bus_login;
	
	private AccountManager accountManager;

	private Account cuentaActual = null;
	
//	private String accountName;
	private String authToken;
//	private Communicationchannel messageEndpoint;
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.lyt_act_login);
		
		contextoPrincipal = getApplicationContext();
		preferences = PreferenceManager.getDefaultSharedPreferences(contextoPrincipal);
		
		mPlusClient = new PlusClient.Builder(contextoPrincipal, this, this)
//		mPlusClient = new PlusClient.;
        .setScopes(Scopes.PLUS_LOGIN)
//        .setScopes(Scopes.PROFILE)
        .build();

		mConnectionProgressDialog = new ProgressDialog(Act_Login.this);
		mConnectionProgressDialog.setMessage(getResources().getString(R.string.str_act_login_login));
		
		accountManager = AccountManager.get(contextoPrincipal);
		
//		bus_login = new Bus_Login();
		
		
		// Obtener datos de la cuenta
//		accountName = getIntent().getStringExtra(Account_Constantes.ACC_KEY_ACCOUNT_NAME);
		authToken = getIntent().getStringExtra(Account_Constantes.ACC_KEY_AUTH_TYPE);
		if(authToken == null)
			authToken = Account_Constantes.AUTHTOKEN_TYPE_FULL_ACCESS;

		findViewById(R.id.sign_in_button).setOnClickListener(this);
		
		if(preferences.getBoolean(Utl_Constantes.PRE_SESSION_INICIADA, false)){
			if(isExistsAccount(preferences.getString(Utl_Constantes.PRE_ACCOUNT_NAME, ""))){
				Intent intent = new Intent(Act_Login.this, Act_Main.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable("cuenta", cuentaActual);
				intent.putExtra("cuenta", bundle);
				startActivity(intent);
				finish();	
			}else{
				Toast.makeText(contextoPrincipal, 
						getResources().getString(R.string.str_toast_error_cuenta_no_existe),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		
		switch (id) {
		case 0:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle(R.string.str_act_login_dialog_title);
			dialog.setMessage(R.string.str_act_login_dialog_mensaje);
			dialog.setCancelable(false);
			dialog.setPositiveButton(R.string.str_dialog_fragment_aceptar,
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
			dialog.setNegativeButton(R.string.str_dialog_fragment_cancelar,
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
			
			return dialog.create();
			

		default:
			break;
		}
		return null;
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mPlusClient.connect();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mPlusClient.disconnect();
	}

	/**
	 * Valida si se va a crear una cuenta nueva o ya hay una cuenta con el mismo nombre
	 * @return true si la cuenta existe, false en caso contrario
	 */
	private boolean isExistsAccount(String usuario){
		Account[] accounts = accountManager.getAccounts();
		for (Account account : accounts) {
			if(account.name.equals(usuario)){
				cuentaActual = account;
				return true;
			}
		}
		return false;
		
	}
	
	private void crearNuevaCuenta(String user, String pass){
		final Account account = new Account(user, Account_Constantes.ACC_VAL_ACCOUNT_TYPE);
		
		accountManager.addAccountExplicitly(account, pass, null);

		Log.i("Account", "Cuenta: " + user+ " agregada correctamente");
		
		cuentaActual = account;
//		setAccountAuthenticatorResult(intent.getExgtras());
//		setResult(RESULT_OK, intent);
		
	}
	
	/**
	 * Inicia la Primera sicronizacion de la aplicacion.
	 * @return true si fue correcta, false en caso contrario
	 * 
	 */
	public void iniciaPrimeraSincronizacion(Context context){
		GetEndPoint getData = new GetEndPoint();
		getData.execute();
	}
	
	public class GetEndPoint extends AsyncTask<Void, Void, Boolean>{

		private List<?> Lista = null;
		private ProgressDialog dialog;
		private Communicationchannel channel = null;

		private Bus_First_Sync insertarBD;
		private Bus_Maquinaria bus_maquinaria;
		

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			// Borrar rutas
			Utl_Base_Datos.borrarDirectorios();
			Log.i(TAG_SYNC, "Rutas Borradas");	

			dialog = new ProgressDialog(Act_Login.this);
			dialog.setTitle(getResources().getString(R.string.str_progressdialog_synctittle));
			dialog.setMessage(getResources().getString(R.string.str_progressdialog_syncmessage));
			dialog.setCancelable(false);
			dialog.show();

			insertarBD = new Bus_First_Sync(contextoPrincipal);
			bus_maquinaria = new Bus_Maquinaria(contextoPrincipal);

			channel = Utl_EndPoints.initEndPoint();

		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
				if(manejoCuentas()){
					// Obtener Empresas	
					CollectionResponseEmpresa ResponseEmpresas = channel.listEmpresa().execute();
					Lista = ResponseEmpresas.getItems();
					if(insertarBD.insertBD_EndPoint(Lista, Utl_Constantes.TABLA_EMPRESAS)){
						Log.i(TAG_SYNC, "Get Empresas OK");	
					}else{
						Log.e(TAG_SYNC, "Get Empresas ERROR");
						return false;
					}

					//	Obtener Obras, Conceptos, Directorio y Documentos
					String user = mPlusClient.getAccountName();
					CollectionResponseObra ResponseObra = channel.listObra(0L, 0L, user).execute();
					Lista = ResponseObra.getItems();
					if(insertarBD.insertBD_EndPoint(Lista, Utl_Constantes.TABLA_OBRA)){
						Log.i(TAG_SYNC, "Get Obras OK");

						if(Lista != null){
							for (Object element : Lista) {
								Obra obra = (Obra) element;

								// Obtener Ubicaciones
								Ubicaciones ResponseUbicaciones = channel.getUbicaciones(obra.getIdUbicacion()).execute();
								List<Ubicaciones> ubica = new ArrayList<Ubicaciones>();
								ubica.add(ResponseUbicaciones);
								if(insertarBD.insertBD_EndPoint(ubica, Utl_Constantes.TABLA_UBICACION_OBRA)){
									Log.i(TAG_SYNC, "Get Ubicaciones OK");	
								}else{
									Log.e(TAG_SYNC, "Get Ubicaciones ERROR");
									return false;
								}

								//	Obtener avance fisico
								CollectionResponseAvanceFisico  ResponseAvanceFisico = channel.listAvanceFisico(obra.getIdObra(), Utl_Constantes.REF_OBRA).execute();
								Lista = ResponseAvanceFisico.getItems();
								if(insertarBD.insertBD_EndPoint(Lista, Utl_Constantes.TABLA_AVANCE_FISICO)){
									Log.i(TAG_SYNC, "Get Av Fisico OK");	
								}else{
									Log.e(TAG_SYNC, "Get Av Fisico ERROR");
									return false;
								}

								// Obtener avance financiero
								CollectionResponseAvanceFinanciero  ResponseAvanceFinanciero = channel.listAvanceFinanciero(obra.getIdObra(), Utl_Constantes.REF_OBRA).execute();
								Lista = ResponseAvanceFinanciero.getItems();
								if(insertarBD.insertBD_EndPoint(Lista, Utl_Constantes.TABLA_AVANCE_FINANCIERO)){
									Log.i(TAG_SYNC, "Get Av Financiero OK");	
								}else{
									Log.e(TAG_SYNC, "Get Av Financiero ERROR");
									return false;
								}

								// Obtener Conceptos
								CollectionResponseConcepto  ResponseConcepto = channel.listConcepto(obra.getIdObra(), 0L).execute();						
								List<?> conceptos = ResponseConcepto.getItems();
								if(insertarBD.insertBD_EndPoint(conceptos, Utl_Constantes.TABLA_CONCEPTO)){
									Log.i(TAG_SYNC, "Get Conceptos OK");	

								}else{
									Log.i(TAG_SYNC, "Get Conceptos Error");
									return false;
								}

								// Decargar Directorio
								CollectionResponseDirectorio responseDirectorio = channel.
										listDirectorio(Long.parseLong(
												String.valueOf(Utl_Constantes.REF_OBRA)), obra.getIdObra()).execute();

								if(obtenerDirectorio(responseDirectorio)){
									Log.i(TAG_SYNC, "Get Directorio OK");	
								}else{
									Log.i(TAG_SYNC, "Get Directorio OK");
									return false;
								}

								//	Descargar Documentos

							}
						}

					}else{
						Log.e(TAG_SYNC, "Get Obras ERROR");
						return false;
					}


					// Obtener cat persoanal
					CollectionResponseCatPersonal ResponseCatPersonal = channel.listCatPersonal().execute();
					Lista = ResponseCatPersonal.getItems();
					if(insertarBD.insertBD_EndPoint(Lista, Utl_Constantes.TABLA_CAT_PERSONAL)){
						Log.i(TAG_SYNC, "Get Personal OK");	
					}else{
						Log.e(TAG_SYNC, "Get Personal ERROR");
						return false;
					}

					// Obtener tipo maquinaria
					CollectionResponseCatTipoMaquinaria ResponseTipoMaquinaria = channel.listCatTipoMaquinaria().execute();
					Lista = ResponseTipoMaquinaria.getItems();
					if(insertarBD.insertBD_EndPoint(Lista, Utl_Constantes.TABLA_TIPO_MAQUINARIA)){
						Log.i(TAG_SYNC, "Get Cat Tipo Maquinaria OK");	
					}else{
						Log.e(TAG_SYNC, "Get Cat Tipo Maquinaria ERROR");
						return false;
					}

					// Obtener maquinaria
					CollectionResponseMaquinaria ResponseMaquinaria = channel.listMaquinaria().execute();
					Lista = ResponseMaquinaria.getItems();
					if(insertarBD.insertBD_EndPoint(Lista, Utl_Constantes.TABLA_CAT_MAQUINARIA)){
						Log.i(TAG_SYNC, "Get Maquinaria OK");

						//	Descargar Imagenes de la Maquinaria
						List<Maquinaria> listaMaquinaria = bus_maquinaria.getCatMaquinaria();
						Utl_Imagen.getFileBlobStore(listaMaquinaria, Utl_Constantes.REF_CAT_MAQUINARIA, contextoPrincipal);

						Log.i(TAG_SYNC, "Get Imagenes Maquinaria OK");

					}else{
						Log.e(TAG_SYNC, "Get Maquinaria ERROR");
						return false;
					}
					return true;
				}
				return false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG_SYNC, "Fallo sincronizacion inicial: " + e.getMessage());

				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if(result){
				edit = preferences.edit();
				edit.putBoolean(Utl_Constantes.PRE_SESSION_INICIADA, true);
				edit.putString(Utl_Constantes.PRE_ACCOUNT_NAME, mPlusClient.getAccountName());
				edit.commit();				

				Intent intent = new Intent(Act_Login.this, Act_Main.class);
				Bundle bundle = new Bundle();
				bundle.putParcelable("cuenta", cuentaActual);
				intent.putExtra("cuenta", bundle);
				startActivity(intent);
				finish();

			}else{
				Toast.makeText(contextoPrincipal, 
						getResources().getString(R.string.str_toast_sync_error),
						Toast.LENGTH_SHORT).show();
				accountManager.removeAccount(cuentaActual, null, null);

				Utl_Base_Datos.limpiarBaseDatos(contextoPrincipal);

				cerrarSesion();
			}

			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}

		private boolean manejoCuentas(){
			String tokeGoogle = "";
			boolean bandera = true;
			try {
				tokeGoogle = GoogleAuthUtil.getToken(Act_Login.this, mPlusClient.getAccountName(), "oauth2:" + Scopes.PLUS_LOGIN);
				Log.i("TOKEN", "Token :" + tokeGoogle);
				
				edit = preferences.edit();
				edit.putString(Utl_Constantes.PRE_G_PLUS_NAME, mPlusClient.getAccountName());
				edit.commit();
				
				
				// Enviar al backend

				// crear cuenta
				crearNuevaCuenta(mPlusClient.getAccountName(), tokeGoogle);
				
				// registrar dispositivo
				bandera = Utl_GooglePlayServices.registrarDispositivo(contextoPrincipal, 
						Act_Login.this, preferences);
				
			} catch (UserRecoverableAuthException e) {
				// TODO Auto-generated catch block
				bandera = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				bandera = false;
			} catch (GoogleAuthException e) {
				// TODO Auto-generated catch block
				bandera = false;
			}

			return bandera;
		}
		
		private boolean obtenerDirectorio(CollectionResponseDirectorio response){
			List<Mod_Directorio> directorio = new ArrayList<Mod_Directorio>();

			try {

				List<Directorio> lista = response.getItems();

				if(lista != null){
					for (Directorio directorio2 : lista) {

						for (String idPersona : directorio2.getArregloPersonasId()) {

							Persona responsePersona = channel.getPersona(Long.parseLong(idPersona)).execute();
							Mod_Directorio item = new Mod_Directorio();
//							item.setIdDirectorio(directorio2.getIdDirectorio());
							item.setIdObra(directorio2.getIdReferencia());
							
							Bus_Obras bus_obra = new Bus_Obras(contextoPrincipal);
							Empresa empresa = bus_obra.getEmpresa(responsePersona.getIdEmpresa());
							
							item.setRfcEmpresa(empresa.getRfc());
							item.setTipoEmpresa(empresa.getIdTipoEmpresa());
							item.setNombreEmpresa(empresa.getNombre());
							item.setNombre(responsePersona.getNombre());
							item.setApPaterno(responsePersona.getApPaterno());
							item.setApMaterno(responsePersona.getApMaterno());
							item.setRfcPersonal(responsePersona.getRfc());
							item.setCargo(responsePersona.getCargo());
							item.setTituloProfesional(responsePersona.getTituloProfesional());
							item.setCedulaProfesional(responsePersona.getCedulaProfesional());
							item.setFotografia(responsePersona.getFotografia());
							item.setSkype(responsePersona.getUsuarioSkype());
							item.setEmail(responsePersona.getEmails());
							item.setTelefonos(responsePersona.getTelefonos());
							item.setRadios(responsePersona.getRadios());
							item.setVisible(responsePersona.getVisible());

							directorio.add(item);
						}
					}
					return insertarBD.insertBD_EndPoint(directorio, Utl_Constantes.TABLA_DIRECTORIO);
				}else
					return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		if(mConnectionProgressDialog.isShowing()){
			// El usuario ya ha hecho clic en el bot��n de inicio de sesi��n. Empezar a resolver
            // errores de conexion. Esperar hasta onConnected() para ignorar el
            // di��logo de conexi��n.
			if(result.hasResolution()){
				try {
					result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
				} catch (SendIntentException e) {
					// TODO: handle exception
					mPlusClient.connect();
				}
			}
		}
		// Guarda el intento para que podamos empezar una actividad cuando el usuario haga clic
	    // en el bot��n de inicio de sesi��n.
		mConnectionResult = result;
	}

	@Override
	public void onConnected(Bundle connectionHint){
		// TODO Auto-generated method stub
		// Hemos resuelto todos los errores de conexi��n.
		mConnectionProgressDialog.dismiss();
		if(mPlusClient.isConnected()){
			Log.i("Login", "AccountName: " + mPlusClient.getAccountName());
			iniciaPrimeraSincronizacion(contextoPrincipal);
		}
	}
	
    private void cerrarSesion(){
    	if(mPlusClient.isConnected()){
    		mPlusClient.clearDefaultAccount();
    		mPlusClient.revokeAccessAndDisconnect(new OnAccessRevokedListener(){

				@Override
				public void onAccessRevoked(ConnectionResult status) {
					// TODO Auto-generated method stub
					
				}
    			
    		});
    		
//    		Desconectar la app de la cuenta
    		mPlusClient.disconnect();
    		
    	}
    }
    	
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		Log.d("Login", "disconnected");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.sign_in_button && !mPlusClient.isConnected()){
			if(mConnectionResult == null){
				mConnectionProgressDialog.show();
				mPlusClient.connect();

			}else{
				try {
					mConnectionResult.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
				} catch (SendIntentException e) {
					// TODO Auto-generated catch block
					// Intenta la conexi��n de nuevo.
					mConnectionResult = null;
					mPlusClient.connect();
				}
			}
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == REQUEST_CODE_RESOLVE_ERR && resultCode == RESULT_OK){
			mConnectionResult = null;
			mPlusClient.connect();
		}
	}

}
