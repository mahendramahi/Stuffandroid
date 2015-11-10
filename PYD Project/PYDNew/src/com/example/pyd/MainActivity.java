package com.example.pyd;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {

	public SharedPreferences MyPrefs;
	private String[] mNavigationDrawerItemTitles;
	public ListView mDrawerListView;
	public DrawerLayout mDrawerlayout;

	public FrameLayout mFramLayout;
	public ActionBarDrawerToggle mActionBarDrawerToggle;

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;
	
	//ContentDisplaceDrawerToggle mContentDisplaceToggle;
	String temp_value_view = "";
	
	RelativeLayout RelativeLayoutOfRightDrawer;
	ObjectDrawerItem[] drawerItem;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getSupportActionBar();
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0c3a4f")));
		
		setContentView(R.layout.main);
		

		MyPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());

		
				mTitle = mDrawerTitle = getTitle();
				
				// initialize properties
				mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigationDrawer_item);
		        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		        mDrawerListView = (ListView) findViewById(R.id.right_drawer);
		        
		     
		        // for app icon control for nav drawer
		        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		        mDrawerlayout.setBackgroundColor(Color.WHITE);
		        mActionBarDrawerToggle = new ActionBarDrawerToggle(
		                this,                  /* host Activity */
		                mDrawerlayout,         /* DrawerLayout object */
		                R.drawable.header_back,  /* nav drawer icon to replace 'Up' caret */
		                R.string.navigation_drawer_open_status,  /* "open drawer" description */
		                R.string.navigation_drawer_close_status  /* "close drawer" description */
		                ) {
		        	
		            /** Called when a drawer has settled in a completely closed state. */
		            public void onDrawerClosed(View view) {
		                super.onDrawerClosed(view);
		                getSupportActionBar().setTitle(mTitle);
		                invalidateOptionsMenu();
		            }

		            /** Called when a drawer has settled in a completely open state. */
		            public void onDrawerOpened(View drawerView) {
		                super.onDrawerOpened(drawerView);
		                getSupportActionBar().setTitle(mDrawerTitle);
		                invalidateOptionsMenu();
		            }
		            
		            /** Called When a drawer has been slide**/
		          @Override
		            public void onDrawerSlide(View drawerView,
		            				float slideOffset) {
		            			// TODO Auto-generated method stub
		            			super.onDrawerSlide(drawerView, slideOffset);
		            			
		            			 View container = findViewById(R.id.content);
		            			    container.setTranslationX(-slideOffset*drawerView.getWidth());		            
		          }
		            @Override
		            		public boolean onOptionsItemSelected(MenuItem item) {
		            	if (item != null && item.getItemId() == R.id.action_navigate) {
		                    if (mDrawerlayout.isDrawerOpen(Gravity.RIGHT)) {
		                    	mDrawerlayout.closeDrawer(Gravity.RIGHT);
		                    } else {
		                    	mDrawerlayout.openDrawer(Gravity.RIGHT);
		                    }
		                    
		                }
		            	
		            	if(item.getItemId() == android.R.id.home)
		            	{
		            		startActivity(new Intent(getBaseContext(), MainOptionActivity.class));
		            		finish();
		            	}
		                return false;
		            			
		            		}
		        };

		        // Set the drawer toggle as the DrawerListener
		        mDrawerlayout.setDrawerListener(mActionBarDrawerToggle);
		        
		        // enable ActionBar app icon to behave as action to toggle nav drawer
		        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		        getSupportActionBar().setHomeButtonEnabled(true);
		        getSupportActionBar().setDisplayShowHomeEnabled(true);
		        /*getSupportActionBar().*/
		        
		        
		        
		        if (savedInstanceState == null) {
		        	selectItem(0);
		        }
			}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	     drawerItem = new ObjectDrawerItem[5];
	     
	        Log.d("onresume length", ""+drawerItem.length);
	        
	        temp_value_view = MyPrefs.getString("view", "0");
	        
	        String tempclick = MyPrefs.getString("click", "no");
	        
	        if(tempclick.equalsIgnoreCase("yes"))
	        {
	        	if(temp_value_view.equalsIgnoreCase("0"))
		        {
		        	drawerItem[0] = new ObjectDrawerItem(R.drawable.pg_flv, "Full LIst");	        	
		       }
		        else if(temp_value_view.equalsIgnoreCase("1"))
		        {
		        	drawerItem[0] = new ObjectDrawerItem(R.drawable.pflv_listv, "List View");	        	
		        }
		        else if(temp_value_view.equalsIgnoreCase("2"))
		        {
		        	drawerItem[0] = new ObjectDrawerItem(R.drawable.pl_grid, "Grid View");	        	
		        }
	        }
	        else {
	        	if(temp_value_view.equalsIgnoreCase("0"))
		        {
		        	drawerItem[0] = new ObjectDrawerItem(R.drawable.pl_grid, "Grid View");	        	
		       }
		        else if(temp_value_view.equalsIgnoreCase("1"))
		        {
		        	drawerItem[0] = new ObjectDrawerItem(R.drawable.pg_flv, "Full LIst");	        	
		        }
		        else if(temp_value_view.equalsIgnoreCase("2"))
		        {
		        	drawerItem[0] = new ObjectDrawerItem(R.drawable.pflv_listv, "List View");	        	
		        }	
			}
	        
	        drawerItem[1] = new ObjectDrawerItem(R.drawable.pl_sort, "Sort By");
	        drawerItem[2] = new ObjectDrawerItem(R.drawable.blank, "");
	        drawerItem[3] = new ObjectDrawerItem(R.drawable.pl_barcode, "Scane Product");
	        drawerItem[4] = new ObjectDrawerItem(R.drawable.pl_search, "Search Product");
	        
	        // Pass the folderData to our ListView adapter
	        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.right_drawer_item, drawerItem);
	        
	        // Set the adapter for the list view
	        mDrawerListView.setAdapter(adapter);
	        
	        // set the item click listener
	        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		Log.d("lol", "onstart");
		
	}

			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}
			
			
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				
		       if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
		           return true;
		       }
		       
		       return super.onOptionsItemSelected(item);
			}
			
			// to change up caret
		    @Override
		    protected void onPostCreate(Bundle savedInstanceState) {
		        super.onPostCreate(savedInstanceState);
		        mActionBarDrawerToggle.syncState();
		    }
			
		    @Override
		    public void onConfigurationChanged(Configuration newConfig) {
		    	// TODO Auto-generated method stub
		    	super.onConfigurationChanged(newConfig);
		    	mActionBarDrawerToggle.onConfigurationChanged(newConfig);
		    }
		    
			// navigation drawer click listener
			private class DrawerItemClickListener implements ListView.OnItemClickListener {
				
			    @Override
			    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			    	
			    	if (position == 0) {
						
			    		SharedPreferences.Editor edit = MyPrefs.edit();
			    		edit.putString("click", "yes");
			    		edit.commit();
			    		onResume();
					}
			    	
			        selectItem(position);
			    }
			    
			}

		    @SuppressLint("NewApi")
			private void selectItem(int position) {
		    	
		    	
		        Fragment fragment = null;
		        
		        switch (position) {
		        case 0:
		        	Log.d("fragment", "fragment");
		        	fragment = new MainViewFragment();
		            break;
		        case 1:
		            fragment = new SortFragment();
		            break;
		        case 2:
		            //fragment = new HelpFragment();
		            break;
		        default:
		            break;
		        }
		        
		        if (fragment != null) {
		            FragmentManager fragmentManager = getFragmentManager();
		            fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
		 
		            // update selected item and title, then close the drawer
		            mDrawerListView.setItemChecked(position, true);
		            mDrawerListView.setSelection(position);
		            setTitle(mNavigationDrawerItemTitles[position]);
		            mDrawerlayout.closeDrawer(mDrawerListView);
		            
		        } else {
		            // error in creating fragment
		            Log.e("MainActivity", "Error in creating fragment");
		        }
		    }
		    
		    @Override
		    public void setTitle(CharSequence title) {
		        mTitle = title;
		        getSupportActionBar().setTitle(mTitle);
		        //getActionBar().setTitle(mTitle);
		    }
		}
