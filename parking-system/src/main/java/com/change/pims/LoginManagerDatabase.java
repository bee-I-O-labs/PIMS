package com.change.pims;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: STEEL
 * Date: 14/09/13
 * Time: 01:38
 * To change this template use File | Settings | File Templates.
 */
public class LoginManagerDatabase extends SQLiteOpenHelper{

    private final static String DB_NAME = "PIMS";
    private final static int DB_VERSION = 1;

    private static final String TABLE_NAME = "LoginManager";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "create table " + TABLE_NAME
                    + " (" + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_USERNAME + " text, " + COLUMN_PASSWORD + " text)";

    public LoginManagerDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DATABASE_CREATE);
        }catch(SQLException e){
            Log.e("Database Create Error", "An Error occured while creating your table", new SQLException());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    /**
     * Adds a new user to the database...
     *
     * @param uName this is new user's user name
     * @param pass this is new user's password
     */
    public void registerUser(String uName, String pass) throws SQLException{

        SQLiteDatabase db = this.getWritableDatabase();
        try{
            if(db == null){
                Log.e("Error", "null database");
            }
            else{
                Log.e("Safe", "not null database");
            }
            ContentValues cv = new ContentValues();
            cv.put(LoginManagerDatabase.COLUMN_USERNAME, uName);
            cv.put(LoginManagerDatabase.COLUMN_PASSWORD , pass);
            db.insertOrThrow(LoginManagerDatabase.TABLE_NAME, null, cv);

        }catch(SQLException e){
            Log.e("message", "insert action");
        }finally{
            if(db != null){
                db.close();
            }
        }

    }

    /**
     *Enables a user change their password
     *
     * @param uName this is user's user name
     * @param pass this is user's new password
     */
    public void changePassword(String uName, String pass) throws SQLException{
        SQLiteDatabase db = this.getWritableDatabase();

        try{

            if(db == null){
                Log.e("Error", "null database");
            }
            else{
                Log.e("Safe", "not null database");
            }
            ContentValues cv = new ContentValues();
            cv.put(LoginManagerDatabase.COLUMN_PASSWORD, pass);
            db.update(LoginManagerDatabase.TABLE_NAME, cv, LoginManagerDatabase.COLUMN_USERNAME
                    + "=" + uName, null);

        }catch(SQLException e){
            Log.e("message", "Error...............................", new SQLException());
        }finally{
            if(db != null){
                db.close();
            }
        }
    }

    /**
     * Gets the list of all the User names
     */
    public String[] getUsernames(){

        SQLiteDatabase db  = this.getReadableDatabase();
        try{
            String[]list;

            Cursor c = db.rawQuery("SELECT " + LoginManagerDatabase.COLUMN_USERNAME + " FROM "
                    + LoginManagerDatabase.TABLE_NAME , null);

            if(c != null){
                Log.e("Cursor Working", "the number of rows is " + c.getCount());
            }

            list = new String[c.getCount()];
            c.moveToFirst();

            int i = 0;
            while(!c.isLast()){
                list[i] = c.getString(c.getColumnIndex(COLUMN_USERNAME));
                c.moveToNext();
                i++;
            }
            return list;
        }catch (SQLException e) {
            Log.e("message", "Error...............................", new SQLException());
        }finally{
            if(db != null){
                db.close();
            }
        }
        return null;
    }

    /**
     * Gets the list of all the passwords
     */
    public String[] getPasswords(){

        SQLiteDatabase db  = this.getReadableDatabase();

        try{
            String[] list;

            Cursor c = db.rawQuery("SELECT " + LoginManagerDatabase.COLUMN_PASSWORD +" FROM "
                    + LoginManagerDatabase.TABLE_NAME , null);

            if(c != null){
                Log.e("Cursor Working", "the number of rows is " + c.getCount());
            }

            list = new String[c.getCount()];
            c.moveToFirst();

            int i = 0;
            while(!c.isLast()){
                list[i] = c.getString(c.getColumnIndex(COLUMN_PASSWORD));
                c.moveToNext();
                i++;
                return list;
            }
        }catch(SQLException e){
            Log.e("message", "Error...............................", new SQLException());
        }finally{
            if(db != null){
                db.close();
            }
        }
        return null;
    }

}