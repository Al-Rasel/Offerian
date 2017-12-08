package com.bynotech.offerian.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bynotech.offerian.R;
import com.bynotech.offerian.interfaces.UserAuthRetrofitInterface;
import com.bynotech.offerian.models.SignUpPayLoad;
import com.bynotech.offerian.models.UserSignInRequestBody;
import com.bynotech.offerian.network.RetrofitClient;
import com.google.firebase.iid.FirebaseInstanceId;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActiivty extends AppCompatActivity {

    private Retrofit retrofit;
    private UserAuthRetrofitInterface userAuthRetrofitInterface;
    ProgressBar progressBar;

    EditText userMobileNumber, userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_actiivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LogIn Now");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressbarSignIn);
        retrofit = RetrofitClient.getInstance(this);
        userAuthRetrofitInterface = retrofit.create(UserAuthRetrofitInterface.class);

        userMobileNumber = (EditText) findViewById(R.id.et_mobileNumber);
        userPassword = (EditText) findViewById(R.id.et_password);


    }

    public void signInCheck(View v) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    101);
        } else {

            signInUser();
        }
    }


    public void signInUser() {

        if (!String.valueOf(userPassword.getText().toString()).equals("null")
                || !String.valueOf(userPassword.getText().toString()).equals("")
                || !String.valueOf(userPassword.getText().toString()).isEmpty()
                || !String.valueOf(userMobileNumber.getText().toString()).equals("null")
                || !String.valueOf(userMobileNumber.getText().toString()).equals("")
                || !String.valueOf(userMobileNumber.getText().toString()).isEmpty()

                ) {


            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;


            UserSignInRequestBody userSignInRequestBody = new UserSignInRequestBody(userMobileNumber.getText().toString(), userPassword.getText().toString(),


                    getLocalIpAddress(), "android", String.valueOf(android.os.Build.VERSION.SDK_INT),


                    String.valueOf(getDeviceName()), String.valueOf(getDeviceName()), String.valueOf(getDeviceName()), telephonyManager.getDeviceId(), FirebaseInstanceId.getInstance().getToken(), String.valueOf(telephonyManager.getSimOperatorName()), String.valueOf(height) + "," + String.valueOf(width));


            userAuthRetrofitInterface.userSignInNetworkCall(userSignInRequestBody).enqueue(new Callback<SignUpPayLoad>() {
                @Override
                public void onResponse(Call<SignUpPayLoad> call, Response<SignUpPayLoad> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SignInActiivty.this, String.valueOf(response.body().getSession_id()), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SignUpPayLoad> call, Throwable t) {

                }
            });
        }


    }


    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.


                    signInUser();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
