package com.example.online_eatery_ordering_system;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	public DataBaseHelper(Context context)  
	{ 
	    super(context, DB_NAME, null, DB_VERSION); 
	    DB_PATH = context.getApplicationInfo().dataDir + "/databases/"; 
	    this.mContext = context; 
	}
	public void createDataBase() throws IOException 
	{ 
	    //If database not exists copy it from the assets 
	 
	    boolean mDataBaseExist = checkDataBase(); 
	    if(!mDataBaseExist) 
	    { 
	        this.getReadableDatabase(); 
	        this.close(); 
	        try  
	        { 
	            //Copy the database from assests 
	            copyDataBase(); 
	            //Log.e(TAG, "createDatabase database created"); 
	        }  
	        catch (IOException mIOException)  
	        { 
	            throw new Error("ErrorCopyingDataBase"); 
	        } 
	    } 
	} 
	    //Check that the database exists here: /data/data/your package/databases/Da Name 
	    private boolean checkDataBase() 
	    { 
	        File dbFile = new File(DB_PATH + DB_NAME); 
	        //Log.v("dbFile", dbFile + "   "+ dbFile.exists()); 
	        return dbFile.exists(); 
	    } 
	 
	    //Copy the database from assets 
	    private void copyDataBase() throws IOException 
	    { 
	        InputStream mInput = mContext.getAssets().open(DB_NAME); 
	        String outFileName = DB_PATH + DB_NAME; 
	        OutputStream mOutput = new FileOutputStream(outFileName); 
	        byte[] mBuffer = new byte[1024]; 
	        int mLength; 
	        while ((mLength = mInput.read(mBuffer))>0) 
	        { 
	            mOutput.write(mBuffer, 0, mLength); 
	        } 
	        mOutput.flush(); 
	        mOutput.close(); 
	        mInput.close(); 
	    } 

	    public boolean openDataBase() throws SQLException 
	    { 
	        String mPath = DB_PATH + DB_NAME; 
	        //Log.v("mPath", mPath); 
	        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
	        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS); 
	        return mDataBase != null; 
	    }

	@Override 
	public synchronized void close()  
	{ 
	        if(mDataBase != null) 
	            mDataBase.close(); 
	        super.close(); 
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	private static String DB_PATH = "";  
	private static String DB_NAME ="menu_v1"; 
	private SQLiteDatabase mDataBase;  
	private final Context mContext; 
	private final static int DB_VERSION=1;
	    
}