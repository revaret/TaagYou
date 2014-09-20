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
        	
        	long[] user_id = new long[]{0,1,2,3,4,5,6,7};
        	int[] user_type_user = new int[]{1,2,2,2,2,2,2,3};
        	String[] user_name = new String[]{"admin1","farsin1","suneesh1","ajmal1","hashim1","ashhar1","safwan1","teacher1"};
        	String[] password = new String[]{"123","123","123","123","123","123","123","123"};
        	String[] name = new String[]{"principal","farsin","suneesh","ajmal","hashim","ashhar","safwan","sumesh sir"};
        	handler.open();
        	for(int i=0;i<user_name.length;i++)
        	{
        		user_id[i]=handler.insertUser(user_type_user[i], user_name[i], password[i],name[i]);
        	}
        	handler.close();
        	
        	//inserting into student_table
        	
        	long[] student_id = new long[]{0,1,2,3,4,5};
        	int[] student_user_id= new int[]{2,3,4,5,6,7};
        	int[] standard = new int[]{10,10,10,9,9,9};
        	String[] division = new String[]{"a","a","b","a","a","b"};
        	String[] rollno = new String[]{"100","200","300","400","500","600"};
        	String[] stud_email = new String[]{"farsin@gmail.com","suneesh@gmail.com","ajmal@gmail.com","hashim@gmail.com","ashhar@gmail.com","safwan@gmail.com"};
        	String[] stud_mobile = new String[]{"8606866644","9567823801","9633907911","9746630958","9048004258","9744247352"};
        	String [] gender = new String[]{"m","m","m","m","m","m"};
        	String [] dob = new String[]{"12/04/1992","13/06/1992","13/08/1991","04/04/1993","05/05/1991","06/0/1993"};
        	String[] parent_name = new String[] {"hamza","raju","shafaf","sayida","anees","ameen"};
        	String[] parent_email = new String[] {"hamza@gmail.com","raju@gmail.com","shafaf@gmail.com","sayida@gmail.com","anees@gmail.com","ameen@gmail.com"};
        	String[] parent_mobile = new String[]{"8086719339","9567823803","9633907917","9746639051","9048004320","9744247953"};
        	String[] image = new String[]{"farsin","suneesh","ajmal","hashim","ashhar","safwan"};
        	handler.open();
        	for(int i=0;i<division.length;i++){
        		student_id[i] = handler.insertStudent(student_user_id[i],standard[i], division[i], rollno[i], stud_email[i], stud_mobile[i], gender[i], dob[i], parent_name[i], parent_email[i], parent_mobile[i], image[i]);
        	}
        	
        	//inserting into exam table 
        	long[] exam_id =new long[]{0,1,2,3,4,5,6,7,8,9,10,11};
        	int[] exam_user_id = new int[]{2,3,4,5,6,7,2,3,4,5,6,7};
        	int[] exam_type = new int[]{1,1,1,1,1,1,2,2,2,2,2,2};
        	float[] sub1 = new float[]{60.0f,75.0f,85.0f,45.0f,49.0f,50.0f,60.0f,75.0f,85.0f,45.0f,49.0f,50.0f};
        	float[] max1 = new float[]{100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f};
        	float[] sub2 = new float[]{65.0f,88.0f,95.0f,87.0f,45.0f,100.0f,65.0f,88.0f,95.0f,87.0f,45.0f,100.0f};
        	float[] max2 = new float[]{100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f};
        	float[] sub3 = new float[]{65.0f,88.0f,95.0f,87.0f,45.0f,100.0f,65.0f,88.0f,95.0f,87.0f,45.0f,100.0f};
        	float[] max3 = new float[]{100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f,100.0f};
        	float[] total = new float[]{190.0f,251.0f,275.0f,219.0f,139.0f,250.0f,190.0f,251.0f,275.0f,219.0f,139.0f,250.0f};
        	handler.open();
        	for(int i =0;i<12;i++)
        	{
        		exam_id[i] = handler.insertExam(exam_user_id[i], exam_type[i], sub1[i], max1[i], sub2[i], max2[i], sub3[i], max3[i], total[i]);
        	}
        	handler.close();
        	
        	//inserting into attendence
        	long[] attendence_id = new long[]{0,1,2,3,4,5,6,7,8,9};
        	int[] attendence_user_id = new int[]{1,3,6,4,3,5,1,2,1,4};
        	String[] abscent_date = new String[]{"01/01/2014","01/01/2014","28/01/2014","02/02/2014","02/02/2014","05/02/2014","05/02/2014","03/03/2014","03/03/2014","03/03/2014"};
        	int[] leave = new int[]{1,1,0,0,1,1,1,1,1,0};
        	String[] abscent_reason= new String[]{"fever","","headache","","","fever","cold","fever","fever","marriage",""};
        	handler.open();
        	for(int i=0;i<abscent_date.length;i++){
        		
        		attendence_id[i]= handler.inserAttendence(attendence_user_id[i], abscent_date[i], leave[i], abscent_reason[i]);
        	}
        	handler.close();
        	
        	//inserting into bulletin
        	long[] bulletin_id = new long[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        	int[] bulletin_user_type = new int[]{1,1,1,2,2,2,1,2,2,2,2,1,1,3,3,2,2};
        	String[] bulletin_title = new String[]{"art fest","tommorow holiday","series exam","phythagorous theoram","algebra","what is sin","series exam","problem in vector",
        			"meaning of zigzag","cos h","cosine","date of sports","world war 2","tachers day","maths","ant","phosphour"};
        	int[] bulletin_user_name = new int[]{1,1,1,2,3,4,1,2,3,5,7,1,1,8,8,3,7};
        	String[] bulletin_content = new String[]{
        			"we fixed the arts fest on 10/10/14",
        			"3/3/14 i sholiday due to harthal",
        			"exam will start on 25/4/14",
        			"what is pyhtogrous theorm",
        			"what is algebra",
        			"what is sin",
        			"exam is reshduled to 30/4/14",
        			"what is vector",
        			"what is the meaning of zigzag",
        			"what is cosh",
        			"what is cosine",
        			"can we condut the sports oln 5/5/14",
        			"what you know about the world war 2",
        			"why the teacher's day",
        			"what is maths",
        			"importance of ants",
        			"what is phosurus"
        			};
        	String[] bulletin_date = new String[]{
        			
        			"01/01/14",
        			"01/02/14",
        			"01/03/14",
        			"01/04/14",
        			"01/04/14",
        			"01/04/14",
        			"01/04/14",
        			"01/05/14",
        			"01/06/14",
        			"01/07/14",
        			"01/08/14",
        			"01/09/14",
        			"01/10/14",
        			"01/11/14",
        			"01/11/14",
        			"01/11/14",
        			"01/12/14",
        			
        	};
        	
        	int[] bulletin_post_ype = new int[] {1,1,2,4,4,4,2,4,4,4,4,3,3,3,3,4,4};
        	int[] bulletin_likes = new int[]{5,4,7,6,5,1,2,3,5,2,4,6,5,4,2,1,1};
        	handler.open();
        	for(int i = 0;i<bulletin_date.length;i++)
        	{
        		bulletin_id[i]= handler.insertBulletin(bulletin_user_type[i], bulletin_title[i], bulletin_user_name[i], bulletin_content[i], bulletin_date[i], bulletin_post_ype[i], bulletin_likes[i]);
        	}
        	handler.close();
        	
        	//insert into comments
        	long[] comments_id = new long[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13};
        	int[] comments_user_id = new int[] {2,3,7,8,7,6,1,4,2,2,8,2,6,7};
        	int[] comments_post_id = new int[]{1,1,1,3,3,3,4,5,6,2,3,1,1,5};
        	String[] comments_text = new String[]{
        			"good",
        			":)",
        			"nice",
        			"its simple",
        			"ok",
        			"why?",
        			"so what?",
        			"its in the book",
        			"simple",
        			"k",
        			":)",
        			":)",
        			"nice to hear",
        			"hello??"

        	};
        	int[] comments_like = new int[]{2,1,0,4,5,6,1,2,3,5,1,2,2,2};
        	handler.open();
        	for(int i=0;i<comments_text.length;i++){
        		comments_id[i] = handler.insertComments(comments_user_id[i],comments_post_id[i] ,comments_text[i], comments_like[i]);
        	}
        	handler.close();
        	
        	//insert into fee
        	long[] fee_id = new long[]{0,1,2,3,4,5};
        	int[] fee_user_id= new int[]{2,3,4,5,6,7};
        	float[] fee_tution = new float[]{15000.0f,15000.0f,15000.0f,15000.0f,15000.0f,15000.0f};
        	float[] fee_exam = new float[]{5000.0f,5000.0f,5000.0f,5000.0f,5000.0f,5000.0f};
        	float[] fee_lab = new float[]{2000.0f,2000.0f,2000.0f,2000.0f,2000.0f,2000.0f};
        	float[] fee_bus = new float[]{10000.0f,9000.0f,1500.0f,12000.0f,2500.50f,1000.0f};
        	float[] fee_lib = new float[]{2000.0f,2000.0f,2000.0f,2000.0f,2000.0f,2000.0f};
        	float[] fee_extra = new float[]{1500.0f,0.0f,1200.75f,200.0f,1524.42f,0.0f};
        	handler.open();
        	for(int i = 0;i<6;i++)
        	{
        		fee_id[i] = handler.insertFee(fee_user_id[i],fee_tution[i], fee_exam[i], fee_lab[i], fee_bus[i], fee_lib[i], fee_extra[i]);
        	}
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
