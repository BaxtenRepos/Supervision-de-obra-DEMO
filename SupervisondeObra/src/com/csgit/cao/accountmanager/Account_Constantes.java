package com.csgit.cao.accountmanager;

public class Account_Constantes {

	public static final String AUTHORITY = "com.csgit.cao.dao.CaoContentProvider";
	
	public final static String ACC_KEY_ACCOUNT_TYPE = "ACCOUNT_TYPE";
	public static final String ACC_VAL_ACCOUNT_TYPE = "iuyet.com.mx";
	
	public final static String ACC_KEY_AUTH_TYPE = "AUTH_TYPE";
	public final static String ACC_KEY_NEW_ACCOUNT = "NEW_ACCOUNT";
	
	public final static String ACC_KEY_ACCOUNT_NAME = "ACCOUNT_NAME";
	public static final String ACC_VAL_ACCOUNT_NAME = "Iuyet";
	
    /**
     * Auth token types
     */
    public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
    public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL = "Read only access to an Iuyet account";

    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an Iuyet account";
}
