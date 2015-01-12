package com.csgit.cao.accountmanager;

import static android.accounts.AccountManager.KEY_BOOLEAN_RESULT;
import static com.csgit.cao.accountmanager.Account_Constantes.AUTHTOKEN_TYPE_FULL_ACCESS;
import static com.csgit.cao.accountmanager.Account_Constantes.AUTHTOKEN_TYPE_FULL_ACCESS_LABEL;
import static com.csgit.cao.accountmanager.Account_Constantes.AUTHTOKEN_TYPE_READ_ONLY;
import static com.csgit.cao.accountmanager.Account_Constantes.AUTHTOKEN_TYPE_READ_ONLY_LABEL;
import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.csgit.cao.controllers.Act_Login;

public class Account_Authenticator extends AbstractAccountAuthenticator{

	private Context context;
	
	public Account_Authenticator(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public Bundle editProperties(AccountAuthenticatorResponse response,
			String accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle addAccount(AccountAuthenticatorResponse response,
			String accountType, String authTokenType,
			String[] requiredFeatures, Bundle options)
			throws NetworkErrorException {
		// TODO Auto-generated method stub
		Log.d(Account_AuthenticatorService.LOG_TAG, "Agregar un a nueva cuenta");
		Bundle reply = new Bundle();
		
		Log.d(Account_AuthenticatorService.LOG_TAG, "Token de la cuenta: "+ authTokenType);
		Intent intent = new Intent(context, Act_Login.class);
		intent.putExtra(Account_Constantes.ACC_KEY_ACCOUNT_TYPE, accountType);
		intent.putExtra(Account_Constantes.ACC_KEY_AUTH_TYPE, authTokenType);
		intent.putExtra(Account_Constantes.ACC_KEY_NEW_ACCOUNT, true);
		intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
		
		reply.putParcelable(AccountManager.KEY_INTENT, intent);
		
		return reply;
	}

	@Override
	public Bundle confirmCredentials(AccountAuthenticatorResponse response,
			Account account, Bundle options) throws NetworkErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle getAuthToken(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options)
			throws NetworkErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthTokenLabel(String authTokenType) {
		// TODO Auto-generated method stub
		if(AUTHTOKEN_TYPE_FULL_ACCESS.equals(authTokenType))
			return AUTHTOKEN_TYPE_FULL_ACCESS_LABEL;
		else if(AUTHTOKEN_TYPE_READ_ONLY.equals(authTokenType))
			return AUTHTOKEN_TYPE_READ_ONLY_LABEL;
		else
			return authTokenType + " (Label)";
	}

	@Override
	public Bundle updateCredentials(AccountAuthenticatorResponse response,
			Account account, String authTokenType, Bundle options)
			throws NetworkErrorException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle hasFeatures(AccountAuthenticatorResponse response,
			Account account, String[] features) throws NetworkErrorException {
		// TODO Auto-generated method stub
		final Bundle result = new Bundle();
		result.putBoolean(KEY_BOOLEAN_RESULT, false);
		
		
		return result;
	}

}
