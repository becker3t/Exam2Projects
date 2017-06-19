package com.example.android.readusers.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.readusers.R;
import com.example.android.readusers.details.DetailsActivity;
import com.example.android.readusers.entities.UserInfo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListContract.View, UserRecyclerAdapter.ListItemClickListener{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    public static final String IMG_URL_TAG = "image_url";
    public static final String NAME_TAG = "name";
    public static final String ADDRESS_TAG = "address";
    public static final String EMAIL_TAG = "email";

    UserListPresenter presenter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter = new UserListPresenter(this, getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.populateUserList();
    }

    @Override
    public void showRetrieveDataErrorMessage() {
        Log.d(TAG, "onFailure: Error retrieving network data.");
        Toast.makeText(this, "Error retrieving data.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserList(List<UserInfo> listInfo) {
        updateRecyclerAdapter(listInfo);
    }

    private void updateRecyclerAdapter(List<UserInfo> list) {
        UserRecyclerAdapter recyclerAdapter = new UserRecyclerAdapter(list, this);
        recyclerAdapter.setListItemClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    //For list item click
    @Override
    public void OnItemClick(View v, int position) {
        Intent i = new Intent(MainActivity.this, DetailsActivity.class);
        i.putExtras(presenter.getDataBundle(position));
        startActivity(i);
    }
}
