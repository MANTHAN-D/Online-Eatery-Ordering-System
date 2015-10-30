package com.example.online_eatery_ordering_system;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter implements Serializable{

	public static final String KEY_ROWID="_id";
	public static final String KEY_NAME="name";
	public static final String KEY_NUMBER="price";
	public static final String TAG="DBAdapter";

	//public static final String DATABASE_TABLE_1="specialities";
	//public static final String DATABASE_TABLE_2="burgers";
	//public static final String DATABASE_TABLE_3="pizzas";
	//public static final String DATABASE_TABLE_4="sandwiches";

	
	private final Context mContext; 
    private SQLiteDatabase mDb; 
    private DataBaseHelper mDbHelper; 
 
	
	
	public DBAdapter(Context ctx){
		mContext=ctx;
		mDbHelper=new DataBaseHelper(mContext);
		}
	
	public DBAdapter createDatabase() throws SQLException  
    { 
        try  
        { 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            //Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
	
	public DBAdapter open() throws SQLException  
    { 
        try  
        { 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            mDb = mDbHelper.getReadableDatabase(); 
        }  
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 

    public void close()  
    { 
        mDbHelper.close(); 
    } 

    public Cursor getAllItems(String category){
		  return mDb.query(category,new String[] {KEY_ROWID,KEY_NAME,KEY_NUMBER},null,null,null,null,null);
	}

}
