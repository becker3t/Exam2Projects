package com.example.android.saveuser.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.saveuser.database.UserUriContract;

/**
 * Created by Android on 6/19/2017.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        addUserTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addUserTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + UserUriContract.UserEntry.TABLE_NAME + " (" +
                        UserUriContract.UserEntry._ID + " INTEGER PRIMARY KEY, " +
                        UserUriContract.UserEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        UserUriContract.UserEntry.COLUMN_ADDRESS + " TEXT UNIQUE NOT NULL, " +
                        UserUriContract.UserEntry.COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, " +
                        UserUriContract.UserEntry.COLUMN_IMG_URL + " TEXT UNIQUE NOT NULL);"
        );
    }
}
