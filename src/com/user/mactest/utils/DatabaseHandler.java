package com.user.mactest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.user.mactest.models.Attendance;
import com.user.mactest.models.Course;
import com.user.mactest.models.Element;
import com.user.mactest.models.Gruppa;
import com.user.mactest.models.Student;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

@SuppressLint("SimpleDateFormat")
public class DatabaseHandler extends SQLiteOpenHelper {
	
	// All Static variables
	// Database Version
	public static final int DATABASE_VERSION = 1;

	// Database Name
	String tableName = "";

	// Contacts table name
	public static final String DATABASE_NAME = "MACTest2";
	public static final String TABLE_ATTND = "attendance";

	// Contacts Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_STUDENT_ID = "studentid";
	public static final String KEY_GROUP_ID = "group_id";
	public static final String KEY_COURSE_ID = "course_id";
	public static final String KEY_DATE = "date";
	public static final String KEY_SUBJECT = "subject";
	public static final String KEY_SUBJECT_ID = "subjectid";
	public static final String KEY_PHOTO = "photo";
	public static final String KEY_ABS = "isabsent";
	private Context context;
	
	public DatabaseHandler(Context context, String tableName) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		this.tableName = tableName;
	}

	// Creating Tables
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + Student.class.getSimpleName() + " ("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, "
				+ KEY_GROUP_ID + " TEXT" + ");";
		db.execSQL(CREATE_CONTACTS_TABLE);
		String CREATE_GROUPS_TABLE = "CREATE TABLE IF NOT EXISTS " + Gruppa.class.getSimpleName() + " ("
		+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
		+ KEY_COURSE_ID + " TEXT" + ")";
		db.execSQL(CREATE_GROUPS_TABLE);
	String CREATE_NAMES_TABLE = "CREATE TABLE IF NOT EXISTS " + Course.class.getSimpleName() + "("
	+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
	+ KEY_GROUP_ID + " TEXT" + ")";
	db.execSQL(CREATE_NAMES_TABLE);

String CREATE_ATTND_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ATTND + "("
+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_STUDENT_ID + " INTEGER,"
+ KEY_GROUP_ID + " TEXT," + KEY_SUBJECT_ID + " INTEGER,"+KEY_DATE+" TEXT,"+KEY_PHOTO+" INTEGER,"+KEY_ABS+" INTEGER"+")";
db.execSQL(CREATE_ATTND_TABLE);



	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + tableName);

		// Create tables again
		onCreate(db);
	}

	public <E extends Element> void addElement(E element){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, element.getId()); // element Id
		values.put(KEY_NAME, element.getName()); // element Name
		
		if (element instanceof Student){
			Student student = (Student)element;
			values.put(KEY_GROUP_ID, student.getGroupId());
		}
		else if (element instanceof Gruppa){
			Gruppa gruppa = (Gruppa) element;
			values.put(KEY_COURSE_ID, gruppa.getCourseName());
		}
		db.insert(element.getClass().getSimpleName(), null, values);
		db.close();
	}
	
	public void clearDB(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE "+Student.class.getSimpleName());
		db.execSQL("DROP TABLE "+Gruppa.class.getSimpleName());
		db.execSQL("DROP TABLE "+Course.class.getSimpleName());
		db.execSQL("DROP TABLE "+TABLE_ATTND);
		db.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public <E extends Element> E getElement(Class<E> type, int id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(type.getSimpleName(), new String[] { KEY_ID,
				KEY_NAME }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		
		
		if (type == Student.class){
			return (E) new Student(cursor.getInt(0),
				cursor.getString(1), cursor.getInt(2));
		} else if (type == Gruppa.class){
			return (E) new Gruppa(cursor.getInt(0),
					cursor.getString(1), cursor.getInt(2));
		}
			return (E) new Element(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		
	}
	@SuppressWarnings("unchecked")
	public <E extends Element> List<E> getAll(Class<E> type) {
		List<E> contactList = new ArrayList<E>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + type.getSimpleName() + " ORDER BY name";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				if (type == Student.class){
					Student student = new Student(
							cursor.getInt(0),
							cursor.getString(1),
							cursor.getInt(2)
						);
					contactList.add((E)student);
					
				}else {
					Element element = new Element(
							cursor.getInt(0),
							cursor.getString(1)
						);
					contactList.add((E)element);
				}
			} while (cursor.moveToNext());
			cursor.close(); //just now
		}

		db.close();
		// return contact list
		return contactList;
	}
	
	public <E extends Element> int updateElement(E element) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, element.getId());
		values.put(KEY_NAME, element.getName());
		if (element instanceof Student)
			values.put(KEY_GROUP_ID, ((Student)element).getGroupId());
		// updating row
		return db.update(tableName, values, KEY_ID + " = ?",
				new String[] { String.valueOf(element.getId()) });
	}

	public <E extends Element> void deleteElement(E element) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tableName, KEY_ID + " = ?",
				new String[] { String.valueOf(element.getId()) });
		db.close();
	}
	

	
	public int getElementsCount(Class<Element> type) {
		String countQuery = "SELECT  * FROM " + type.getSimpleName();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	public void addAttendance(Attendance attendance){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_ID, attendance.getId()); // Contact Name
		values.put(KEY_NAME, attendance.getStudent().getName());
		values.put(KEY_GROUP_ID, attendance.getStudent().getGroupId());
		values.put(KEY_SUBJECT, attendance.getCourse().getName());
		values.put(KEY_DATE, attendance.getDate().toString());
		values.put(KEY_PHOTO, attendance.getPhoto().hashCode());
		values.put(KEY_ABS, attendance.isAbsent()?1:0);
		
		db.insert(TABLE_ATTND, null, values);
		db.close(); // Closing database connection
	
	}

	public Attendance getAttendance(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ATTND, new String[] { KEY_ID,
				KEY_NAME }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		
		int studentId = cursor.getInt(1);
		Student student = getElement(Student.class, studentId);
		Course course = getElement(Course.class, cursor.getInt(3));
		Calendar calendar = Calendar.getInstance();
		Date date;
		try {
			date = new SimpleDateFormat().parse(cursor.getString(2));
			calendar.setTime(date);
			Bitmap photo = BitmapFactory.decodeFile( context.getFilesDir().getAbsolutePath()+"/"+cursor.getInt(4)+".jpg");
			
			return new Attendance(id, 
					student, 
					calendar, 
					course, 
					photo, cursor.getInt(5)==1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Attendance> getAllAttendances() {
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ATTND;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				int studentId = cursor.getInt(1);
				Student student = getElement(Student.class, studentId);
				Course course = getElement(Course.class, cursor.getInt(3));
				Calendar calendar = Calendar.getInstance();
				Date date;
				try {
					date = new SimpleDateFormat().parse(cursor.getString(2));
					calendar.setTime(date);
					Bitmap photo = BitmapFactory.decodeFile( context.getFilesDir().getAbsolutePath()+"/"+cursor.getInt(4)+".jpg");
					
					attendanceList.add(new Attendance(cursor.getInt(0), 
							student, 
							calendar, 
							course, 
							photo, cursor.getInt(5)==1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} while (cursor.moveToNext());
		}

		// return contact list
		return attendanceList;
	}
	
	public List<Gruppa> getGroupsByCourse(int courseId){
		List<Gruppa> list = new ArrayList<Gruppa>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(Gruppa.class.getSimpleName(), 
								new String[]{KEY_ID, KEY_NAME}, 
								KEY_GROUP_ID, 
								new String[]{String.valueOf(courseId)},
								null, null, null);
		if (cursor.moveToFirst()) {
			do {
					Gruppa gruppa = new Gruppa(
							cursor.getInt(0),
							cursor.getString(1),
							cursor.getInt(2)
						);
					list.add(gruppa);
				
			} while (cursor.moveToNext());
			cursor.close(); //just now
		}
		return list;
	}
	
	public List<Student> getStudentsByGroup(int groupId){
		List<Student> list = new ArrayList<Student>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(Gruppa.class.getSimpleName(), 
								new String[]{KEY_ID, KEY_NAME}, 
								KEY_GROUP_ID, 
								new String[]{String.valueOf(groupId)},
								null, null, null);
		if (cursor.moveToFirst()) {
			do {
					Student student = new Student(
							cursor.getInt(0),
							cursor.getString(1),
							cursor.getInt(2)
						);
					list.add(student);
				
			} while (cursor.moveToNext());
			cursor.close(); //just now
		}
		return list;
	}
}
