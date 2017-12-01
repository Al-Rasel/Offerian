package com.bynotech.offerian.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.bynotech.offerian.R;
import com.bynotech.offerian.interfaces.UserAuthRetrofitInterface;
import com.bynotech.offerian.models.DemoOtpCheck;
import com.bynotech.offerian.models.DemoOtpPayLoad;
import com.bynotech.offerian.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MobileVerification extends AppCompatActivity {
    private Retrofit retrofit;
    private UserAuthRetrofitInterface userAuthRetrofitInterface;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Mobile Verification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progressbarOtp);
        retrofit = RetrofitClient.getInstance(this);
        userAuthRetrofitInterface = retrofit.create(UserAuthRetrofitInterface.class);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            ;
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void otpCheckNetworkCall(View v) {

        progressBar.setVisibility(View.VISIBLE);
        userAuthRetrofitInterface.demoOtpCheck(new DemoOtpCheck("012345")).enqueue(new Callback<DemoOtpPayLoad>() {
            @Override
            public void onResponse(Call<DemoOtpPayLoad> call, Response<DemoOtpPayLoad> response) {
                if (response.isSuccessful()) {

                    SharedPreferences.Editor editor = getSharedPreferences("UserAuth", MODE_PRIVATE).edit();
                    editor.putString("login_status", String.valueOf(response.body().getStatus()));

                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DemoOtpPayLoad> call, Throwable t) {

                progressBar.setVisibility(View.GONE);


            }
        });

    }

}
