package com.example.android.readusers.list;

import com.example.android.readusers.entities.UserInfo;

import java.util.List;

/**
 * Created by Android on 6/19/2017.
 */

public interface UserListContract {

    interface View {
        void showRetrieveDataErrorMessage();

        void showUserList(List<UserInfo> listInfo);
    }

    interface Presenter {
        void populateUserList();
    }
}
