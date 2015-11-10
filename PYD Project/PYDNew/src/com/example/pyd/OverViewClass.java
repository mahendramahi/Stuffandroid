package com.example.pyd;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.pyd.util.CommonMethods;
import com.example.pyd.util.Constant;
import com.example.pydVO.ProductDetail;
import com.google.zxing.client.android.CaptureActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint({ "NewApi", "Recycle" })
@SuppressWarnings("deprecation")
public class OverViewClass extends ActionBarActivity {

	
	Constant constant;
	CommonMethods common;
	String email = "";
	String marketAssociatedEmailId = "";
	String[] all_rate_tag;
	public TypedArray all_compeny_icon;

	TextView Available_Tag, Lowest_Price_tag, ProductName_Tag,
			Product_category;

	ListView List_view_Diffrent_compney_rate_tag;
	RelativeLayout Fram_of_Layout;
	
	Activity _overview;
	private static final int ZBAR_SCANNER_REQUEST = 0;
	PackageManager pm;
	private ArrayList<ProductDetail> al_product_detail = new ArrayList<ProductDetail>();
	
	protected int tasktype;
	private String strTitle;
	Animation CreateFragment_Frame;
	
	
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.d("OverView", "oncreate call");
		getSupportActionBar();
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0c3a4f")));
		
		setContentView(R.layout.over_view_class);
		
		pm = getPackageManager();
		_overview = this;
		constant = new Constant(_overview);
		common = new CommonMethods(_overview, this);
		CreateFragment_Frame = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

		Available_Tag = (TextView) findViewById(R.id.textView_Available_From);
		Lowest_Price_tag = (TextView) findViewById(R.id.textView_Lowest_Price);
		ProductName_Tag = (TextView) findViewById(R.id.textView_product_name_tag);
		Product_category = (TextView) findViewById(R.id.textView_category_tag);
		List_view_Diffrent_compney_rate_tag = (ListView) findViewById(R.id.List_view_list);
		Fram_of_Layout = (RelativeLayout) findViewById(R.id.oneRelativeLayout);

		all_rate_tag = getResources().getStringArray(R.array.all_rate_tag);

		all_compeny_icon = getResources().obtainTypedArray(
				R.array.all_compeny_icon);

		List_view_Diffrent_compney_rate_tag
				.setAdapter(new MyOverViewCustomListAdapter(getBaseContext()));
		List_view_Diffrent_compney_rate_tag.setOnItemClickListener(
				new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
		Fram_of_Layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				FramFragment fram_fragment  = new FramFragment();
				
				FragmentTransaction tran = getFragmentManager().beginTransaction();
				 tran.replace(android.R.id.content, fram_fragment);
				  tran.addToBackStack(null);
				  tran.commit();
				
				//popupImageFrameSelection();
				
			}
		});

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}
	
	class FramFragment extends android.app.Fragment
	{
		
		public FramFragment(){}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.popup_image_fram_select, container, false);
			
			ImageView cross_image = (ImageView) rootView.findViewById(R.id.popup_ImageInfo_Cross);
			
			cross_image.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					getActivity().getFragmentManager().popBackStack();
				}
			});
			
			rootView.startAnimation(CreateFragment_Frame);
			
			return rootView;
		}
		
	}

	class MyOverViewCustomListAdapter extends ArrayAdapter<String> {

		public MyOverViewCustomListAdapter(Context context) {
		super(context, R.layout.over_view_custom_list,all_rate_tag);
		
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null)
			{
				convertView = getLayoutInflater().inflate(R.layout.over_view_custom_list, parent, false);
			}
			
			ImageView ImageView_compeny_icon = (ImageView) convertView.findViewById(R.id.imageview_compny_icon_);
			ImageView_compeny_icon.setImageResource(all_compeny_icon.getResourceId(position, -1));
			
			TextView TextView_rate_tag = (TextView) convertView.findViewById(R.id.textView_rate_tag_);
			TextView_rate_tag.setText(all_rate_tag[position]);
			
			TextView Textview_buy_now = (TextView) convertView.findViewById(R.id.textView_buy_now_tag);
			ImageView ImageView_arrow_right = (ImageView) convertView.findViewById(R.id.imageView_arrow_of_over_view_list);
			
 					
					
			
			return convertView;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.over_view_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		if(item.getItemId() == android.R.id.home)
    	{
    		//startActivity(new Intent(getBaseContext(), MainOptionActivity.class));
    		finish();
    	}
		else if (item.getItemId() == R.id.action_over_email) {
			popupEmailSelection();
		}
		else if (item.getItemId() == R.id.action_over_barcode) {
			if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
				Log.d("Overview", "CAMERA IS AVAILABLE");
				
				Intent localintent = new Intent(_overview, CaptureActivity.class);
				startActivityForResult(localintent, ZBAR_SCANNER_REQUEST);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				_overview.finish();
			}
		}
		else if (item.getItemId() == R.id.action_over_home) {
			startActivity(new Intent(getBaseContext(), MainOptionActivity.class));
    		finish();
		}
        return false;
	}
	private void popupImageFrameSelection()
	{
		
		final Dialog dialogpopup = new Dialog(_overview);
		
		dialogpopup.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		dialogpopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialogpopup.setContentView(R.layout.popup_image_fram_select);
		dialogpopup.setCancelable(true);
		dialogpopup.show();
		
		final ImageView  MainFramImage = (ImageView) dialogpopup.findViewById(R.id.popup_ImageInfo_Image);
		
		
		//MainFramImage.setImageResource(R.drawable.banner_img);

		dialogpopup.findViewById(R.id.popup_ImageInfo_Cross).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				dialogpopup.dismiss();
				
			}
		});
		
	}
	private void popupEmailSelection() {
		final Dialog dialogMapMain = new Dialog(_overview);
		dialogMapMain.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		dialogMapMain.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialogMapMain.setContentView(R.layout.popup_email_select);
		dialogMapMain.setCancelable(false);
		dialogMapMain.getWindow().setGravity(Gravity.CENTER);
		dialogMapMain.show();

		final CheckBox cb_default = (CheckBox) dialogMapMain
				.findViewById(R.id.checkBoxDefault);

		final CheckBox cb_Enter = (CheckBox) dialogMapMain
				.findViewById(R.id.checkBoxEnter);

		final TextView txtEmail = (TextView) dialogMapMain
				.findViewById(R.id.txtEmailIDDevice);

		final EditText ed_email = (EditText) dialogMapMain
				.findViewById(R.id.ed_email);

		txtEmail.setText(marketAssociatedEmailId);

		cb_default.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {

					cb_Enter.setChecked(false);
				}

			}
		});

		cb_Enter.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {

					cb_default.setChecked(false);
				}

			}
		});

		TextWatcher Watcher = new TextWatcher() {
			public void afterTextChanged(Editable paramAnonymousEditable) {
				if (paramAnonymousEditable.length() == 0) {
					cb_default.setChecked(true);
					cb_Enter.setChecked(false);
				} else {
					cb_default.setChecked(false);
					cb_Enter.setChecked(true);
				}

			}

			public void beforeTextChanged(
					CharSequence paramAnonymousCharSequence,
					int paramAnonymousInt1, int paramAnonymousInt2,
					int paramAnonymousInt3) {
			}

			public void onTextChanged(CharSequence paramAnonymousCharSequence,
					int paramAnonymousInt1, int paramAnonymousInt2,
					int paramAnonymousInt3) {
			}
		};

		ed_email.addTextChangedListener(Watcher);

		dialogMapMain.findViewById(R.id.imgCrossBtn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialogMapMain.dismiss();
					}
				});

		dialogMapMain.findViewById(R.id.btn_submit).setOnClickListener(
				new OnClickListener() {

					@SuppressLint("DefaultLocale")
					@Override
					public void onClick(View v) {

						if (cb_Enter.isChecked()) {
							email = ed_email.getText().toString();
						} else {
							email = txtEmail.getText().toString();
						}

						if (!isValidEmail(email)) {
							constant.showMsg("" + "Invalid email");
						} else {
							sendMail(email);
						}

					}

					private void sendMail(String strEmail) {

						JSONArray jsonArray = new JSONArray();

						for (int i = 0; i < al_product_detail.size(); i++) {

							JSONObject jsonObject = new JSONObject();

							try {
								jsonObject.put("image", al_product_detail
										.get(i).getImage());
								jsonObject.put("url", al_product_detail.get(i)
										.getRefferal_link());

								jsonArray.put(i, jsonObject);
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						if (constant.getConnectivityStatus()) {
							try {
								tasktype = Constant.TaskType.SENDMAIL;
								common.postAudioComment(common
										.postAudioCommentRequestParmas(
												strEmail, jsonArray.toString(),
												strTitle));
							} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
								localUnsupportedEncodingException
										.printStackTrace();
							}
						} else {
							constant.showMsg(_overview.getResources()
									.getString(R.string.internet_error_message));
						}
						dialogMapMain.dismiss();
					}

					// validating email id
					public boolean isValidEmail(CharSequence target) {
						if (target == null) {
							return false;
						} else {
							return android.util.Patterns.EMAIL_ADDRESS.matcher(
									target).matches();
						}
					}// end isValidEmail() method

				});

	}
	
}