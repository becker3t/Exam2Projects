package com.example.android.saveuser.user;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.example.android.saveuser.utils.RetrofitService;
import com.example.android.saveuser.database.UserUriContract;
import com.example.android.saveuser.entities.Result;
import com.example.android.saveuser.entities.User;
import com.example.android.saveuser.entities.UserInfo;
import com.example.android.saveuser.utils.UserInfoHelper;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Android on 6/19/2017.
 */

public class FetchUserPresenter implements FetchUserContract.Presenter {

    private static final String TAG = FetchUserPresenter.class.getSimpleName() + "_TAG";

    private UserInfoHelper helper;

    private RetrofitService service;

    private Context context;

    FetchUserContract.View view;

    public FetchUserPresenter(UserInfoHelper helper, RetrofitService service, FetchUserContract.View view, Context context) {
        this.helper = helper;
        this.service = service;
        this.view = view;
        this.context = context;
    }

    @Override
    public void getUser() {
        Call<User> call = service.getUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {
                if(response.isSuccessful()) {
                    User example = response.body();

                    for(Result r: example.getResults()) {
                        final String userImgUrl = r.getPicture().getMedium();
                        final String userName = helper.getNameString(r);
                        final String userAddress = helper.getAddressString(r);
                        final String userEmail = helper.getEmailString(r);

                        saveUser(new UserInfo(userImgUrl, userName, userAddress, userEmail));
                    }
                }
                else {
                    view.showDataErrorMessage();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<User> call, Throwable t) {
                //do stuff on failure
                view.showNetworkErrorMessage();
            }
        });
    }

    private void saveUser(UserInfo user) {
        ContentValues userContentValues = getUserContentValues(user);

        Uri genreInsertUri = context.getContentResolver().insert(UserUriContract.UserEntry.CONTENT_URI, userContentValues);

        long userRowId = ContentUris.parseId(genreInsertUri);

        if(userRowId > 0) {
            Toast.makeText(context, "Inserted User record. Id = " + userRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private ContentValues getUserContentValues(UserInfo user) {
        ContentValues value = new ContentValues();
        value.put(UserUriContract.UserEntry.COLUMN_NAME, user.getName());
        value.put(UserUriContract.UserEntry.COLUMN_ADDRESS, user.getAddress());
        value.put(UserUriContract.UserEntry.COLUMN_EMAIL, user.getEmail());
        value.put(UserUriContract.UserEntry.COLUMN_IMG_URL, user.getImageUrl());
        return value;
    }
}
