package com.example.android.readusers.details;

import android.os.Bundle;

/**
 * Created by Android on 6/19/2017.
 */

public interface UserDetailsContract {
    interface View {
        void DisplayUserData(String url, String name, String address, String email);

        void ErrorDisplayingUserData();
    }

    interface Presenter {
        void GetUserData(Bundle b);
    }
}
