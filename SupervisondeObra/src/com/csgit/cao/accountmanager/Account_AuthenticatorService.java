package com.csgit.cao.accountmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Account_AuthenticatorService extends Service{

	/**
     * The tag used for the logs.
     */
    public static final String LOG_TAG = Account_AuthenticatorService.class.getSimpleName();
    
    private static Account_Authenticator sAccountAuthenticator = null;
    
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v(Account_AuthenticatorService.LOG_TAG, "Binding the service");
		IBinder ret = null;
		if(intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT)){
			ret = getAuthenticator().getIBinder();
		}
		
		return ret;
	}
	
	/**
     * The method used to obtain the authenticator. It is implemented as a singleton
     * @return The implementation of the class |AbstractAccountAuthenticator|
     */
    private Account_Authenticator getAuthenticator() {
        if (Account_AuthenticatorService.sAccountAuthenticator == null) {
        	Account_AuthenticatorService.sAccountAuthenticator = new Account_Authenticator(this);
        }
        return Account_AuthenticatorService.sAccountAuthenticator;
    }

}
