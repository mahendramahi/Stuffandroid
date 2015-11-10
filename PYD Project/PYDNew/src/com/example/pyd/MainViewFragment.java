package com.example.pyd;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainViewFragment extends Fragment implements OnItemClickListener {   //implements OnClickListener {


	private ListView productlistView;
	private GridView productgridview;
	private ListView productFullList;
	
	public SharedPreferences MyPrefs;
	
	String[] all_tag_list,all_sub_tag,all_category,all_price;
	
	public TypedArray all_icon;
	
	String temp_view = "";
	String temp_click = "";
	
	
	@SuppressLint("Recycle")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.mainviewfragment, container,
				false);
		
		MyPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		
		all_tag_list = getResources().getStringArray(R.array.all_tag_list);
		all_sub_tag = getResources().getStringArray(R.array.all_sub_tag);
		all_category = getResources().getStringArray(R.array.all_category);
		all_price = getResources().getStringArray(R.array.all_price);
		
		all_icon = getResources().obtainTypedArray(R.array.all_icon);
	
	
		temp_view = MyPrefs.getString("view", "0");
		temp_click = MyPrefs.getString("click", "no");
		Log.d("temp_view", "value :"+temp_view+"click value: "+temp_click);
		
		productlistView = (ListView) rootView.findViewById(R.id.main_view_fragment_list_view);
		productgridview = (GridView) rootView.findViewById(R.id.main_view_grid);
		productFullList = (ListView) rootView.findViewById(R.id.main_view_fragment_full_custom_list_view);
		
		
		if (temp_click.equalsIgnoreCase("yes")) { 
			if (temp_view.equalsIgnoreCase("0")) {
				gridview();
				Log.d("click", "hua tha grid");
				SharedPreferences.Editor edit = MyPrefs.edit();
				edit.putString("view", "1");
				edit.putString("click", "no");
				edit.commit();
			}
			else if(temp_view.equalsIgnoreCase("1")){
				fulllistview();
				Log.d("click", "full k ly");
				SharedPreferences.Editor edit = MyPrefs.edit();
				edit.putString("view", "2");
				edit.putString("click", "no");
				edit.commit();
			}
			else if(temp_view.equalsIgnoreCase("2")){
				listview();
				Log.d("click", "list k ly");
				SharedPreferences.Editor edit = MyPrefs.edit();
				edit.putString("view", "0");
				edit.putString("click", "no");
				edit.commit();
			}
		}
		else {
			if (temp_view.equalsIgnoreCase("0") ) {
				listview();
				Log.d("hello", "list");
			}
			else if(temp_view.equalsIgnoreCase("1")){
				gridview();
				Log.d("hello", "grid");
			}
			else if(temp_view.equalsIgnoreCase("2")){
				fulllistview();
				Log.d("hello", "grid");
			}
		}
		
			return rootView;
	}
	
	
	void listview()
	{
		productgridview.setVisibility(View.GONE);
		productFullList.setVisibility(View.GONE);
		productlistView.setVisibility(View.VISIBLE);
		Log.d("hello", "list view function");
		productlistView.setAdapter(new MyCustomAdap(getActivity()));
		productlistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				startActivity(new Intent(getActivity(),OverViewClass.class));
				
			}
		});
	}
	void gridview()
	{
		Log.d("lol", "grid view call");
		productlistView.setVisibility(View.GONE);
		productFullList.setVisibility(View.GONE);
		productgridview.setVisibility(View.VISIBLE);
		productgridview.setAdapter(new MygridAdapter(getActivity()));
		productgridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				startActivity(new Intent(getActivity(),OverViewClass.class));
		
			}
		});
	}
	
	void fulllistview()
	{
		productlistView.setVisibility(View.GONE);
		productFullList.setVisibility(View.VISIBLE);
		productgridview.setVisibility(View.GONE);
		productFullList.setAdapter(new MyFullAdapter(getActivity()));
		productFullList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				startActivity(new Intent(getActivity(),OverViewClass.class));				

			}
		});

		
		
	}
	
	
	public class MyCustomAdap extends ArrayAdapter<String>
	{

		public MyCustomAdap(Context context) {
			super(context,R.layout.temp,all_tag_list);
			Log.d("HELLO", "adapt custom constractor call");
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.temp, parent, false);
			}

			Log.d("HELLO", "adapt view");
			
			ImageView list_icon_imageview = (ImageView) convertView
					.findViewById(R.id.imageView_tv_titlle_story);
			list_icon_imageview.setImageResource(all_icon.getResourceId(position, -1));
			
			

			TextView list_mainTag_textview = ((TextView) convertView.findViewById(R.id.tv_title_Category));
			String s = all_tag_list[position]+"\n"+"("+all_sub_tag[position]+")";
			list_mainTag_textview.setText(s);
			
			
			TextView list_category_textview = (TextView) convertView.findViewById(R.id.textView_category);
			
			list_category_textview.setText("category:"+all_category[position]);
			
			
			TextView list_price_textview = (TextView) convertView.findViewById(R.id.textView_price);
			
			list_price_textview.setText("price:"+all_price[position]);
			
			ImageView list_arrow_image = (ImageView) convertView
					.findViewById(R.id.imageViewarrow);
			
			return convertView;
		}
	}

	public class MygridAdapter extends ArrayAdapter<String>
	{

		public MygridAdapter(Context context) {
			super(context, R.layout.custom_grid_view,all_tag_list);

		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if(convertView == null)
			{
				convertView = getActivity().getLayoutInflater().inflate(R.layout.custom_grid_view, parent,false);
			}
			
			
			
			ImageView grid_icon = (ImageView) convertView.findViewById(R.id.imageView1);
			grid_icon.setImageResource((all_icon.getResourceId(position, -1)));
			
			TextView textView_gid_title = (TextView) convertView.findViewById(R.id.textView_gid_title);
			textView_gid_title.setText(all_tag_list[position]);
			
			TextView textView_grid_subtitle = (TextView) convertView.findViewById(R.id.textView_grid_subtitle);
			textView_grid_subtitle.setText("("+all_sub_tag[position]+")");
			
			
			TextView textView_grid_category = (TextView) convertView.findViewById(R.id.textView_grid_category);
			textView_grid_category.setText(""+all_category[position]);
			
			TextView textView_grid_price = (TextView) convertView.findViewById(R.id.textView_grid_price);
			textView_grid_price.setText(""+all_price[position]);
			
			
			return convertView;
		}
	}
	
	public class MyFullAdapter extends ArrayAdapter<String>
	
	{

		public MyFullAdapter(Context context) {
			super(context, R.layout.custom_full_list_view,all_tag_list);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null)
			{
				convertView = getActivity().getLayoutInflater().inflate(R.layout.custom_full_list_view, parent, false);
			}
			Log.d("HELLO", "adapt view");
			
			ImageView ImageView_icon_image = (ImageView) convertView.findViewById(R.id.imageView_fulllist_icon);
			ImageView_icon_image.setImageResource(all_icon.getResourceId(position, -1));
			
			TextView TextView_main_tag_text = (TextView) convertView.findViewById(R.id.textView_main_full_tag_text);
			TextView_main_tag_text.setText(all_tag_list[position]);
			
			
			TextView TextView_sub_Tag_text = (TextView) convertView.findViewById(R.id.textView_full_subtag_text);
			TextView_sub_Tag_text.setText(all_sub_tag[position]);
			
			
			TextView TextView_category_Tag_text = (TextView) convertView.findViewById(R.id.textView_full_category_tag);
			TextView_category_Tag_text.setText(all_category[position]);
			
			TextView TextView_price_tag_text = (TextView) convertView.findViewById(R.id.textView_full_price_tag);
			TextView_price_tag_text.setText(all_price[position]);
			
			return convertView;
		}
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	
}
