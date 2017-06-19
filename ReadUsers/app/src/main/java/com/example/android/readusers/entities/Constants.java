package com.example.android.readusers.entities;

import android.net.Uri;

/**
 * Created by Android on 6/19/2017.
 */

public class Constants {
    /**
     * Is the name for the entire ContentProvider
     */
    private static final String CONTENT_AUTHORITY = "com.mac.training.movieapp";

    /**
     *
     */
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     *
     */
    private static final String PATH_USER = "user";

    /**
     *
     */
    public static final Uri USER_CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_USER).build();

    /**
     *
     */
    public static final String _ID = "_id";

    /**
     *
     */
    public static final String GENRE_COLUMN_NAME = "genreName";

    /**
     *
     */
    public static final String USER_COLUMN_NAME = "userName";

    /**
     *
     */
    public static final String USER_COLUMN_ADDRESS = "userAddress";

    /**
     *
     */
    public static final String USER_COLUMN_EMAIL = "userEmail";

    public static final String USER_COLUMN_IMG_URL = "userImgUrl";
}
