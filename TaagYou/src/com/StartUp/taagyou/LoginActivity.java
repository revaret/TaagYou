package com.StartUp.taagyou;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
       
        }
		
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//check username and password 
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				//pass username in intent
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
