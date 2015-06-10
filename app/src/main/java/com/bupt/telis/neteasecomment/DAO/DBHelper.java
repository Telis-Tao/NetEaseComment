package com.bupt.telis.neteasecomment.DAO;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Telis on 2015/6/9.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int
            version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static final int VERSION = 1;
    public static final String DB_NAME = "comments.db";

    public static final String CREATE_USER = "create table user (_id varchar(16) primary key " +
            ", name varchar(16),voted BLOB,title varchar(16),locale varchar(16))";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBHelper", "database created");
        db.execSQL(CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
