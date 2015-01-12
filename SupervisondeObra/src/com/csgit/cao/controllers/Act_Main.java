package com.csgit.cao.controllers;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.csgit.cao.R;
import com.csgit.cao.accountmanager.Account_Constantes;
import com.csgit.cao.adapters.Adp_ArrayAdapter_Drawer;
import com.csgit.cao.model.Mod_Item_Drawer;
import com.csgit.cao.utils.Utl_Constantes;
import com.csgit.cao.utils.Utl_Netwotk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.OnAccessRevokedListener;
import com.google.android.gms.plus.model.people.Person;

/**
 * The Main Activity.
 * 
 * This activity starts up the RegisterActivity immediately, which communicates
 * with your App Engine backend using Cloud Endpoints. It also receives push
 * notifications from backend via Google Cloud Messaging (GCM).
 * 
 * Check out RegisterActivity.java for more details.
 */

@SuppressLint({ "CommitTransaction", "Recycle" })
@SuppressWarnings("deprecation")
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Act_Main extends FragmentActivity implements ConnectionCallbacks, 
														OnConnectionFailedListener{
	
//	private FragmentManager fm;
//	private FragmentTransaction ft;
	
	/**
	 * Preferencias de Envio de multimedia
	 */
//	// Whether the display should be refreshed.
//	public static boolean refreshDisplay = true;
//	// Whether there is a Wi-Fi connection.
//	public static boolean wifiConnected = false;
//	// Whether there is a mobile connection.
//	public static boolean mobileConnected = false;
//	// The user's current network preference setting.
//	public static String sPref = null;
//
//	// The BroadcastReceiver that tracks network connectivity changes.
//	private NetworkReceiver receiver = new NetworkReceiver();
	
	private static final String TAG_SESION = "Cuenta";
	
	private static final int REQUEST_CODE_RESOLVE_ERR = 9001;
	private ProgressDialog mConnectionProgressDialog;
	private PlusClient mPlusClient;
//	private ConnectionResult mConnectionResult;
    
 // The BroadcastReceiver that tracks network connectivity changes.
//    private netwo
    
	private Context contextoPrincipal =  null;
//	private ConnectivityManager  connMagr = null;
//	private NetworkInfo networkInfo = null;
	
	/**
	 * DrawerView
	 */
	private String[] mCaoTitulos;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;
	private CharSequence drawerTitle;
	private CharSequence title_app;
	
	SharedPreferences preferences;
	Editor edit;
	
	/**
	 * Menus
	 */
	public static MenuItem menuBuscarDocumentos;
	public static MenuItem menuBuscarObras;
	public static MenuItem menuBuscarConceptos;
	public static MenuItem menuOpciones;
	public static MenuItem menuUbicacion;
	public static MenuItem menuDirectorio;
	public static MenuItem menuVerDocumentos;
	public static MenuItem menuTomarEvidencia;
	public static MenuItem menuGalerias;
//	public static MenuItem menuGaleriaEvidencia;
//	public static MenuItem menuGaleriaMinutas;
	public static MenuItem menuAgregarReporte;
	public static MenuItem menuEstimacion;
	public static MenuItem menuPersonal;
	public static MenuItem menuMaquinaria;
	public static MenuItem menuNotas;
	
//	private Dao_ContentObserver observer;
	private List<Mod_Item_Drawer> dataList;
	private Adp_ArrayAdapter_Drawer adapterDrawer;
	private Account cuentaActual = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_main);
		
		contextoPrincipal = this.getApplicationContext();
//		fm = getSupportFragmentManager();
//		ft = fm.beginTransaction();
		
//		IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//		receiver = new NetworkReceiver();  
//		contextoPrincipal.registerReceiver(receiver, filter);
		
		mPlusClient = new PlusClient.Builder(contextoPrincipal, this, this)
        .setScopes(Scopes.PLUS_LOGIN)
//        .setScopes(Scopes.PROFILE)
        .build();
		
		mConnectionProgressDialog = new ProgressDialog(Act_Main.this);
		mConnectionProgressDialog.setMessage("Signing in...");
		
		preferences = PreferenceManager.getDefaultSharedPreferences(contextoPrincipal);
//		preferences = contextoPrincipal.
//				getSharedPreferences(Constantes.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		
//		observer = new Dao_ContentObserver(new Handler(), contextoPrincipal);
		
		configDrawerView();
		
		if (savedInstanceState == null) {
			selectItem(Utl_Constantes.MENU_MIS_OBRAS);
		}
//		Bundle bundle = getIntent().getBundleExtra("cuenta");
//		cuentaActual = bundle.getParcelable("cuenta");
		
		
//		// registra el Observer de la Base de datos
//		contextoPrincipal.getContentResolver().registerContentObserver(
//				ConstantesBD.CAO_CONTENT_URI, true, observer);
		
//		Utl_Operaciones.getPuntosY();
		
	}
	

	/**
	 * Inicia el proceso de sincronización
	 */
	@SuppressLint("ShowToast")
	public void sincronizacion(){
		
//		if(refreshDisplay){
		NetworkInfo networkInfo = Utl_Netwotk.getNetworkInfo(contextoPrincipal);
		if(networkInfo != null && networkInfo.isConnected()){
			Bundle settingsBundle =  new Bundle();
			settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
			settingsBundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
			
			ContentResolver.requestSync(cuentaActual, Account_Constantes.AUTHORITY, settingsBundle);
		}else{
			Toast.makeText(contextoPrincipal, R.string.str_toast_lost_connection, Toast.LENGTH_SHORT).show();
		}
				
//		}else{
//			Toast.makeText(contextoPrincipal, R.string.str_toast_lost_connection, Toast.LENGTH_SHORT).show();	
//		}
	}
	
	/**
	 * Crea la lista del menú
	 */
	private void llenarListaMenus(){
		mCaoTitulos = getResources().getStringArray(R.array.menu_cao_array);
		dataList.add(new Mod_Item_Drawer(mCaoTitulos[0], R.drawable.ic_action_view_as_list));
		dataList.add(new Mod_Item_Drawer(mCaoTitulos[1], R.drawable.ic_action_go_to_today));
		dataList.add(new Mod_Item_Drawer(mCaoTitulos[2], R.drawable.ic_action_refresh));
//		dataList.add(new Mod_Item_Drawer(mCaoTitulos[3], R.drawable.ic_action_settings));
		dataList.add(new Mod_Item_Drawer(mCaoTitulos[3], R.drawable.ic_action_help));
		dataList.add(new Mod_Item_Drawer(mCaoTitulos[4], R.drawable.ic_action_about));
		dataList.add(new Mod_Item_Drawer(mCaoTitulos[5], R.drawable.ic_action_remove));
		
	}
	
	/**
	 * Configurar DrawerView
	 */
	private void configDrawerView(){
		
		dataList = new ArrayList<Mod_Item_Drawer>();
		title_app = drawerTitle = getTitle();
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);
		
		// muestra una sombra cuando el menu aparece
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
		
		llenarListaMenus();
		
		//iniciliza adapter
		adapterDrawer = new Adp_ArrayAdapter_Drawer(contextoPrincipal, R.layout.lty_drawer_list_item,
				dataList);
		
		// asigna la lista de elementos al menu
//		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.lty_drawer_list_item, mCaoTitulos));
		drawerList.setAdapter(adapterDrawer);

		// Asigna el evento click a los elementos del munu
		drawerList.setOnItemClickListener(new DrawerItemClickListener());

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		drawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				drawerLayout,         /* DrawerLayout object */
				R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
				R.string.drawer_open,  /* "open drawer" description for accessibility */
				R.string.drawer_close  /* "close drawer" description for accessibility */
				) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(title_app);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(drawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);

		// muestra la accion UP de la actividad (drawerToggle)
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// activa el icono de la aplicacion para mostrar el menú
		getActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
    public void setTitle(CharSequence title) {
    	title_app = title;
        getActionBar().setTitle(title);
    }
	
	/**
     * Cuando se utiliza el ActionBar, se debe llamar durante
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        drawerToggle.onConfigurationChanged(newConfig);
    }
    
    /**
     * Ocula los menus cuando el drawerView aparece
     */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// If the nav drawer is open, hide action items related to the content view
		boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
		if (drawerOpen) {
////			menu.findItem(R.id.menu_buscar_obras).setVisible(!drawerOpen);
////			menu.findItem(R.id.menu_buscar_conceptos).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_agregar_reporte_maquinaria).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_agregar_reporte_personal).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_directorio).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_minutas).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_ubicacion).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_agregar_notas).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_agregar_estimacion).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_act_galeria_minuta).setVisible(!drawerOpen);
//			menu.findItem(R.id.menu_ver_documentos).setVisible(!drawerOpen);
			
//			menuMaquinaria.setVisible(!drawerOpen);
//			menuPersonal.setVisible(!drawerOpen);
//			menuDirectorio.setVisible(!drawerOpen);
//			menuUbicacion.setVisible(!drawerOpen);
//			menuNotas.setVisible(!drawerOpen);
//			menuEstimacion.setVisible(!drawerOpen);
//			menuGaleriaMinutas.setVisible(!drawerOpen);
//			menuVerDocumentos.setVisible(!drawerOpen);
		}

		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Clase para control del DrawerView
	 * @author csgit
	 *
	 */
	/* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    /**
     * Controla las acciones de la lista de menus
     * @param position
     */
    @SuppressLint("NewApi")
	private void selectItem(int position) {
    	
    	
    	
    	switch (position) {
    	
    	case Utl_Constantes.MENU_PERFIL:
    		break;
    		
		case Utl_Constantes.MENU_MIS_OBRAS:
			
			Fragment frag = getSupportFragmentManager().findFragmentByTag("Frag_Obras");
			if(frag != null){
				getSupportFragmentManager().beginTransaction().remove(frag);
			}
			
			FragmentTransaction frag_trans = getSupportFragmentManager().beginTransaction();
	    	Fragment fragmento = null;
			fragmento = new Frag_Obras();
			frag_trans.replace(R.id.content_frame, fragmento, "Frag_Obras");
			frag_trans.commit();
			
			break;
			
		case Utl_Constantes.MENU_CALENDAR:
			abrirCalendar();
			break;

		case Utl_Constantes.MENU_SINCRONIZAR:
			sincronizacion();
			break;

//		case Constantes.MENU_CONFIGURACIONES:
//			Intent intentConf = new Intent(Act_Main.this, Act_Configuraciones.class);
//			startActivity(intentConf);
//			break;

		case Utl_Constantes.MENU_AYUDA:
			Intent intentAyuda = new Intent(Act_Main.this, Act_Ayuda.class);
			startActivity(intentAyuda);
			break;

		case Utl_Constantes.MENU_ACERCA_DE:
			Intent intentAcerca = new Intent(Act_Main.this, Act_Acerca_De.class);
			startActivity(intentAcerca);
			break;

		case Utl_Constantes.MENU_CERRAR_SESION:
			cerrarSesion();
			break;
			
		default:
			break;
		}
    	
        // update selected item and title, then close the drawer
        drawerList.setItemChecked(position, true);
    	
//        setTitle(mCaoTitulos[position]);
        setTitle(getResources().getString(R.string.titulo_principal));
        drawerLayout.closeDrawer(drawerList);
    }
    
    /**
     * Abre Calendar del dispositivo
     */
    private void abrirCalendar(){
    	
    	long startMillis = 0;
		Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
		builder.appendPath("time");
		ContentUris.appendId(builder, startMillis);
		Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
		
    	PackageManager packageManager = getPackageManager();
    	List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
    	if(activities.size() > 0)
    		startActivity(intent);
    	else{
    		Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Utl_Constantes.URL_GOOGLE_PLAY_CALENDAR));
    		startActivity(i);
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
    		Log.i(TAG_SESION, "Sesión Cerrada");
    		Toast.makeText(contextoPrincipal, getResources()
    				.getString(R.string.str_act_main_sesion_cerrada), Toast.LENGTH_SHORT).show();
    		
//    		Borrar preferences
    		edit = preferences.edit();
    		edit.putBoolean(Utl_Constantes.PRE_SESSION_INICIADA, false);
    		edit.putString(Utl_Constantes.PRE_ACCOUNT_NAME, "");
    		edit.commit();
    		
//    		Borrar base de datos
//    		Utl_Limpiar.limpiarBaseDatos(contextoPrincipal);
    		
//    		Quitar el registro del Observer
//    		contextoPrincipal.getContentResolver().unregisterContentObserver(observer);
    		
    		Intent intent = new Intent(this, Act_Login.class);
			startActivity(intent);
			finish();
    	}
//    	else{
//    		mPlusClient.connect();
//    	}
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	if(!mPlusClient.isConnected())
    		mPlusClient.connect();
    	
//    	updateConnectedFlags();
    	
//    	Toast.makeText(contextoPrincipal, "onresume", Toast.LENGTH_SHORT).show();
    }
    
	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		if(mConnectionProgressDialog.isShowing()){
			// El usuario ya ha hecho clic en el botón de inicio de sesión. Empezar a resolver
            // errores de conexión. Esperar hasta onConnected() para ignorar el
            // diálogo de conexión.
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
	    // en el botón de inicio de sesión.
//		mConnectionResult = result;
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		// Hemos resuelto todos los errores de conexión.
		mConnectionProgressDialog.dismiss();

		if(mPlusClient.isConnected()){
			Person person = mPlusClient.getCurrentPerson();
//			Toast.makeText(this, person.getDisplayName() +" is connected! en el main", Toast.LENGTH_LONG).show();
		}
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		Log.d("Login", "disconnected");
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mPlusClient.connect();
		
		// Obtener preferencias de usuario
//		sPref = preferences.getString("listPref", Constantes.WIFI);
//		updateConnectedFlags();
		
//		Toast.makeText(contextoPrincipal, "onstart", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mPlusClient.disconnect();

//		updateConnectedFlags();
//		Toast.makeText(contextoPrincipal, "stop", Toast.LENGTH_SHORT).show();
	}
	
//	protected void onPause() {
//		updateConnectedFlags();
//	};
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		if (receiver != null) {
//            contextoPrincipal.unregisterReceiver(receiver);
//        }
		
//		Toast.makeText(contextoPrincipal, "destroy", Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * Clase para escuchar los cambios de configuracion
	 * @author Iuyet
	 *
	 */
//	public class NetworkReceiver extends BroadcastReceiver{
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			// TODO Auto-generated method stub
//			NetworkInfo networkInfo = Utl_Netwotk.getNetworkInfo(context);
//			if(Constantes.WIFI.equals(sPref) && networkInfo != null 
//					&& networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
//				
//				refreshDisplay = true;
//				
//			}else if(Constantes.ANY.equals(sPref) && networkInfo != null){
//				
//				refreshDisplay = true;
//				
//			}else{
//				refreshDisplay = false;
//				Toast.makeText(context, R.string.str_toast_lost_connection, Toast.LENGTH_SHORT).show();
//			}
//		}
//		
//	}
	
//	private void updateConnectedFlags(){
//		NetworkInfo activeInfo = Utl_Netwotk.getNetworkInfo(contextoPrincipal);
//		if (activeInfo != null && activeInfo.isConnected()) {
//            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
//            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
//        } else {
//            wifiConnected = false;
//            mobileConnected = false;
//        }
//	}
}
