package com.example.pyd;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.pyd.util.CommonMethods;
import com.example.pyd.util.Constant;

public class SplaceScreen extends Activity implements Runnable {

	Thread t;
	
	private CommonMethods common;
	private Constant constant;
	Activity activity;
	String marketAssociatedEmailId = "";
	String TAG = "SplaceScreen";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		t = new Thread(this);
		getDeviceLoginEmailId();
		activity = this;
		constant = new Constant(this);
		common = new CommonMethods(activity, this);
		
		if(constant.getConnectivityStatus())
		{
			try {
				common.SetSubscribeEmail(common.setSubscriber(marketAssociatedEmailId));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			constant.showMsg(getResources().getString(R.string.internet_error_message));
		}
		t.start();
		
	}

	@Override
	public void run() {

		try {
			Thread.sleep(1500);
			startActivity(new Intent(getBaseContext(), MainOptionActivity.class));
			this.finish();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void getDeviceLoginEmailId() {
		Account[] arrayofAccount = AccountManager.get(SplaceScreen.this)
				.getAccountsByType("com.google");
		if(arrayofAccount.length > 0)
		{
			this.marketAssociatedEmailId = arrayofAccount[0].name;
			Log.d(TAG, marketAssociatedEmailId);
			return;
		}
		Log.d(TAG, "Email Id is NUll");
	}
}
