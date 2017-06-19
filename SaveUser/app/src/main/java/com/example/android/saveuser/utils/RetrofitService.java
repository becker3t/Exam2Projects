package com.example.android.saveuser.utils;

import com.example.android.saveuser.entities.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Android on 6/19/2017.
 */

public interface RetrofitService {

    @GET("api")
    Call<User> getUser();

}
