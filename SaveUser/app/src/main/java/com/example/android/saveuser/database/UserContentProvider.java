package com.example.android.saveuser.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Android on 6/19/2017.
 */

public class UserContentProvider extends ContentProvider {

    private static final int USER = 100;
    private static final int USER_ID = 101;

    private UserDbHelper dbHelper;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        dbHelper = new UserDbHelper(getContext());
        return true;
    }

    public static UriMatcher buildUriMatcher() {
        String content = UserUriContract.CONTENT_AUTHORITY;

        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(content, UserUriContract.PATH_USER, USER);
        matcher.addURI(content, UserUriContract.PATH_USER+"/#",USER_ID);

        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor returnCursor;
        switch (uriMatcher.match(uri)) {
            case USER:
                returnCursor = db.query(
                        UserUriContract.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case USER_ID:
                long _id = ContentUris.parseId(uri);
                returnCursor = db.query(
                        UserUriContract.UserEntry.TABLE_NAME,
                        projection,
                        UserUriContract.UserEntry._ID + " =?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER:
                return UserUriContract.UserEntry.CONTENT_TYPE;
            case USER_ID:
                return UserUriContract.UserEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch (uriMatcher.match(uri)) {
            case USER:
                _id = db.insert(UserUriContract.UserEntry.TABLE_NAME, null, values);
                if(_id > 0) {
                    returnUri = UserUriContract.UserEntry.buildUserUri(_id);
                } else {
                    throw new UnsupportedOperationException("Unable to insert row into: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows;

        switch (uriMatcher.match(uri)) {
            case USER:
                rows = db.delete(UserUriContract.UserEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(selection == null || rows != 0 ) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows;

        switch (uriMatcher.match(uri)) {
            case USER:
                rows = db.update(UserUriContract.UserEntry.TABLE_NAME, values, selection, selectionArgs);;
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rows != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }
}
