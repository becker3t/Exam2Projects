package com.example.android.readusers.list;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.readusers.entities.Constants;
import com.example.android.readusers.entities.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 6/19/2017.
 */

public class UserListPresenter implements UserListContract.Presenter{

    private List<UserInfo> listUserInfo;

    private Context context;

    UserListContract.View view;

    public UserListPresenter(UserListContract.View view, Context context) {
        this.view = view;
        this.context = context;
        listUserInfo = new ArrayList<>();
    }

    public Bundle getDataBundle (int position) {
        Bundle myBundle = new Bundle();
        myBundle.putString(MainActivity.IMG_URL_TAG, listUserInfo.get(position).getImageUrl());
        myBundle.putString(MainActivity.NAME_TAG, listUserInfo.get(position).getName());
        myBundle.putString(MainActivity.ADDRESS_TAG, listUserInfo.get(position).getAddress());
        myBundle.putString(MainActivity.EMAIL_TAG, listUserInfo.get(position).getEmail());
        return myBundle;
    }

    @Override
    public void populateUserList() {
        Cursor cursor = context.getContentResolver().query(Constants.USER_CONTENT_URI, null, null, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                UserInfo user = new UserInfo();
                user.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Constants._ID)));
                user.setName(cursor.getString(cursor.getColumnIndexOrThrow(Constants.USER_COLUMN_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(Constants.USER_COLUMN_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Constants.USER_COLUMN_EMAIL)));
                user.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(Constants.USER_COLUMN_IMG_URL)));
                listUserInfo.add(user);

                Toast.makeText(context, user.toString(), Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            Toast.makeText(context, "Cursor is null", Toast.LENGTH_SHORT).show();
        }

        view.showUserList(listUserInfo);
    }
}
