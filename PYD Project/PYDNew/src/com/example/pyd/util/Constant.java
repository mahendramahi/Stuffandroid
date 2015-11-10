package com.example.pyd.util;

import com.example.pyd.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;




public class Constant {

	Context context;
	
	public Constant(Context con) {
		this.context = con;
	}

	/*
	 * Used for show Toast, take string parameter for message
	 */

	public void showMsg(String msg) {
		Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
	}

	/*
	 * Check camera
	 */
	public boolean isCameraAvailable() {
		Log.d("MainOptionActivity", "hellllll");
		PackageManager pm = context.getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

	/**
	 * This method is used for get the connectivity status
	 * 
	 * @return
	 */
	public boolean getConnectivityStatus() {
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connManager.getActiveNetworkInfo();
		if (info != null)
			if (info.isConnected()) {
				return true;
			} else {
				return false;
			}
		else
			return false;
	}

	/**
	 * This interface is used for handling constants of web services
	 * 
	 * @author Pramit Chaturvedi
	 * 
	 */
	public interface TaskType {
		public static final int SearchByProduct = 1001;
		public static final int SearchByProductName = 1002;
		public static final int SearchByProductAsin = 1003;
		public static final int SENDMAIL = 1004;

	}

	public static void showDialog(Activity activity, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(msg).setIcon(R.drawable.pl_logo)
				.setTitle(activity.getResources().getString(R.string.app_name))
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});

		AlertDialog dialog = builder.show();
		TextView messageText = (TextView) dialog
				.findViewById(android.R.id.message);
		messageText.setGravity(Gravity.CENTER);
	}

}
