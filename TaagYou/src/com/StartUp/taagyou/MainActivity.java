package com.StartUp.taagyou;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ExpandableListActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;


@SuppressLint("CutPasteId") public class MainActivity extends ActionBarActivity {

	ExpandableListAdapter listAdapter;
	ExpandableListView mDrawerList;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	String get_id,get_user_name,get_user_standard,get_user_image,get_user_division;
	DataHandler handler;
	LinearLayout container;
	String[] admin_titles,admin_contents,admin_dates,admin_likes,student_ids;
	String  inClause;

	//String []arrayMenu={"Acadamic","Bullettin Board","Discussion Centre","Documents","Fee Structure","School Bus","More"};
	// Within which the entire activity is enclosed
	private DrawerLayout mDrawerLayout;

	// ListView represents Navigation Drawer




	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
	private ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	private String mTitle = "";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = "TaagYou";
		getActionBar().setTitle(mTitle);


		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ExpandableListView) findViewById(R.id.drawer_list);
		mDrawerList.setDividerHeight(5);
		mDrawerList.setGroupIndicator(null);
		mDrawerList.setClickable(true);
		container = (LinearLayout) findViewById(R.id.notifications);


		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Home");
				invalidateOptionsMenu();
			}

		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting user name
		handler = new DataHandler(getBaseContext());
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View addview = layoutInflater.inflate(R.layout.user_row, null);

		ImageView user_image = (ImageView)addview.findViewById(R.id.user_image);
		TextView user_name = (TextView)addview.findViewById(R.id.user_name);
		TextView user_standard = (TextView)addview.findViewById(R.id.user_standard);
		TextView user_division = (TextView) addview.findViewById(R.id.user_division);
		get_id = getIntent().getStringExtra("user_id");

		handler.open();
		Cursor crs = handler.geUserById(get_id);
		if(crs.moveToFirst()){
			do{
				get_user_name = crs.getString(2);
			}while(crs.moveToNext());
		}
		handler.close();

		handler.open();
		Cursor cursor = handler.getStudent(get_id);
		if(cursor.moveToFirst()){
			do{
				get_user_standard = cursor.getString(0);
				get_user_division = cursor.getString(1);
				get_user_image = cursor.getString(3);
			}while(cursor.moveToNext());
		}
		user_name.setText(get_user_name);
		user_standard.setText("Class : "+get_user_standard+" th");
		user_division.setText("Division : "+get_user_division.toUpperCase());
		InputStream bitmap=null;

		try {
			bitmap=getAssets().open(get_user_image+".jpg");
			Bitmap bit=BitmapFactory.decodeStream(bitmap);
			user_image.setImageBitmap(bit);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bitmap!=null)
				try {
					bitmap.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//setting list adapter
		mDrawerList.addHeaderView(addview);

		//fetch admin notices
		admin_titles = new String[100];
		admin_contents = new String[100];
		student_ids = new String[100];
		admin_dates = new String[100];
		admin_likes = new String[100];
		
		//get ids by class and division
		handler.open();
		Cursor studIdStdDiv = handler.getStudentByStandardDivision(get_user_standard, get_user_division);
		student_ids = handler.makeArray(studIdStdDiv, "user_name");
		handler.close();
		Log.d("length", String.valueOf(student_ids.length));
		for(int i =0;i<student_ids.length;i++)
			Log.d("id", student_ids[i]);
		//make inclause
		inClause = Arrays.toString(student_ids);
		Log.d("string ids", inClause);
		inClause = inClause.replace("[","(");
		inClause = inClause.replace("]",")");

		//admin_titles
		handler.open();
		Cursor admin_data_cursor_title = handler.getNotices(inClause);
		admin_titles = handler.makeArray(admin_data_cursor_title, "title");
		handler.close();

		//admin_content
		handler.open();
		Cursor admin_data_cursor_content = handler.getNotices(inClause);
		admin_dates = handler.makeArray(admin_data_cursor_content, "content");
		handler.close();
		
		//dates
		handler.open();
		Cursor bulletin_date= handler.getNotices(inClause);
		admin_contents = handler.makeArray(bulletin_date, "date");
		handler.close();
		
		//LIKE
		handler.open();
		Cursor bulletin_likes= handler.getNotices(inClause);
		admin_likes = handler.makeArray(bulletin_likes, "likes");
		handler.close();
		
		
		

		//setting notifications
		for(int i=0;i<admin_titles.length;i++)
		{
			LayoutInflater layoutInflaternotice = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View addview1 = layoutInflaternotice.inflate(R.layout.notification_row, null);
			TextView notification_icon = (TextView) addview1.findViewById(R.id.icon_notification);
			TextView notification_title = (TextView) addview1.findViewById(R.id.title_notification);
			TextView notification_content = (TextView) addview1.findViewById(R.id.content_notification);
			TextView notification_date = (TextView) addview1.findViewById(R.id.bulletin_date);
			TextView notification_like = (TextView) addview1.findViewById(R.id.bulletin_likes);

			notification_icon.setText(String.valueOf(admin_titles[i].charAt(0)).toUpperCase());
			notification_title.setText(admin_titles[i]);
			notification_content.setText(admin_contents[i]);
			notification_date.setText(admin_dates[i]);
			notification_like.setText(admin_likes[i]+" likes this");

			container.addView(addview1);
		}

		user_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getBaseContext(), "profile", Toast.LENGTH_LONG).show();
				//gotoprofile
			}
		});
		mDrawerList.setAdapter(listAdapter);

		//mDrawerLayout.addView(addview);

		mDrawerList.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2,
					long arg3) {
				String parent=(String) arg0.getExpandableListAdapter().getGroup(arg2);
				Toast.makeText(getBaseContext(), parent,Toast.LENGTH_SHORT).show();
				return false;
			}
		});


		mDrawerList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
						+ " : "
						+ listDataChild.get(
								listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
										.show();
				return false;
			}
		});

		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);


		// Setting item click listener for the listview mDrawerList

	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data

		listDataHeader.add("Academic");
		listDataHeader.add("Bulletin Board");
		listDataHeader.add("Discussions");
		listDataHeader.add("Doucuments");
		listDataHeader.add("Fee Details");
		listDataHeader.add("More");

		// Adding child data
		List<String> child = new ArrayList<String>();
		child.add("Attendence");
		child.add("Test Results");
		child.add("Academic Calendar");
		child.add("Performance Graph");

		//Bulletin Board
		List<String> child1 = new ArrayList<String>();
		//child.add("");


		// discussion center
		List<String> child2 = new ArrayList<String>();
		child2.add("Topic Today");
		child2.add("Doubt Center");


		// Documents
		List<String> child3 = new ArrayList<String>();
		child3.add("Lesson on Demand");
		child3.add("E Library");
		child3.add("Syllaby");
		child3.add("Question Paper");


		//fee
		List<String> child4 = new ArrayList<String>();
		//child.add("");


		// more
		List<String> child5 = new ArrayList<String>();
		child5.add("Time Table");
		child5.add("Birthday Calendar");
		child5.add("Assignments");
		child5.add("School Bus");
		child5.add("Library");
		child5.add("Blog");




		listDataChild.put(listDataHeader.get(0), child); // Header, Child data
		listDataChild.put(listDataHeader.get(1), child1);
		listDataChild.put(listDataHeader.get(2), child2);
		listDataChild.put(listDataHeader.get(3), child3);
		listDataChild.put(listDataHeader.get(4), child4);
		listDataChild.put(listDataHeader.get(5), child5);
	}

}
