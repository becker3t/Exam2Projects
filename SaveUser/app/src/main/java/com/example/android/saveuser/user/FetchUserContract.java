package com.example.android.saveuser.user;

import java.util.List;

/**
 * Created by Android on 6/19/2017.
 */

public interface FetchUserContract {

    interface View {
        void showDataErrorMessage();

        void showNetworkErrorMessage();
    }

    interface Presenter {
        void getUser();
    }
}
