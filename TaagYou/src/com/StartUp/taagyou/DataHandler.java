package com.StartUp.taagyou;

import java.io.ObjectOutputStream.PutField;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;



public class DataHandler {

	public static final String DATA_BASE_NAME ="TaagYou.db";
	public static final int DATA_BASE_VERSION = 2;
	
	//TABLE USER TYPE
	public static final String USER_TYPE_ID = "id";
	public static final String USER_TYPE ="user_type";
	public static final String TABLE_USER_TYPE ="user_type_table";
	public static final String TABLE_USER_TYPE_CREATE="CREATE TABLE user_type_table(id INTEGER PRIMARY KEY AUTOINCREMENT,user_type TEXT NOT NULL);";
	
	//TABLE_USER
	public static final String  USER_ID ="id";
	public static final String USER_TYPE_USER ="user_type";
	public static final String USER_NAME_ID ="user_name";
	public static final String USER_PASSWORD ="password";
	public static final String USER_NAME = "name";
	public static final String TABLE_USER = "user_table";
	public static final String TABLE_USER_CREATE = "CREATE TABLE user_table(id INTEGER PRIMARY KEY AUTOINCREMENT,user_type INTEGER NOT NULL,user_name TEXT NOT NULL,password TEXT NOT NULL,name TEXT NOT NULL );";

	//TABLE STUDENT
	public static final String STUDENT_ID ="id";
	public static final String STUDENT_USER_NAME = "user_name";
		public static final String STUDENT_STANDARD = "standard";
	public static final String STUDENT_DIVISION ="division";
	public static final String STUDENT_ROLLNO = "roll_number";
	public static final String STUDENT_EMAIL = "student_email";
	public static final String STUDENT_MOBILE = "student_mobile";
	public static final String STUDENT_GENDER = "gender";
	public static final String STUDENT_DOB = "dob";
	public static final String STUDENT_PARENT_NAME="parent_name";
	public static final String STUDENT_PARENT_EMAIL="parent_email";
	public static final String STUDENT_PARENT_MOBILE="parent_mobile";
	public static final String STUDENT_PIC = "image";
	public static final String TABLE_STUDENT = "student_table";
	public static final String TABLE_STUDENT_CREATE = "CREATE TABLE student_table(id INTEGER PRIMARY KEY AUTOINCREMENT ,user_name INTEGER NOT NULL, " +
			"standard INTEGER NOT NULL ,division TEXT NOT NULL,roll_number TEXT NOT NULL,student_email TEXT NOT NULL,"
			+"student_mobile TEXT NOT NULL, gender INTEGER NOT NULL,dob TEXT NOT NULL, parent_name TEXT NOT NULL, parent_email TEXT NOT NULL, " +
			"parent_mobile TEXT NOT NULL,image TEXT NOT NULL);";
	
	//TABLE TEST RESULTS
	public static final String EXAM_ID ="id";
	public static final String EXAM_USER_NAME="user_name";
	public static final String EXAM_TYPE = "type";
	public static final String EXAM_SUB1="sub1";
	public static final String EXAM_MAX1="max1";
	public static final String EXAM_SUB2= "sub2";
	public static final String EXAM_MAX2 = "max2";
	public static final String EXAM_SUB3 = "sub3";
	public static final String EXAM_MAX3 = "max3";
	public static final String EXAM_TOTAL = "total";
	public static final String TABLE_EXAM = "exam_table";
	public static final String TABLE_EXAM_CREATE = "CREATE TABLE exam_table (id INTEGER PRIMARY KEY AUTOINCREMENT ,user_name INTEGER NOT NULL,type INTEGER NOT NULL,sub1 REAL NOT NULL, max1 REAL NOT NULL, " +
			"sub2 REAL NOT NULL, max2 REAL NOT NULL,sub3 REAL NOT NULL,max3 REAL NOT NULL , total REAL NOT NULL);";
	
	//TABLE ATTENDENCE
	public static final String ATTENDENCE_ID ="id";
	public static final String ATTENDENCE_USER = "user_name";
	public static final String ATTENDENCE_ABSCENT_DATE = "abscent_date";
	public static final String ATTENDENCE_LEAVE = "leave";
	public static final String ATTENDENCE_ABSCENT_REASON = "abscent_reason";
	public static final String TABLE_ATTENDENCE = "attendence_table";
	public static final String TABLE_ATTENDENCE_CREATE = "CREATE TABLE attendence_table(id INTEGER PRIMARY KEY AUTOINCREMENT,user_name INTEGER NOT NULL ,"
			+"abscent_date TEXT NOT NULL ,leave INTEGER NOT NULL, abscent_reason TEXT NOT NULL );";
	
	
	//TABLE BULLETTIN BOARD
	public static final String BULLETIN_ID="id";
	public static final String BULLETIN_USER_TYPE = "user_type";
	public static final String BULLETIN_TITLE = "title";
	public static final String BULLETIN_USER_ID= "user_name";
	public static final String BULLETIN_CONTENT = "content";
	public static final String BULLETIN_DATE = "date";
	public static final String BULLETIN_POST_TYPE = "post_type";
	public static final String BULLETIN_LIKE = "likes";
	public static final String TABLE_BULLETIN = "bulletin_table";
	public static final String TABLE_BULLETIN_CREATE = "CREATE TABLE bulletin_table(id INTEGER PRIMARY KEY AUTOINCREMENT ,user_type INTEGER NOT NULL ,title TEXT NOT NULL ,"
			+"user_name INTEGER NOT NULL,content TEXT NOT NULL,date TEXT NOT NULL,post_type INTEGER NOT NULL,likes INTEGER NOT NULL);";
	
	//TABLE COMMENTS
	public static final String COMMENTS_ID = "id";
	public static final String COMMENTS_USER_ID = "user_name";
	public static final String COMMENTS_POST_ID="post_id";
	public static final String COMMENTS_TEXT = "comment";
	public static final String COMMENTS_DATE_TIME = "comment_time";
	public static final String COMMENTS_LIKE = "likes";
	public static final String TABLE_COMMENTS = "comment_table";
	public static final String TABLE_COMMENTS_CREATE = "CREATE TABLE comment_table(id INTEGER PRIMARY KEY AUTOINCREMENT ,user_name INTEGER NOT NULL ,post_id INTEGER NOT NULL"
			+"comment TEXT NOT NULL ,comment_time DATETIME DEFAULT CURRENT_TIMESTAMP,likes INTEGER NOT NULL );";
	
	//TABLE FEE
	public static final String FEE_ID="id";
	public static final String FEE_USER_ID = "user_name";
	public static final String FEE_TUTION_FEE = "tution_fee";
	public static final String FEE_EXAM_FEE = "exam_fee";
	public static final String FEE_LAB_FEE = "lab_fee";
	public static final String FEE_BUS_FEE = "bus_fee";
	public static final String FEE_LIBRARY_FEE = "lib_fee";
	public static final String FEE_EXTRA_FEE ="extra_fee";
	public static final String TABLE_FEE="fee_table";
	public static final String TABLE_FEE_CREATE = "CREATE TABLE fee_table (id INTEGER PRIMARY KEY AUTOINCREMENT,user_name INTEGER NOT NULL ,tution_fee REAL NOT NULL ,"
			+"exam_fee REAL NOT NULL ,lab_fee REAL NOT NULL ,bus_fee REAL NOT NULL ,lib_fee REAL NOT NULL ,extra_fee REAL NOT NULL );";	


	DataBaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;

	public DataHandler(Context ctx){

		this.ctx = ctx;
		dbhelper = new DataBaseHelper(ctx);
	}

	public static class DataBaseHelper extends SQLiteOpenHelper{

		public DataBaseHelper(Context ctx) {
			super(ctx,DATA_BASE_NAME , null, DATA_BASE_VERSION);

		}

	
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(TABLE_USER_TYPE_CREATE);
				db.execSQL(TABLE_USER_CREATE);
				db.execSQL(TABLE_STUDENT_CREATE);
				db.execSQL(TABLE_EXAM_CREATE);
				db.execSQL(TABLE_ATTENDENCE_CREATE);
				db.execSQL(TABLE_BULLETIN_CREATE);
				db.execSQL(TABLE_COMMENTS_CREATE);
				db.execSQL(TABLE_FEE_CREATE);
								
			} catch (SQLException e) {
				Log.d("datasbase", "error");
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_ATTENDENCE);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_BULLETIN);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_COMMENTS);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_EXAM);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_FEE);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
			db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_TYPE);
			onCreate(db);

		}
	}
	public DataHandler open()
	{
		db =dbhelper.getWritableDatabase();
		return this;
	}

	public void close(){
		dbhelper.close();
	}

	//INSERT USER TYPE
	public long insetUserType(String type)
	{
		ContentValues cv = new ContentValues();
		cv.put(USER_TYPE, type);
		return db.insert(TABLE_USER_TYPE, null, cv);
	}

	//INSERT USER
	public long insertUser(Integer type,String user_name,String password,String name)
	{
		ContentValues cv = new ContentValues();
		cv.put(USER_TYPE_USER, type);
		cv.put(USER_NAME_ID, user_name);
		cv.put(USER_PASSWORD, password);
		cv.put(USER_NAME, name);
		return db.insert(TABLE_USER, null, cv);
	}

	//INSERT STUDENT
	public long insertStudent(Integer user_name,Integer standard,String division,String rollno,String student_email,String student_mobile,String student_gender,String dob,String parent_name,String parent_email,String parent_mobile,String student_pic){
		ContentValues cv = new ContentValues();
		cv.put(STUDENT_USER_NAME, user_name);
		cv.put(STUDENT_STANDARD, standard);
		cv.put(STUDENT_DIVISION,division);
		cv.put(STUDENT_ROLLNO, rollno);
		cv.put(STUDENT_EMAIL, student_email);
		cv.put(STUDENT_MOBILE, student_mobile);
		cv.put(STUDENT_GENDER, student_gender);
		cv.put(STUDENT_DOB, dob);
		cv.put(STUDENT_PARENT_NAME, parent_name);
		cv.put(STUDENT_PARENT_EMAIL, parent_email);
		cv.put(STUDENT_PARENT_MOBILE, parent_mobile);
		cv.put(STUDENT_PIC, student_pic);
		return db.insert(TABLE_STUDENT, null, cv);
	}

	//INSERT EXAM
	public long insertExam(Integer user_name,Integer type,Float sub1,Float max1,Float sub2,Float max2,Float sub3,Float max3,Float total){
		ContentValues cv = new ContentValues();
		cv.put(EXAM_USER_NAME, user_name);
		cv.put(EXAM_TYPE, type);
		cv.put(EXAM_SUB1, sub1);
		cv.put(EXAM_MAX1, max1);
		cv.put(EXAM_SUB2, sub2);
		cv.put(EXAM_MAX2, max2);
		cv.put(EXAM_SUB3, max2);
		cv.put(EXAM_SUB3, sub3);
		cv.put(EXAM_MAX3, max3);
		cv.put(EXAM_TOTAL, total);
		return db.insert(TABLE_EXAM,null,cv);
	}

	//INSERT ATTENDENCE
	public long inserAttendence(Integer user_name,String abscent_date,Integer leave,String abscent_reason){
		ContentValues cv = new ContentValues();
		cv.put(ATTENDENCE_USER, user_name);
		cv.put(ATTENDENCE_ABSCENT_DATE, abscent_date);
		cv.put(ATTENDENCE_LEAVE, leave);
		cv.put(ATTENDENCE_ABSCENT_REASON, abscent_reason);
		return db.insert(TABLE_ATTENDENCE,null,cv);
	}

	//INSERT BULLETIN
	public long insertBulletin(Integer user_type,String title,Integer user_name,String content,String date,Integer post_type,Integer like){
		ContentValues cv = new ContentValues();
		cv.put(BULLETIN_USER_TYPE, user_type);
		cv.put(BULLETIN_TITLE, title);
		cv.put(BULLETIN_USER_ID, user_name);
		cv.put(BULLETIN_CONTENT,content);
		cv.put(BULLETIN_DATE, date);
		cv.put(BULLETIN_POST_TYPE, post_type);
		cv.put(BULLETIN_LIKE, like);
		return db.insert(TABLE_BULLETIN,null,cv);
	}

	public long insertComments(Integer user_name,Integer post_id,String comment,Integer like){
		ContentValues cv = new ContentValues();
		cv.put(COMMENTS_USER_ID, user_name);
		cv.put(COMMENTS_POST_ID, post_id);
		cv.put(COMMENTS_TEXT,comment);
		cv.put(COMMENTS_LIKE, like);
		return db.insert(TABLE_COMMENTS,null,cv);
	}

	public long insertFee(Integer user_name,Float tution_fee,Float exam_fee,Float lab_fee,Float bus_fee,Float library_fee,Float extra_fee){
		ContentValues cv = new ContentValues();
		cv.put(FEE_USER_ID, user_name);
		cv.put(FEE_TUTION_FEE,tution_fee);
		cv.put(FEE_EXAM_FEE, exam_fee);
		cv.put(FEE_LAB_FEE, lab_fee);
		cv.put(FEE_BUS_FEE,bus_fee);
		cv.put(FEE_LIBRARY_FEE, library_fee);
		cv.put(FEE_EXTRA_FEE, extra_fee);
		return db.insert(TABLE_FEE, null, cv);
	}
	
	public Cursor returnUserType()
	{
		return db.query(TABLE_USER_TYPE, new String[]{USER_TYPE_ID , USER_TYPE },null, null, null, null, null);
	}
	
	public Cursor geUserByUsername(String username)
	{
		return db.query(TABLE_USER,new String[]{USER_ID,USER_NAME_ID,USER_NAME,USER_PASSWORD,USER_TYPE_USER},USER_NAME_ID+"=?",new String[]{username},null,null,null);
	}
	public Cursor geUserById(String id)
	{
		return db.query(TABLE_USER,new String[]{USER_ID,USER_NAME_ID,USER_NAME,USER_PASSWORD,USER_TYPE_USER},USER_ID+"=?",new String[]{id},null,null,null);
	}
	public Cursor getStudent(String id){
		return db.query(TABLE_STUDENT, new String[]{STUDENT_STANDARD,STUDENT_DIVISION,STUDENT_ROLLNO,STUDENT_PIC}, STUDENT_USER_NAME+"=?", new String[]{id},null,null,null);
	}
	//get admin notification
	public Cursor getAdminNoticeFromBulletin(){
		return db.query(TABLE_BULLETIN,new String[]{BULLETIN_TITLE,BULLETIN_CONTENT},BULLETIN_USER_TYPE+"=?",new String[]{"1"},null,null,null);
	}
	
	//get student_id class and division
	public Cursor getStudentByStandardDivision(String standard,String division)
	{
		return db.query(TABLE_STUDENT, new String[]{STUDENT_USER_NAME},STUDENT_STANDARD+"=? AND "+STUDENT_DIVISION+"=?",new String[]{standard,division},null,null,null);
	}
	//get student notices by user_ids
	public Cursor getNoticeByStudentIds(String ids)
	{
		return db.query(TABLE_BULLETIN,new String[]{BULLETIN_TITLE,BULLETIN_CONTENT},BULLETIN_USER_ID+"in ?",new String[]{ids},null,null,null);
	}
	
	public Cursor getNotices(String ids)
	{
		Log.d("idsss", ids);
		return db.query(TABLE_BULLETIN,new String[]{BULLETIN_TITLE,BULLETIN_CONTENT,BULLETIN_DATE,BULLETIN_LIKE},BULLETIN_USER_ID+"=1 OR "+BULLETIN_USER_ID+" in "+ids,null,null,null,BULLETIN_DATE+" DESC");
	}
	
	public String[] makeArray(Cursor crs,String coloumn){
		String[] array = new String[crs.getCount()];
		int i = 0;
		while(crs.moveToNext()){
		    String uname = crs.getString(crs.getColumnIndex(coloumn));
		    array[i] = uname;
		    i++;
		}
	    return array;
	}

}



