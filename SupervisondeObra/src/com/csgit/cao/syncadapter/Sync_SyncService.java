package com.csgit.cao.syncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Sync_SyncService extends Service{

	private static Sync_SyncAdapter syncAdapter = null;
	private static final Object syncAdapterLock = new Object();
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
	
		synchronized (syncAdapterLock){
			if(syncAdapter == null){
				syncAdapter = new Sync_SyncAdapter(getApplicationContext(), true);
			}
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return syncAdapter.getSyncAdapterBinder();
	}

}
