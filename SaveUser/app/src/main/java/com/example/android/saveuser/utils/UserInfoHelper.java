package com.example.android.saveuser.utils;

import android.content.Context;

import com.example.android.saveuser.R;
import com.example.android.saveuser.entities.Result;

/**
 * Created by Android on 6/19/2017.
 */

public class UserInfoHelper {

    Context context;

    public UserInfoHelper(Context context) {
        this.context = context;
    }

    public String getNameString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getApplicationContext().getString(R.string.user_name_label));
        builder.append(" ");
        builder.append(r.getName().getFirst());
        builder.append(" ");
        builder.append(r.getName().getLast());
        return builder.toString();
    }

    public String getAddressString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getApplicationContext().getString(R.string.user_address_label));
        builder.append(" ");
        builder.append(r.getLocation().getStreet());
        builder.append(", ");
        builder.append(r.getLocation().getCity());
        builder.append(", ");
        builder.append(r.getLocation().getState());
        builder.append(" ");
        builder.append(r.getLocation().getPostcode());
        return builder.toString();
    }

    public String getEmailString(Result r) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getApplicationContext().getString(R.string.user_email_label));
        builder.append(" ");
        builder.append(r.getEmail());
        return builder.toString();
    }
}
