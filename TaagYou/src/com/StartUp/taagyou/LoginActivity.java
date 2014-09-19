package com.StartUp.taagyou;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {
	
	Button login;
	EditText username,password;
	DataHandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		login =(Button) findViewById(R.id.button1);
		username = (EditText) findViewById(R.id.login_username);
		password = (EditText)findViewById(R.id.login_password);
		handler = new DataHandler(getBaseContext());
		
		
		boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun){
        	getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("firstrun", false)
            .commit();
        	
        	//insert into database
        	
        	
        	
        	//inserting into user_type
        	
        	String[] user_type = new String[]{"admin","student","teacher","alumin"};
        	long[] user_type_id = new long[]{0,1,2,3};
        	handler.open();
        	for(int i=0;i<user_type.length;i++)
        	{
        		
        		user_type_id[i]= handler.insetUserType(user_type[i]);
        	}
        	handler.close();
        	
        	//inserting into user_table
        	
        	long[] user_id = new long[]{0,1,2,3,4,5,6};
        	int[] user_type_user = new int[]{1,2,2,2,2,2,2};
        	String[] user_name = new String[]{"admin1","farsin1","suneesh1","ajmal1","hashim1","ashhar1","safwan1"};
        	String[] password = new String[]{"123","123","123","123","123","123","123"};
        	handler.open();
        	for(int i=0;i<user_name.length;i++)
        	{
        		user_id[i]=handler.insertUser(user_type_user[i], user_name[i], password[i]);
        	}
        	handler.close();
        	
        	//inserting into student_table
        	
        	//long[] student_id = new long[]{0,1,2,3,4,5,6};
        	
      	
        	Toast.makeText(getBaseContext(), "data inserted", Toast.LENGTH_LONG).show();
          	
        	
       
        }
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//check username and password 
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				//pass username in intent
				
				//checking 
				handler.open();
				Cursor cursor = handler.returnUserType();
				if(cursor.moveToFirst())
				{
					do{
						Toast.makeText(getBaseContext(), cursor.getString(0),Toast.LENGTH_SHORT).show();
						Toast.makeText(getBaseContext(), cursor.getString(1),Toast.LENGTH_SHORT).show();
					}while(cursor.moveToNext());
				}
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
