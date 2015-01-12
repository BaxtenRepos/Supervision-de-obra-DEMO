package com.csgit.cao.utils;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.csgit.cao.controllers.Act_Login;
import com.csgit.cao.model.communicationchannel.Communicationchannel;
import com.csgit.cao.model.communicationchannel.model.DeviceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class Utl_GooglePlayServices {

	/**
	 * Check the device to make sure it has the Google Play Services APK. If
	 * it doesn't, display a dialog that allows users to download the APK from
	 * the Google Play Store or enable it in the device's system settings.
	 */
	public static boolean checkPlayServices(Context context, Activity activity){
		String TAG = "GOOGLE PLAY SERVICES";
		
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
		if(resultCode != ConnectionResult.SUCCESS){
			if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
				GooglePlayServicesUtil.getErrorDialog(resultCode, activity, 
						Utl_Constantes.PLAY_SERVICES_RESOLUTION_REQUEST).show();
				
			}else{
				Log.i(TAG, "This device is not supported.");
	            activity.finish();
			}
			return false;
		}
		return true;
	}

	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	public static String getRegistrationId(Context context){
		String TAG = "GCM";
		
		final SharedPreferences prefs = getGCMPreferences(context);
		String registratioId = prefs.getString(Utl_Constantes.PROPERTY_REG_ID, "");
		if(registratioId.isEmpty()){
			Log.i(TAG, "Registration not found.");
	        return "";
		}
		
		// Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
		int registeredVersion = prefs.getInt(Utl_Constantes.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
		int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
		
		return registratioId;
	}
	
	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private static SharedPreferences getGCMPreferences(Context context) {
	    // This sample app persists the registration ID in shared preferences, but
	    // how you store the regID in your app is up to you.
	    return context.getSharedPreferences(Act_Login.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	
	public static boolean registrarDispositivo(Context context, Act_Login activity, 
			SharedPreferences preferences){
		GoogleCloudMessaging gcm;
		String regid;
		
		if(Utl_GooglePlayServices.checkPlayServices(context, activity)){
			gcm = GoogleCloudMessaging.getInstance(context);
			regid = Utl_GooglePlayServices.getRegistrationId(context);
			if (regid.isEmpty()) {
//				String msg = "";
				 try {
					if(gcm == null){
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					if(Utl_Constantes.SENDER_ID == null)
						return false;
					regid = gcm.register(Utl_Constantes.SENDER_ID);
//					Log.i("REG_ID", regid);
//					msg = "Device registered, registration ID=" + regid;
					
					// You should send the registration ID to your server over HTTP,
		           // so it can use GCM/HTTP or CCS to send messages to your app.
		           // The request to your server should be authenticated if your app
		           // is using accounts.
		           sendRegistrationIdToBackend(regid, context, preferences);

		           // For this demo: we don't need to send it because the device
		           // will send upstream messages to a server that echo back the
		           // message using the 'from' address in the message.

		           // Persist the regID - no need to register again.
		           storeRegistrationId(context, regid);
		           return true;
				} catch (IOException ex) {
					// TODO: handle exception
//					 msg = "Error :" + ex.getMessage();
		                // If there is an error, don't just keep trying to register.
		                // Require the user to click a button again, or perform
		                // exponential back-off.
					 return false;
				}
            }
			return true;
		}else{
			Log.i("GCM", "No valid Google Play Services APK found.");
			return false;
		}
	}
	
	/**
	 * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP
	 * or CCS to send messages to your app. Not needed for this demo since the
	 * device sends upstream messages to a server that echoes back the message
	 * using the 'from' address in the message.
	 */
	private static void sendRegistrationIdToBackend(String registration, Context context, SharedPreferences preferences) {
		// Your implementation here.
		Communicationchannel endpoint = Utl_EndPoints.initEndPoint();
		try {
			boolean alreadyRegisteredWithEndpointServer = false;
			// verificar si ya existe el registro en el backend
			DeviceInfo existingInfo = endpoint.getDeviceInfo(registration).execute();
			if(existingInfo != null && registration.equals(existingInfo.getDeviceRegistrationID())){
				alreadyRegisteredWithEndpointServer = true;
			}
			if(!alreadyRegisteredWithEndpointServer){
				DeviceInfo deviceInfo = new DeviceInfo();

				endpoint.insertDeviceInfo(
						deviceInfo
						.setDeviceRegistrationID(registration)
						.setTimestamp(System.currentTimeMillis())
						.setDeviceInformation(preferences.getString(Utl_Constantes.PRE_G_PLUS_NAME,
								""))).execute();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Stores the registration ID and app versionCode in the application's
	 * {@code SharedPreferences}.
	 *
	 * @param context application's context.
	 * @param regId registration ID
	 */
	private static void storeRegistrationId(Context context, String regId) {
		String TAG = "GCM";
	    final SharedPreferences prefs = getGCMPreferences(context);
	    int appVersion = getAppVersion(context);
	    Log.i(TAG, "Saving regId on app version " + appVersion);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(Utl_Constantes.PROPERTY_REG_ID, regId);
	    editor.putInt(Utl_Constantes.PROPERTY_APP_VERSION, appVersion);
	    editor.commit();
	}

	

}
