package com.example.android.saveuser.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.saveuser.R;
import com.example.android.saveuser.utils.RetrofitService;
import com.example.android.saveuser.utils.UserInfoHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FetchUserContract.View{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    private static final String RETROFIT_BASE_URL ="https://randomuser.me/";

    FetchUserPresenter presenter;

    Button saveUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveUserBtn = (Button) findViewById(R.id.buttonSaveUser);
        saveUserBtn.setOnClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RETROFIT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);

        presenter = new FetchUserPresenter(new UserInfoHelper(this), service, this, getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSaveUser:
                presenter.getUser();
                break;
        }
    }

    @Override
    public void showDataErrorMessage() {
        Log.d(TAG, "onFailure: Error retrieving network data.");
        Toast.makeText(this, "Error retrieving data.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        Log.d(TAG, "onFailure: Error connecting to network.");
        Toast.makeText(this, "Network error. Check internet settings", Toast.LENGTH_SHORT).show();
    }
}
