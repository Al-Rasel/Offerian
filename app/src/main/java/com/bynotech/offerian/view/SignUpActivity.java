package com.bynotech.offerian.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bynotech.offerian.R;
import com.bynotech.offerian.interfaces.UserAuthRetrofitInterface;
import com.bynotech.offerian.models.DistrictPayLoad;
import com.bynotech.offerian.models.SignUpPayLoad;
import com.bynotech.offerian.models.SignupRequestBody;
import com.bynotech.offerian.network.RetrofitClient;
import com.google.firebase.iid.FirebaseInstanceId;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private UserAuthRetrofitInterface userAuthRetrofitInterface;

    private EditText editTextFullName, editTextMobileNumber, editTextPassword, editTextReference;
    private List<DistrictPayLoad> districtPayLoads;
    Spinner spinnerArea, spinnerGender;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Signup Now");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressbarSignUp);
        retrofit = RetrofitClient.getInstance(this);
        userAuthRetrofitInterface = retrofit.create(UserAuthRetrofitInterface.class);

        spinnerGender = (Spinner) findViewById(R.id.spnerGender);
        String[] years = {"Male", "Female"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_text, years);
        langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinnerGender.setAdapter(langAdapter);
        getAllDistricts();


    }


    private void findViews() {
        editTextFullName = (EditText) findViewById(R.id.et_name);
        editTextMobileNumber = (EditText) findViewById(R.id.et_mobileNumber);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        editTextReference = (EditText) findViewById(R.id.et_referance);
    }


    private void getAllDistricts() {
        userAuthRetrofitInterface.getAllDistrict().enqueue(new Callback<List<DistrictPayLoad>>() {
            @Override
            public void onResponse(Call<List<DistrictPayLoad>> call, Response<List<DistrictPayLoad>> response) {
                if (response.isSuccessful()) {
                    districtPayLoads = response.body();

                    String[] areas = new String[response.body().size()];

                    for (int i = 0; i < response.body().size(); i++) {
                        areas[i] = response.body().get(i).getName_en();
                    }

                    ArrayAdapter<CharSequence> areaAdapter = new ArrayAdapter<CharSequence>(getApplicationContext(), R.layout.spinner_text, areas);
                    areaAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                    spinnerArea = (Spinner) findViewById(R.id.spinerArea);
                    spinnerArea.setAdapter(areaAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<DistrictPayLoad>> call, Throwable t) {

            }
        });
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

    public void signUp(View v) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    101);
        } else {

            onSubmitRegestirationPaper();
        }
    }


    public void onSubmitRegestirationPaper() {
        progressBar.setVisibility(View.VISIBLE);

        if (!editTextFullName.getText().toString().isEmpty() && !String.valueOf(editTextFullName.getText().toString()).equals("null")
                &&
                !editTextMobileNumber.getText().toString().isEmpty() && !String.valueOf(editTextMobileNumber.getText().toString()).equals("null")
                &&
                !editTextPassword.getText().toString().isEmpty() && !String.valueOf(editTextPassword.getText().toString()).equals("null")

                ) {
            int genderID = spinnerGender.getSelectedItem().toString().equals("male") ? 1 : 2;
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;


            SignupRequestBody signupRequestBody = new SignupRequestBody(editTextFullName.getText().toString(), editTextMobileNumber.getText().toString(),

                    Integer.parseInt(districtPayLoads.get(spinnerArea.getSelectedItemPosition()).getBd_district_id()), genderID,
                    editTextPassword.getText().toString(), String.valueOf(editTextReference.getText().toString()),
                    getLocalIpAddress(), "android", String.valueOf(android.os.Build.VERSION.SDK_INT),


                    String.valueOf(getDeviceName()), String.valueOf(getDeviceName()), String.valueOf(getDeviceName()), telephonyManager.getDeviceId(), FirebaseInstanceId.getInstance().getToken(), String.valueOf(telephonyManager.getSimOperatorName()), String.valueOf(height) + "," + String.valueOf(width));

            Log.e("checkValueess", "onSubmitRegestirationPaper: " + String.valueOf(signupRequestBody));
            userAuthRetrofitInterface.signUpNetworkCall(signupRequestBody).enqueue(new Callback<SignUpPayLoad>() {
                @Override
                public void onResponse(Call<SignUpPayLoad> call, Response<SignUpPayLoad> response) {
                    if (response.isSuccessful()) {


                        startActivity(new Intent(getApplicationContext(), MobileVerification.class));


                        //  Toast.makeText(SignUpActivity.this, String.valueOf(response.body().getSession_id()), Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<SignUpPayLoad> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please Insert Required data", Toast.LENGTH_SHORT).show();
        }


    }

    public  String getLocalIpAddress() {
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


    public  String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private  String capitalize(String str) {
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


                    onSubmitRegestirationPaper();
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

}
