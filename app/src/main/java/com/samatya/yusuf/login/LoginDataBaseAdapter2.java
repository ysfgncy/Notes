package com.samatya.yusuf.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginDataBaseAdapter2
{
		static final String DATABASE_NAME = "not.db";
		static final int DATABASE_VERSION = 1;
	    static final String TABLE_NAME = "NOTES";

		private static String TITLE = "title";
		private static String ID = "id";
		private static String NOTE = "not";



	public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"NOTES"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ "TITLE  text,NOTE text); ";


		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper2 dbHelper;


		public LoginDataBaseAdapter2(Context _context)
		{
			context = _context;
			dbHelper = new DataBaseHelper2(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		public LoginDataBaseAdapter2 open() throws SQLException
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}

		public void close()
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String title,String note)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("TITLE", title);
			newValues.put("NOTE",note);

			// Insert the row into your table
			db.insert("NOTES", null, newValues);
			Toast.makeText(context, "Not Başarıyla kaydedildi.!", Toast.LENGTH_LONG).show();
		}

		public int deleteEntry(String title)
		{
			//String id=String.valueOf(ID);
		    String where="TITLE=?";
		    int numberOFEntriesDeleted= db.delete("NOTES", where, new String[]{title}) ;
	       // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	

		public String getSinlgeEntry(String id)
		{
			Cursor cursor=db.query("NOTES", null, " ID=?", new String[]{id}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String baslik= cursor.getString(cursor.getColumnIndex("TITLE"));
			cursor.close();
			return baslik;
		}

		public void  updateEntry(String title,String note)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("TITLE", title);
			updatedValues.put("NOTE",note);

	        String where="TITLE = ?";
		    db.update("NOTES",updatedValues, where, new String[]{title});
		}

		public  ArrayList<HashMap<String, String>> notListele(){

			//Bu methodda ise tablodaki tüm değerleri alıyoruz
			//ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
			//Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
			//olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return).

			db = dbHelper.getReadableDatabase();
			String selectQuery = "SELECT * FROM " + TABLE_NAME;
			Cursor cursor = db.rawQuery(selectQuery, null);
			ArrayList<HashMap<String, String>> notlist = new ArrayList<HashMap<String, String>>();
			// looping through all rows and adding to list

			if (cursor.moveToFirst()) {
				do {
					HashMap<String, String> map = new HashMap<String, String>();
					for(int i=0; i<cursor.getColumnCount();i++)
					{
						map.put(cursor.getColumnName(i), cursor.getString(i));
					}

					notlist.add(map);
				} while (cursor.moveToNext());
			}
			db.close();
			// return kitap liste
			return notlist;
		}


}