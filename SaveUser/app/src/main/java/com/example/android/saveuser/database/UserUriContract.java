package com.example.android.saveuser.database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Android on 6/19/2017.
 */

public class UserUriContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.saveuser";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_USER = "user";

    public static final class UserEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_USER;

        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_USER;


        public static final String TABLE_NAME = "userTable";
        public static final String COLUMN_NAME = "userName";
        public static final String COLUMN_ADDRESS = "userAddress";
        public static final String COLUMN_EMAIL = "userEmail";
        public static final String COLUMN_IMG_URL = "userImgUrl";

        public static Uri buildUserUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
