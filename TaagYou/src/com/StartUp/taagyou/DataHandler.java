package com.StartUp.taagyou;

import java.io.ObjectOutputStream.PutField;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataHandler {


	DataBaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;

	public DataHandler(Context ctx){

		this.ctx = ctx;
		dbhelper = new DataBaseHelper(ctx);
	}

	public static class DataBaseHelper extends SQLiteOpenHelper{

		public DataBaseHelper(Context ctx) {
			super(ctx,Constants.DATA_BASE_NAME , null, Constants.DATA_BASE_VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(Constants.TABLE_ATTENDENCE_CREATE);
				db.execSQL(Constants.TABLE_BULLETIN_CREATE);
				db.execSQL(Constants.TABLE_COMMENTS_CREATE);
				db.execSQL(Constants.TABLE_EXAM_CREATE);
				db.execSQL(Constants.TABLE_FEE_CREATE);
				db.execSQL(Constants.TABLE_STUDENT_CREATE);
				db.execSQL(Constants.TABLE_USER_CREATE);
				db.execSQL(Constants.TABLE_USER_TYPE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_ATTENDENCE);
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_BULLETIN);
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_COMMENTS);
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_EXAM);
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_FEE);
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_STUDENT);
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_USER);
			db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_USER_TYPE);
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
		cv.put(Constants.USER_TYPE_USER, type);
		return db.insert(Constants.TABLE_USER_TYPE, null, cv);
	}

	//INSERT USER
	public long insertUser(Integer type,String name,String password)
	{
		ContentValues cv = new ContentValues();
		cv.put(Constants.USER_TYPE_USER, type);
		cv.put(Constants.USER_NAME, name);
		cv.put(Constants.USER_PASSWORD, password);
		return db.insert(Constants.TABLE_USER, null, cv);
	}

	//INSERT STUDENT
	public long insertStudent(Integer user_name,String name,Integer standard,String division,String rollno,String student_email,String student_mobile,String student_gender,String parent_name,String parent_email,String parent_mobile,String student_pic){
		ContentValues cv = new ContentValues();
		cv.put(Constants.STUDENT_USER_NAME, user_name);
		cv.put(Constants.STUDENT_NAME, name);
		cv.put(Constants.STUDENT_STANDARD, standard);
		cv.put(Constants.STUDENT_DIVISION,division);
		cv.put(Constants.STUDENT_ROLLNO, rollno);
		cv.put(Constants.STUDENT_EMAIL, student_email);
		cv.put(Constants.STUDENT_MOBILE, student_mobile);
		cv.put(Constants.STUDENT_GENDER, student_gender);
		cv.put(Constants.STUDENT_PARENT_NAME, parent_name);
		cv.put(Constants.STUDENT_PARENT_EMAIL, parent_email);
		cv.put(Constants.STUDENT_PARENT_MOBILE, parent_mobile);
		cv.put(Constants.STUDENT_PIC, student_pic);
		return db.insert(Constants.TABLE_STUDENT, null, cv);
	}

	//INSERT EXAM
	public long insertExam(Integer user_name,Integer type,Float sub1,Float max1,Float sub2,Float max2,Float sub3,Float max3,Float total){
		ContentValues cv = new ContentValues();
		cv.put(Constants.EXAM_USER_NAME, user_name);
		cv.put(Constants.EXAM_TYPE, type);
		cv.put(Constants.EXAM_SUB1, sub1);
		cv.put(Constants.EXAM_MAX1, max1);
		cv.put(Constants.EXAM_SUB2, sub2);
		cv.put(Constants.EXAM_MAX2, max2);
		cv.put(Constants.EXAM_SUB3, max2);
		cv.put(Constants.EXAM_SUB3, sub3);
		cv.put(Constants.EXAM_MAX3, max3);
		cv.put(Constants.EXAM_TOTAL, total);
		return db.insert(Constants.TABLE_EXAM,null,cv);
	}

	//INSERT ATTENDENCE
	public long inserAttendence(Integer user_name,String abscent_date,Integer leave,String abscent_reason){
		ContentValues cv = new ContentValues();
		cv.put(Constants.ATTENDENCE_USER, user_name);
		cv.put(Constants.ATTENDENCE_ABSCENT_DATE, abscent_date);
		cv.put(Constants.ATTENDENCE_LEAVE, leave);
		cv.put(Constants.ATTENDENCE_ABSCENT_REASON, abscent_reason);
		return db.insert(Constants.TABLE_ATTENDENCE,null,cv);
	}

	//INSERT BULLETIN
	public long insertBulletin(Integer user_type,String title,Integer user_name,String content,String date,Integer post_type,Integer like){
		ContentValues cv = new ContentValues();
		cv.put(Constants.BULLETIN_USER_TYPE, user_type);
		cv.put(Constants.BULLETIN_TITLE, title);
		cv.put(Constants.BULLETIN_USER_ID, user_name);
		cv.put(Constants.BULLETIN_CONTENT,content);
		cv.put(Constants.BULLETIN_DATE, date);
		cv.put(Constants.BULLETIN_POST_TYPE, post_type);
		cv.put(Constants.BULLETIN_LIKE, like);
		return db.insert(Constants.TABLE_BULLETIN,null,cv);
	}

	public long insertComments(Integer user_name,String comment,Integer like){
		ContentValues cv = new ContentValues();
		cv.put(Constants.COMMENTS_USER_ID, user_name);
		cv.put(Constants.COMMENTS_TEXT,comment);
		cv.put(Constants.COMMENTS_LIKE, like);
		return db.insert(Constants.TABLE_COMMENTS,null,cv);
	}

	public long insertFee(Integer user_name,Float tution_fee,Float exam_fee,Float lab_fee,Float bus_fee,Float library_fee,Float extra_fee){
		ContentValues cv = new ContentValues();
		cv.put(Constants.FEE_USER_ID, user_name);
		cv.put(Constants.FEE_TUTION_FEE,tution_fee);
		cv.put(Constants.FEE_EXAM_FEE, exam_fee);
		cv.put(Constants.FEE_LAB_FEE, lab_fee);
		cv.put(Constants.FEE_BUS_FEE,bus_fee);
		cv.put(Constants.FEE_LIBRARY_FEE, library_fee);
		cv.put(Constants.FEE_EXTRA_FEE, extra_fee);
		return db.insert(Constants.TABLE_FEE, null, cv);
	}





}



