package com.StartUp.taagyou;

public class Constants {
	
	//Data base name and version
	
		public static final String DATA_BASE_NAME ="TaagYou.db";
		public static final int DATA_BASE_VERSION = 2;
		
		//TABLE USER TYPE
		public static final String USER_TYPE_ID = "id";
		public static final String USER_TYPE ="user_type";
		public static final String TABLE_USER_TYPE ="user_type_table";
		public static final String TABLE_USER_TYPE_CREATE="CREATE TABLE "+TABLE_USER_TYPE+" ( "+USER_TYPE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_TYPE+" TEXT NOT NULL "+");";
		
		//TABLE_USER
		public static final String  USER_ID ="id";
		public static final String USER_TYPE_USER ="user_type";
		public static final String USER_NAME ="user_name";
		public static final String USER_PASSWORD ="password";
		public static final String TABLE_USER = "user_table";
		public static final String TABLE_USER_CREATE = "CREATE TABLE "+TABLE_USER+" ( "+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_TYPE+" INTEGER NOT NULL, "+USER_NAME+" TEXT UNIQUE, "+USER_PASSWORD+" TEXT NOT NULL "+");";

		//TABLE STUDENT
		public static final String STUDENT_ID ="id";
		public static final String STUDENT_USER_NAME = "user_name";
		public static final String STUDENT_NAME = "name";
		public static final String STUDENT_STANDARD = "standard";
		public static final String STUDENT_DIVISION ="division";
		public static final String STUDENT_ROLLNO = "roll_number";
		public static final String STUDENT_EMAIL = "student_email";
		public static final String STUDENT_MOBILE = "student_mobile";
		public static final String STUDENT_GENDER = "gender";
		public static final String STUDENT_PARENT_NAME="parent_name";
		public static final String STUDENT_PARENT_EMAIL="parent_email";
		public static final String STUDENT_PARENT_MOBILE="parent_mobile";
		public static final String STUDENT_PIC = "image";
		public static final String TABLE_STUDENT = "student_table";
		public static final String TABLE_STUDENT_CREATE = "CREATE TABLE "+TABLE_STUDENT+" ("+STUDENT_ID+"INTEGER PRIMARY KEY AUTOINCREMENT ,"+STUDENT_USER_NAME+" INTEGER NOT NULL, "
				+STUDENT_NAME+"TEXT NOT NULL, "+STUDENT_STANDARD+"INTEGER NOT NULL ,"+STUDENT_DIVISION+" TEXT NOT NULL,"+STUDENT_ROLLNO+" TEXT NOT NULL,"+STUDENT_EMAIL+" TEXT NOT NULL,"+
				STUDENT_MOBILE+"TEXT NOT NULL, "+STUDENT_GENDER+" INTEGER NOT NULL, "+STUDENT_PARENT_NAME+" TEXT NOT NULL, "+STUDENT_PARENT_EMAIL+" TEXT NOT NULL, "
				+STUDENT_PIC+" TEXT NOT NULL "+");";
		
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
		public static final String TABLE_EXAM_CREATE = "CREATE TABLE "+TABLE_EXAM+" ("+EXAM_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ," + EXAM_USER_NAME+" INTEGER NOT NULL,"+EXAM_TYPE+" INTEGER NOT NULL,"+EXAM_SUB1+"REAL NOT NULL, "+EXAM_MAX1+" REAL NOT NULL, "
				+EXAM_SUB2+" REAL NOT NULL, "+EXAM_MAX2+" REAL NOT NULL, "+EXAM_SUB3+" REAL NOT NULL, "+EXAM_MAX3+" REAL NOT NULL , "+EXAM_TOTAL+" REAL NOT NULL"+");";
		
		//TABLE ATTENDENCE
		public static final String ATTENDENCE_ID = "id";
		public static final String ATTENDENCE_USER = "user_name";
		public static final String ATTENDENCE_ABSCENT_DATE = "abscent_date";
		public static final String ATTENDENCE_LEAVE = "leave";
		public static final String ATTENDENCE_ABSCENT_REASON = "abscent_reason";
		public static final String TABLE_ATTENDENCE = "attendence_table";
		public static final String TABLE_ATTENDENCE_CREATE = "CREATE TABLE "+TABLE_ATTENDENCE+" ("+ATTENDENCE_ID+" INTENGER PRIMARY KEY AUTOINCREMENT ,"+ATTENDENCE_USER+" INTEGER NOT NULL ,"
				+ATTENDENCE_ABSCENT_DATE+" TEXT NOT NULL ,"+ATTENDENCE_LEAVE+" INTEGER NOT NULL, "+ATTENDENCE_ABSCENT_REASON+" TEXT NOT NULL );";
		
		
		//TABLE BULLETTIN BOARD
		public static final String BULLETIN_ID="id";
		public static final String BULLETIN_USER_TYPE = "user_type";
		public static final String BULLETIN_TITLE = "title";
		public static final String BULLETIN_USER_ID= "user_name";
		public static final String BULLETIN_CONTENT = "content_name";
		public static final String BULLETIN_DATE = "date";
		public static final String BULLETIN_POST_TYPE = "post_type";
		public static final String BULLETIN_LIKE = "like";
		public static final String TABLE_BULLETIN = "bulletin_table";
		public static final String TABLE_BULLETIN_CREATE = "CREATE TABLE "+TABLE_BULLETIN+" ("+BULLETIN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+BULLETIN_USER_TYPE+" INTEGER NOT NULL ,"+BULLETIN_TITLE+" TEXT NOT NULL ,"
				+BULLETIN_USER_ID+" INTEGER NOT NULL,"+BULLETIN_CONTENT+" TEXT NOT NULL,"+BULLETIN_DATE+" TEXT NOT NULL,"+BULLETIN_POST_TYPE+" INTEGER NOT NULL, "+BULLETIN_LIKE+" INTEGER NOT NULL);";
		
		//TABLE COMMENTS
		public static final String COMMENTS_ID = "id";
		public static final String COMMENTS_USER_ID = "user_id";
		public static final String COMMENTS_TEXT = "comment";
		public static final String COMMENTS_DATE_TIME = "comment_time";
		public static final String COMMENTS_LIKE = "likes";
		public static final String TABLE_COMMENTS = "comment_table";
		public static final String TABLE_COMMENTS_CREATE = "CREATE TABLE "+TABLE_COMMENTS+" ("+COMMENTS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+COMMENTS_USER_ID+" INTEGER NOT NULL ,"
				+COMMENTS_TEXT+" TEXT NOT NULL ,"+COMMENTS_DATE_TIME+"  DATETIME DEFAULT CURRENT_TIMESTAMP,"+COMMENTS_LIKE+"INTEGER NOT NULL ,"+");";
		
		//TABLE FEE
		public static final String FEE_ID="id";
		public static final String FEE_USER_ID = "user_id";
		public static final String FEE_TUTION_FEE = "tution_fee";
		public static final String FEE_EXAM_FEE = "exam_fee";
		public static final String FEE_LAB_FEE = "lab_fee";
		public static final String FEE_BUS_FEE = "bus_fee";
		public static final String FEE_LIBRARY_FEE = "lib_fee";
		public static final String FEE_EXTRA_FEE ="extra_fee";
		public static final String TABLE_FEE="fee_table";
		public static final String TABLE_FEE_CREATE = "CREATE TABLE "+TABLE_FEE+" ( "+FEE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FEE_USER_ID+" INTEGER NOT NULL ,"+FEE_TUTION_FEE+" REAL NOT NULL ,"
				+FEE_EXAM_FEE+" REAL NOT NULL ,"+FEE_LAB_FEE+" REAL NOT NULL ,"+FEE_BUS_FEE+" REAL NOT NULL ,"+FEE_LIBRARY_FEE+" REAL NOT NULL ,"+FEE_EXTRA_FEE+" REAL NOT NULL );";	
		
}
