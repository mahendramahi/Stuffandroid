package com.example.pyd;

import com.example.pyd.util.Constant;
import com.google.zxing.client.android.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainOptionActivity extends Activity {

	String tex = "";
	private static final int ZBAR_SCANNER_REQUEST = 0;
	ImageView CameraOpenImageButoon,HelpImageButton;
	Constant constant;
	Activity _activity;
	String TAG = "MainOptionActivity";
	PackageManager pm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		_activity = this;

		Animation myRotation = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.rotate_anim);

		final Animation myBounce = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.bounce);

		final ImageView img = (ImageView) findViewById(R.id.rotate);

		final ImageView searchsssss = (ImageView) findViewById(R.id.searchsssss);

		final TextView staticText1 = (TextView) findViewById(R.id.staticText1);

		final LinearLayout llEditSearch = (LinearLayout) findViewById(R.id.llEditSearch);

		final EditText edsearch = (EditText) findViewById(R.id.edsearch);

		final ImageView searchCross = (ImageView) findViewById(R.id.searchCross);

		CameraOpenImageButoon = (ImageView) findViewById(R.id.segarch);
		HelpImageButton = (ImageView) findViewById(R.id.searchss);
		img.startAnimation(myRotation);
		pm = getBaseContext().getPackageManager();

		searchsssss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				searchCross.setVisibility(View.VISIBLE);
				edsearch.setVisibility(View.VISIBLE);
				llEditSearch.setVisibility(View.VISIBLE);
				staticText1.setVisibility(View.GONE);
				llEditSearch.startAnimation(myBounce);
			}
		});

		edsearch.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				String input;
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					tex = edsearch.getText().toString();
					Log.d("lol", tex);
					// MyActivity.calculate(input);
					if (!tex.equalsIgnoreCase("")) {
						startActivity(new Intent(MainOptionActivity.this,
								MainActivity.class));
						finish();
					}
					return true; // consume.
				}
				return false; // pass on to other listeners.
			}
		});

		searchCross.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				searchCross.setVisibility(View.GONE);
				edsearch.setVisibility(View.GONE);
				llEditSearch.setVisibility(View.GONE);
				staticText1.setVisibility(View.VISIBLE);
			}
		});

		CameraOpenImageButoon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
					Log.d(TAG, "CAMERA IS AVAILABLE");
					
					Intent localintent = new Intent(_activity, CaptureActivity.class);
					startActivityForResult(localintent, ZBAR_SCANNER_REQUEST);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
					//_activity.finish();
				}

			}
		});
		
		HelpImageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Constant.showDialog(_activity, getResources().getString(R.string.scan_page_info));
			}
		});

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		
		
		case ZBAR_SCANNER_REQUEST:
			
			if (resultCode == RESULT_OK) {
				
				String str = data.getStringExtra("SCAN_RESULT");
				String format = data.getStringExtra("SCAN_RESULT_FORMAT");
				
				Log.d("MainOptionActivity", "content:"+str+" Format:"+format);
				
				Intent localIntent = new Intent();
				
				localIntent.putExtra("code", str);
				localIntent.putExtra("whichservice", "code");
				localIntent.setClass(getBaseContext(), OverViewClass.class);
				startActivity(localIntent);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				finish();
			}
			break;

		default:
			break;
		}
	}

}
